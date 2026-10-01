package Practice.Contests.Misc;

import java.util.*;


// ============================================================================
// LC 3508. Implement Router   [Medium]
// https://leetcode.com/problems/implement-router/
// Topics: Array, Hash Table, Binary Search, Design, Queue, Ordered Set
// ============================================================================
//
// PROBLEM
//   Design a Router that stores network packets - each has a source, a destination and a
//   timestamp - and never keeps more than memoryLimit of them.
//     Router(memoryLimit)
//     addPacket(source, destination, timestamp)
//         Store the packet and return true, unless an identical packet (same source,
//         destination AND timestamp) is already stored: then change nothing and return
//         false. If the router is full, the OLDEST stored packet is dropped first.
//         The timestamps of successive addPacket calls never decrease.
//     forwardPacket()
//         Remove the oldest stored packet (first in, first out) and return it as
//         [source, destination, timestamp]; return an empty array if nothing is stored.
//     getCount(destination, startTime, endTime)
//         Number of packets currently stored that go to destination and have a timestamp
//         from startTime to endTime, both ends included.
//
// EXAMPLES   memoryLimit = 3 (example 1 - main() replays it)
//   addPacket(1,4,90)    -> true
//   addPacket(2,5,90)    -> true
//   addPacket(1,4,90)    -> false     identical packet already stored
//   addPacket(3,5,95)    -> true
//   addPacket(4,5,105)   -> true      router was full: the oldest packet [1,4,90] is dropped
//   forwardPacket()      -> [2,5,90]
//   addPacket(5,2,110)   -> true
//   getCount(5,100,110)  -> 1         only [4,5,105] goes to 5 with a timestamp in 100..110
//   Example 2, memoryLimit = 2: addPacket(7,4,90) -> true, forwardPacket() -> [7,4,90],
//   forwardPacket() -> [] (nothing left).
//
// CONSTRAINTS
//   2 <= memoryLimit <= 10^5,   1 <= source, destination <= 2 * 10^5,
//   1 <= timestamp <= 10^9,   1 <= startTime <= endTime <= 10^9,
//   at most 10^5 calls in total, addPacket timestamps non-decreasing
//
// INTUITION
//   Each requirement asks for its own small structure:
//     - dropping and forwarding always take the OLDEST packet -> a FIFO queue;
//     - "is this packet a duplicate?" must be O(1) -> a HashSet with one key string
//       "source-destination-timestamp" per stored packet;
//     - getCount asks about one destination and a time window -> keep, per destination, the
//       list of timestamps of its stored packets. addPacket timestamps never decrease, so
//       appending keeps every list SORTED, and the packets inside [startTime, endTime] are
//       found with two binary searches: (first index with timestamp > endTime) minus
//       (first index with timestamp >= startTime).
//
// APPROACH (as implemented below)
//   Fields: packets (Queue<int[]>, oldest first), packetSet (Set<String> of keys),
//   destToTimestamps (Map<destination, sorted List<timestamp>>).
//   1. addPacket: build key = source-destination-timestamp; key already in packetSet ->
//      return false. Queue already holds memoryLimit packets -> poll the oldest and remove
//      its key from packetSet. Then offer the new packet, add its key, append its timestamp to
//      its destination's list, return true.
//   2. forwardPacket: empty queue -> return an empty array. Otherwise poll the oldest packet,
//      remove its key, binary-search its timestamp in its destination's list and remove that
//      entry (dropping the list when it becomes empty), return the packet.
//   3. getCount: no list for the destination -> 0. Otherwise left = lowerBound(list,
//      startTime), right = upperBound(list, endTime), answer right - left.
//   4. lowerBound = first index whose value is >= target; upperBound = first index whose
//      value is > target (both list.size() when there is none).
//
// DRY RUN   main() = example 1, Router(3)   (queue oldest first; destN = its timestamp list)
//   add (1,4,90)   queue [(1,4,90)]                         dest4 [90]           -> true
//   add (2,5,90)   queue [(1,4,90),(2,5,90)]                dest5 [90]           -> true
//   add (1,4,90)   key "1-4-90" is already in packetSet                          -> false
//   add (3,5,95)   queue [(1,4,90),(2,5,90),(3,5,95)]       dest5 [90,95]        -> true
//   add (4,5,105)  full: poll (1,4,90); queue [(2,5,90),(3,5,95),(4,5,105)]
//                  dest5 [90,95,105]; dest4 is STILL [90] (see STATUS)             -> true
//   forward        poll (2,5,90); dest5 [95,105]                                 -> [2,5,90]
//   add (5,2,110)  queue [(3,5,95),(4,5,105),(5,2,110)]     dest2 [110]          -> true
//   getCount(5,100,110): dest5 = [95,105]; lowerBound(100) = 1, upperBound(110) = 2
//                  -> 2 - 1 = 1
//
// STATUS
//   Known issue: when addPacket drops the oldest packet because the router is full, it
//   removes it from the queue and the key set but NOT its timestamp from destToTimestamps,
//   so getCount goes on counting packets that are gone. Example: Router(2); addPacket(1,5,10),
//   addPacket(2,5,11), addPacket(3,6,12) - this drops (1,5,10) - then getCount(5,10,10)
//   returns 1 (correct 0) and getCount(5,10,11) returns 2 (correct 1). LeetCode's example 1
//   still passes because the dropped packet went to destination 4 while getCount asks about 5.
//   Compared with a scanning reference on 3000 random call sequences, only getCount was ever
//   wrong, and only after an eviction. The stale timestamps also make destToTimestamps grow
//   beyond memoryLimit.
//   Structure differs from LeetCode: Router is a nested static class and main() replays
//   example 1 (a second scenario is commented out). The code is left unchanged on purpose.
//
// COMPLEXITY
//   addPacket    : O(1) on average - short key String, O(1) queue and HashSet operations
//   forwardPacket: O(log m + m) worst case - binary search, then ArrayList.remove shifts the
//                  tail (the oldest timestamp is usually at index 0); m = timestamps kept
//                  for that destination
//   getCount     : O(log m) - two binary searches
//   Space: O(memoryLimit) for the queue and the key set; destToTimestamps also keeps the
//          stale timestamps of dropped packets (see STATUS), so it grows with every
//          packet ever added
//
// GOTCHAS
//   - "Duplicate" only looks at the packets currently stored: once a packet has been
//     forwarded or dropped, an identical one may be added again (the commented-out second
//     scenario in main() checks exactly that).
//   - The duplicate test comes before the eviction, so a duplicate returns false without
//     dropping anything.
//   - Timestamps can repeat (non-decreasing, not strictly increasing), which is why the
//     window count uses lowerBound and upperBound instead of an exact-match search.
//   - tsList.remove(index) gets an int, so it removes by position, not by value.
//
// RELATED
//   - LC 146 LRU Cache (in this repo) - another bounded-capacity design that drops the
//     oldest / least recently used entry when full.
//   - LC 981 Time Based Key-Value Store - also keeps timestamps in arrival order and
//     answers queries with a binary search.
// ============================================================================
public class LC3508ImplementRouter {

    public static void  main(String[] args) {

        // Example 1 - prints true, true, false, true, true, [2, 5, 90], true, 1
        Router router = new Router(3); // Initialize Router with memoryLimit of 3.
        System.out.println(router.addPacket(1, 4, 90)); // Packet is added. Return True.
        System.out.println(router.addPacket(2, 5, 90)); // Packet is added. Return True.
        System.out.println(router.addPacket(1, 4, 90)); // This is a duplicate packet. Return False.
        System.out.println(router.addPacket(3, 5, 95)); // Packet is added. Return True
        System.out.println(router.addPacket(4, 5, 105)); // Packet is added, [1, 4, 90] is removed as number of packets exceeds memoryLimit. Return True.
        System.out.println(Arrays.toString(router.forwardPacket())); // Return [2, 5, 90] and remove it from router.
        System.out.println(router.addPacket(5, 2, 110)); // Packet is added. Return True.
        System.out.println(router.getCount(5, 100, 110));

        // second scenario (kept commented out): after a packet has been forwarded, the same
        // packet may be added again - it would print true, 1, [4, 2, 1], 0, true, 1
//        Router router = new Router(5); // Initialize Router with memoryLimit of 2.
//        System.out.println(router.addPacket(4,2,1));
//        System.out.println(router.getCount(2,1,1));
//        System.out.println(SDE.Arrays.toString(router.forwardPacket()));
//        System.out.println(router.getCount(2,1,1));
//        System.out.println(router.addPacket(4,2,1));
//        System.out.println(router.getCount(2,1,1));
    }

    static class Router {

        int memoryLimit;
        // stored packets as {source, destination, timestamp}, oldest at the head
        Queue<int[]> packets;
        // "source-destination-timestamp" of every stored packet, for the duplicate test
        Set<String> packetSet;

        // destination -> timestamps of its packets in arrival order (so sorted)
        Map<Integer, List<Integer>> destToTimestamps;


        public Router(int memoryLimit) {
            this.memoryLimit = memoryLimit;
            this.packets = new LinkedList<>();
            this.packetSet = new HashSet<>();
            this.destToTimestamps = new HashMap<>();
        }

        // Stores the packet; false if an identical packet is already stored.
        public boolean addPacket(int source, int destination, int timestamp) {

            // duplicate: same source, destination and timestamp among the stored packets
            String key = source+"-"+destination+"-"+timestamp;
            if(packetSet.contains(key)){
                return false;
            }
            // full: drop the oldest packet. BUG (see STATUS): its timestamp is not removed
            // from destToTimestamps here, unlike in forwardPacket
            if(packets.size() == memoryLimit) {
                int[] oldPacket = packets.poll();
                String oldKey = oldPacket[0] + "-" + oldPacket[1] + "-" + oldPacket[2];
                packetSet.remove(oldKey);
            }

            // timestamps never decrease, so appending keeps the destination's list sorted
            packets.offer(new int[]{source, destination, timestamp});
            packetSet.add(key);
            destToTimestamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

            return true;
        }

        // Removes and returns the oldest stored packet, or an empty array if there is none.
        public int[] forwardPacket() {
            if(!packets.isEmpty()){
                int[] oldPacket = packets.poll();
                String oldKey = oldPacket[0] + "-" + oldPacket[1] + "-" + oldPacket[2];
                packetSet.remove(oldKey);
                // also remove its timestamp from its destination's sorted list
                List<Integer> tsList = destToTimestamps.get(oldPacket[1]);
                int index = Collections.binarySearch(tsList, oldPacket[2]);
                tsList.remove(index);
                if (tsList.isEmpty()) destToTimestamps.remove(oldPacket[1]);
                return oldPacket;
            }else {
                return new int[0];
            }
        }

        // Number of stored packets for destination with startTime <= timestamp <= endTime.
        public int getCount(int destination, int startTime, int endTime) {
            if (!destToTimestamps.containsKey(destination)) return 0;

            // timestamps in the window occupy indices left .. right - 1 of the sorted list
            List<Integer> tsList = destToTimestamps.get(destination);
            int left = lowerBound(tsList, startTime);
            int right = upperBound(tsList, endTime);
            return right - left;
        }

        // first index whose value is >= target (list.size() if none)
        private int lowerBound(List<Integer> list, int target) {
            int l = 0, r = list.size();
            while (l < r) {
                int m = l + (r - l) / 2;
                if (list.get(m) >= target) r = m;
                else l = m + 1;
            }
            return l;
        }

        // first index whose value is > target (list.size() if none)
        private int upperBound(List<Integer> list, int target) {
            int l = 0, r = list.size();
            while (l < r) {
                int m = l + (r - l) / 2;
                if (list.get(m) > target) r = m;
                else l = m + 1;
            }
            return l;
        }
        }
}

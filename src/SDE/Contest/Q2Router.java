package SDE.Contest;

import java.util.*;


public class Q2Router {


    /**
    Design a data structure that can efficiently manage data packets in a network router. Each data packet consists of the following attributes:

source: A unique identifier for the machine that generated the packet.
destination: A unique identifier for the target machine.
timestamp: The time at which the packet arrived at the router.
Implement the Router class:

Router(int memoryLimit): Initializes the Router object with a fixed memory limit.

memoryLimit is the maximum number of packets the router can store at any given time.
If adding a new packet would exceed this limit, the oldest packet must be removed to free up space.
bool addPacket(int source, int destination, int timestamp): Adds a packet with the given attributes to the router.

A packet is considered a duplicate if another packet with the same source, destination, and timestamp already exists in the router.
Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.

Remove the packet from storage.
Return the packet as an array [source, destination, timestamp].
If there are no packets to forward, return an empty array.
int getCount(int destination, int startTime, int endTime):

Returns the number of packets currently stored in the router (i.e., not yet forwarded) that have the specified destination and have timestamps in the inclusive range [startTime, endTime].
Note that queries for addPacket will be made in increasing order of timestamp.

 

Example 1:

Input:
["Router", "addPacket", "addPacket", "addPacket", "addPacket", "addPacket", "forwardPacket", "addPacket", "getCount"]
[[3], [1, 4, 90], [2, 5, 90], [1, 4, 90], [3, 5, 95], [4, 5, 105], [], [5, 2, 110], [5, 100, 110]]

Output:
[null, true, true, false, true, true, [2, 5, 90], true, 1]

Explanation

Router router = new Router(3); // Initialize Router with memoryLimit of 3.
router.addPacket(1, 4, 90); // Packet is added. Return True.
router.addPacket(2, 5, 90); // Packet is added. Return True.
router.addPacket(1, 4, 90); // This is a duplicate packet. Return False.
router.addPacket(3, 5, 95); // Packet is added. Return True
router.addPacket(4, 5, 105); // Packet is added, [1, 4, 90] is removed as number of packets exceeds memoryLimit. Return True.
router.forwardPacket(); // Return [2, 5, 90] and remove it from router.
router.addPacket(5, 2, 110); // Packet is added. Return True.
router.getCount(5, 100, 110); // The only packet with destination 5 and timestamp in the inclusive range [100, 110] is [4, 5, 105]. Return 1.
Example 2:

Input:
["Router", "addPacket", "forwardPacket", "forwardPacket"]
[[2], [7, 4, 90], [], []]

Output:
[null, true, [7, 4, 90], []]

Explanation

Router router = new Router(2); // Initialize Router with memoryLimit of 2.
router.addPacket(7, 4, 90); // Return True.
router.forwardPacket(); // Return [7, 4, 90].
router.forwardPacket(); // There are no packets left, return [].©leetcode
     */

    public static void  main(String[] args) {

        Router router = new Router(3); // Initialize Router with memoryLimit of 3.
        System.out.println(router.addPacket(1, 4, 90)); // Packet is added. Return True.
        System.out.println(router.addPacket(2, 5, 90)); // Packet is added. Return True.
        System.out.println(router.addPacket(1, 4, 90)); // This is a duplicate packet. Return False.
        System.out.println(router.addPacket(3, 5, 95)); // Packet is added. Return True
        System.out.println(router.addPacket(4, 5, 105)); // Packet is added, [1, 4, 90] is removed as number of packets exceeds memoryLimit. Return True.
        System.out.println(Arrays.toString(router.forwardPacket())); // Return [2, 5, 90] and remove it from router.
        System.out.println(router.addPacket(5, 2, 110)); // Packet is added. Return True.
        System.out.println(router.getCount(5, 100, 110));

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
        Queue<int[]> packets;
        Set<String> packetSet;

        Map<Integer, List<Integer>> destToTimestamps;


        public Router(int memoryLimit) {
            this.memoryLimit = memoryLimit;
            this.packets = new LinkedList<>();
            this.packetSet = new HashSet<>();
            this.destToTimestamps = new HashMap<>();
        }

        public boolean addPacket(int source, int destination, int timestamp) {

            String key = source+"-"+destination+"-"+timestamp;
            if(packetSet.contains(key)){
                return false;
            }
            if(packets.size() == memoryLimit) {
                int[] oldPacket = packets.poll();
                String oldKey = oldPacket[0] + "-" + oldPacket[1] + "-" + oldPacket[2];
                packetSet.remove(oldKey);
            }

            packets.offer(new int[]{source, destination, timestamp});
            packetSet.add(key);
            destToTimestamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

            return true;
        }

        public int[] forwardPacket() {
            if(!packets.isEmpty()){
                int[] oldPacket = packets.poll();
                String oldKey = oldPacket[0] + "-" + oldPacket[1] + "-" + oldPacket[2];
                packetSet.remove(oldKey);
                List<Integer> tsList = destToTimestamps.get(oldPacket[1]);
                int index = Collections.binarySearch(tsList, oldPacket[2]);
                tsList.remove(index);
                if (tsList.isEmpty()) destToTimestamps.remove(oldPacket[1]);
                return oldPacket;
            }else {
                return new int[0];
            }
        }

        public int getCount(int destination, int startTime, int endTime) {
            if (!destToTimestamps.containsKey(destination)) return 0;

            List<Integer> tsList = destToTimestamps.get(destination);
            int left = lowerBound(tsList, startTime);
            int right = upperBound(tsList, endTime);
            return right - left;
        }

        private int lowerBound(List<Integer> list, int target) {
            int l = 0, r = list.size();
            while (l < r) {
                int m = l + (r - l) / 2;
                if (list.get(m) >= target) r = m;
                else l = m + 1;
            }
            return l;
        }

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

package Practice.Design;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 460. LFU Cache   [Hard]
// https://leetcode.com/problems/lfu-cache/
// Topics: Hash Table, Linked List, Design, Doubly-Linked List
// ============================================================================
//
// PROBLEM
//   Build a key -> value cache with a fixed capacity that throws out the Least Frequently
//   Used key when it runs out of room:
//     - LFUCache(capacity)  start empty, with room for `capacity` keys.
//     - get(key)            return the key's value, or -1 if it is not cached.
//     - put(key, value)     overwrite the value if the key is cached, else insert it. A new
//                           key arriving at a full cache first removes the key with the
//                           smallest use count; if several keys share that count, the one
//                           used longest ago goes.
//   Every key has a use count: it is 1 right after the key is inserted, and each later get
//   or put on that key adds 1. get and put must both be O(1) on average.
//
// EXAMPLES
//   capacity = 2
//     put(1,1), put(2,2)   cnt(1)=1, cnt(2)=1
//     get(1)   -> 1        cnt(1)=2
//     put(3,3)             full: 2 has the smallest count -> evict 2
//     get(2)   -> -1,   get(3) -> 3      now cnt(1)=2, cnt(3)=2
//     put(4,4)             tie at count 2: 1 was used longer ago -> evict 1
//     get(1)   -> -1,   get(3) -> 3,   get(4) -> 4
//   Output: [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
// CONSTRAINTS
//   1 <= capacity <= 10^4,   0 <= key <= 10^5,   0 <= value <= 10^9
//   At most 2 * 10^5 calls will be made to get and put.
//
// INTUITION
//   Group the keys by use count. freqMap maps each count to a doubly linked list of the
//   keys with that count, most recent first - so inside one group the LRU key is always
//   the last node (tail.prev), exactly like LC 146 LRU Cache.
//   A use moves a node from list f to list f + 1 - an O(1) unlink plus an O(1) insert,
//   because cache maps key -> node directly.
//   The eviction group is the one with the smallest count, kept in minFrequency. It never
//   needs a search, because only two events can change it:
//     - a new key arrives -> its count is 1, the lowest possible, so minFrequency = 1;
//     - the last node of the minFrequency list gets used -> it moves to minFrequency + 1,
//       and nothing is left below that, so minFrequency++.
//
// APPROACH (as implemented below)
//   1. Constructor: remember capacity; curSize = 0; both maps empty.
//   2. get(key): not in cache -> -1. Otherwise updateNode(curr) and return curr.value.
//   3. put on an existing key: overwrite curr.value, then updateNode(curr) (a use).
//   4. put on a new key: curSize++. If curSize > capacity, take the list
//      freqMap.get(minFrequency), remove its tail.prev from that list and from cache, and
//      curSize--. Then minFrequency = 1 and the new node goes to the front of the
//      frequency-1 list and into cache.
//   5. updateNode(curr): remove curr from the list of its count; if that list was the
//      minFrequency list and is now empty, minFrequency++. Then frequency++ and add curr
//      to the front of the list for the new count (created on first use).
//
// DRY RUN   capacity = 2, the LC example   (lists shown most recent first)
//   put(1,1)   freq1 [1]                                     min 1
//   put(2,2)   freq1 [2,1]                                   min 1
//   get(1)     1 -> freq2        freq1 [2]    freq2 [1]      min 1    returns 1
//   put(3,3)   curSize 3 > 2: evict freq1's tail.prev = 2
//                                freq1 [3]    freq2 [1]      min 1
//   get(2)     not cached                                             returns -1
//   get(3)     3 -> freq2; freq1 is now empty and was the min list -> min 2
//                                freq1 []     freq2 [3,1]    min 2    returns 3
//   put(4,4)   evict freq2's tail.prev = 1 (the LRU key of the tie)
//                                freq1 [4]    freq2 [3]      min 1
//   get(1) -> -1,   get(3) -> 3 (3 -> freq3),   get(4) -> 4 (4 -> freq2, min 2)
//
// STATUS
//   There is no main(), so running the file prints nothing. Replaying the LC example above
//   gives the expected output, and a randomized check against a brute-force LFU cache
//   agreed (both checked).
//   LeetCode names the class LFUCache: rename the class and constructor to submit.
//
// COMPLEXITY
//   Time : O(1) average per get / put - a fixed number of HashMap calls and pointer updates
//   Space: O(capacity + F) - at most capacity nodes (plus cache entries), and one list per
//          use count ever reached (F = the highest count); empty lists stay in freqMap
//
// GOTCHAS
//   - Ties are broken by recency: addNode puts a node at the head, so the victim is the
//     tail.prev of the minFrequency list.
//   - The eviction reads the OLD minFrequency, so it must run before minFrequency = 1 is set
//     for the new key.
//   - Only a NEW key can evict. A put on an existing key is just a use (updateNode).
//   - The eviction must also delete the key from cache - that is why DLLNode stores its key.
//   - capacity 0 would throw a NullPointerException on the first put (no list to evict
//     from), but LeetCode guarantees capacity >= 1.
//
// RELATED
//   - LC 146 LRU Cache - one recency list instead of one list per count; solved in this
//     repo as LC146LRUCache.java.
// ============================================================================
public class LC460LFUCache {
    // curSize: keys currently stored.  minFrequency: smallest use count present, i.e. the
    // count whose list holds the next eviction victim.
    int capacity;
    int minFrequency;
    int curSize;

    // freqMap: use count -> list of the nodes with that count (most recent first)
    // cache  : key -> its node, for O(1) lookup
    Map<Integer, DoubleLinkedList> freqMap;
    Map<Integer, DLLNode> cache;

    public LC460LFUCache(int capacity){
        this.capacity = capacity;
        this.curSize = 0;
        this.minFrequency = 0;

        this.freqMap = new HashMap<>();
        this.cache = new HashMap<>();
    }

    // Returns the value (or -1); a hit counts as one more use of the key.
    public int get(int key){
        DLLNode curr = cache.get(key);
        if(curr == null){
            return -1;
        }
        updateNode(curr);
        return curr.value;
    }

    // Updates or inserts the key. A new key arriving at a full cache first evicts the least
    // frequently used key (the least recently used one among ties).
    public void put(int key, int value){
        DLLNode curr = cache.get(key);
        if(curr != null){
            // existing key: new value, and the put counts as a use - never evicts
            curr.value = value;
            updateNode(curr);
        }else {
            // new key: count it first; going over capacity means one key must go
            curSize++;
            DLLNode newNode = new DLLNode(key, value);
            if(curSize > capacity){
                // victim = the least recent node (tail.prev) of the lowest-count list; this
                // still uses the OLD minFrequency, so it has to run before the reset below
                DoubleLinkedList list = freqMap.get(minFrequency);
                DLLNode deletableNode = list.tail.prev;
                list.removeNode(deletableNode);
                cache.remove(deletableNode.key);
                curSize --;
            }
            // the new key has been used once - no count can be lower
            minFrequency = 1;
            DoubleLinkedList currList = freqMap.getOrDefault(1, new DoubleLinkedList());
            currList.addNode(newNode);
            freqMap.put(1, currList);
            cache.put(key, newNode);
        }
    }

    // One more use of curr: move it from the list of its count to the front of the list
    // for count + 1.
    private void updateNode(DLLNode curr) {
        int currFreq = curr.frequency;
        DoubleLinkedList currList = freqMap.get(currFreq);
        currList.removeNode(curr);
        // curr was the last node with the smallest count -> the smallest count is now
        // currFreq + 1 (curr itself is there)
        if(currFreq == minFrequency  && currList.size == 0 ){
            minFrequency++;
        }
        curr.frequency++;
        DoubleLinkedList newList = freqMap.getOrDefault(curr.frequency, new DoubleLinkedList());
        newList.addNode(curr);
        freqMap.put(curr.frequency, newList);
    }


    // Doubly linked list with head / tail sentinels: head.next is the most recent node,
    // tail.prev the least recent (the eviction victim). size lets updateNode spot an empty
    // list.
    class DoubleLinkedList{
        int size;
        DLLNode tail;
        DLLNode head;
        public DoubleLinkedList(){
            this.size = 0;
            this.tail = new DLLNode(0, 0);
            this.head = new DLLNode(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // inserts node right after head, i.e. at the most-recent end
        public void addNode(DLLNode node){
            DLLNode nextNode = head.next;
            node.next = nextNode;
            node.prev = head;
            nextNode.prev = node;
            head.next = node;
            size++;
        }

        // unlinks node in O(1): its two neighbours now point at each other
        public void removeNode(DLLNode node){
            DLLNode prevNode = node.prev;
            DLLNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }
    }
    // One cached key. frequency is its use count (1 after the first put); the key is kept
    // so that eviction can also remove the entry from cache.
    class DLLNode{
        int key;
        int value;
        int frequency;
        DLLNode next;
        DLLNode prev;
        public DLLNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
            this.next = null;
            this.prev = null;
        }
    }
}

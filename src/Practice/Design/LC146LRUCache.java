package Practice.Design;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 146. LRU Cache   [Medium]
// https://leetcode.com/problems/lru-cache/
// Topics: Hash Table, Linked List, Design, Doubly-Linked List
// ============================================================================
//
// PROBLEM
//   Build a key -> value cache with a fixed capacity that forgets the Least Recently Used
//   key when it runs out of room:
//     - LRUCache(capacity)  start empty, with room for `capacity` keys (capacity >= 1).
//     - get(key)            return the key's value, or -1 if it is not cached.
//     - put(key, value)     insert the key, or overwrite its value if it is already there.
//                           If this makes the cache hold more than capacity keys, throw
//                           out the key that was used longest ago.
//   Both get and a put count as a "use" of that key. get and put must be O(1) on average.
//
// EXAMPLES
//   capacity = 2
//     put(1,1), put(2,2)   cache {1=1, 2=2}
//     get(1)   -> 1        1 is now the most recently used
//     put(3,3)             full, 2 is the least recent -> evict 2, cache {1=1, 3=3}
//     get(2)   -> -1
//     put(4,4)             evict 1 -> cache {3=3, 4=4}
//     get(1)   -> -1,   get(3) -> 3,   get(4) -> 4
//   Output: [null, null, null, 1, null, -1, null, -1, 3, 4]
//
// CONSTRAINTS
//   1 <= capacity <= 3000,   0 <= key <= 10^4,   0 <= value <= 10^5
//   At most 2 * 10^5 calls will be made to get and put.
//
// INTUITION
//   Two jobs must both be O(1):
//     - find a key's entry                              -> a HashMap
//     - keep the usage order, move an entry to "most recent", drop the oldest
//                                                       -> a doubly linked list
//   The list runs from most recent (right after head) to least recent (right before
//   tail), and the map stores key -> list node. After a lookup the node can be unlinked in
//   O(1) because it knows its own prev and next, then spliced in right after head. The key
//   to evict is therefore always tail.prev.
//   head and tail are sentinel (dummy) nodes, so the list is never really empty and
//   remove / insertAfterHead never need a null check.
//
// APPROACH (as implemented below)
//   1. Constructor: link the sentinels head <-> tail, remember capacity, empty map.
//   2. get(key): not in map -> -1. Otherwise remove(node) + insertAfterHead(node) to mark
//      it most recent, and return node.value.
//   3. put(key, value) for an existing key: overwrite node.value, move it after head.
//   4. put(key, value) for a new key: if map.size() == capacity, evict lru = tail.prev
//      from the list AND from the map (that is why Node keeps its key); then create the
//      node, insertAfterHead(newNode) and map.put(key, newNode).
//   5. remove(node) points its two neighbours at each other; insertAfterHead(node) wires
//      node in between head and the old first node.
//
// DRY RUN   capacity = 2, the LC example   (list shown head -> tail, most recent first)
//   put(1,1)   list [1]
//   put(2,2)   list [2,1]
//   get(1)     1 moves to the front             list [1,2]     returns 1
//   put(3,3)   full: lru = tail.prev = 2, gone  list [3,1]
//   get(2)     not in map                                      returns -1
//   put(4,4)   full: lru = tail.prev = 1, gone  list [4,3]
//   get(1) -> -1,   get(3) -> 3 (list [3,4]),   get(4) -> 4 (list [4,3])
//
// STATUS
//   main() is only a smoke test (capacity 4, get(4) returns -1, then put(4,2)) and prints
//   nothing. Replaying the LC example above gives the expected output (checked).
//   LeetCode names the class LRUCache: rename the class and constructor to submit.
//
// COMPLEXITY
//   Time : O(1) average per get / put - one HashMap operation plus a fixed number of
//          pointer updates
//   Space: O(capacity) - at most capacity nodes and map entries, plus the two sentinels
//
// GOTCHAS
//   - Evict only when adding a NEW key to a full cache. Updating an existing key keeps
//     the size the same, so nothing is evicted.
//   - Eviction must also delete lru.key from the map, or get() would find a node that is
//     no longer in the list.
//   - put on an existing key is a use too: the node must move to the front.
//
// RELATED
//   - LC 460 LFU Cache - evicts the least FREQUENTLY used key instead (ties broken by
//     recency); solved in this repo as LC460LFUCache.java.
// ============================================================================
public class LC146LRUCache {



    public static void main(String[] args) {
        // Smoke test only: get(4) on an empty cache returns -1 (kept in param_1),
        // then put(4,2) stores 4=2. Nothing is printed.
        LC146LRUCache obj = new LC146LRUCache(4);
        int param_1 = obj.get(4);
        obj.put(4,2);
    }


    // map: key -> its list node.  List order: head.next = most recent ... tail.prev = least recent
    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    public LC146LRUCache(int capacity) {
        // sentinels: the list is never empty, so remove / insert need no null checks
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    // Returns the value (or -1) and marks the key as the most recently used.
    public int get(int key) {

        if (!map.containsKey(key)) return -1;
        // deleting a node
        Node temp = map.get(key);
        remove(temp);
        // inserting in after head
        insertAfterHead(temp);
        return temp.value;
    }

    // Inserts or updates the key and marks it most recent; evicts tail.prev when a new key
    // arrives at a full cache.
    public void put(int key, int value) {

        if(map.containsKey(key)){
            // existing key: new value + move to the front; the size is unchanged, no eviction
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAfterHead(node);
        }else {
            // new key: make room first when the cache is full
            if (map.size() == capacity) {
                // Remove LRU node from list and map
                // (the least recently used node always sits right before the tail sentinel)
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            insertAfterHead(newNode);
            map.put(key, newNode);
        }
    }


    // Unlinks node in O(1): its two neighbours now point at each other.
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Splices node in right after head, i.e. into the "most recently used" slot.
    private void insertAfterHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }



    // Doubly linked list node. It stores its key so eviction can also delete the map entry.
    static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
}

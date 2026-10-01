package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * LC 146. LRU Cache   [Medium]
 * https://leetcode.com/problems/lru-cache/
 * Topics: Hash Table, Linked List, Design, Doubly-Linked List
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #10
 *       also: ch. 33 Designing data structures (#1)
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Design a cache that holds at most `capacity` key-value pairs and, when it overflows,
 *   throws out the key that was used least recently.
 *     LRUCache(capacity)  - creates an empty cache (capacity is positive)
 *     get(key)            - returns the stored value, or -1 when the key is not in the cache
 *     put(key, value)     - stores the pair, overwriting the value if the key already exists;
 *                           if that leaves more than `capacity` keys, evict the least
 *                           recently used one
 *   Reading a key with get and writing it with put both count as using it.
 *   Both get and put must run in O(1) average time.
 *
 * EXAMPLES
 *   LRUCache(2), put(1,1), put(2,2), get(1) -> 1, put(3,3) [key 2 is evicted],
 *   get(2) -> -1, put(4,4) [key 1 is evicted], get(1) -> -1, get(3) -> 3, get(4) -> 4
 *
 * CONSTRAINTS
 *   1 <= capacity <= 3000,   0 <= key <= 10^4,   0 <= value <= 10^5,
 *   at most 2 * 10^5 calls will be made to get and put
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Design/LC146LRUCache.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC146LRUCache {

    /**
     * Variant A - brute force: keep keys in a recency list, scan it on every operation
     * <br>Target: time O(capacity) per get and per put, space O(capacity)
     */
    public static class LRUCacheBruteForce {
        public LRUCacheBruteForce(int capacity) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int get(int key) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void put(int key, int value) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B - let java.util.LinkedHashMap keep the keys in access order
     * <br>Target: time O(1) per get and per put, space O(capacity)
     */
    public static class LRUCacheLinkedHashMap {
        public LRUCacheLinkedHashMap(int capacity) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int get(int key) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void put(int key, int value) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant C - this chapter's technique: hash map into a hand-built doubly linked list
     * <br>Target: time O(1) per get and per put, space O(capacity)
     */
    public static class LRUCache {
        public LRUCache(int capacity) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int get(int key) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void put(int key, int value) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", capacity -> {
            LC146LRUCache.LRUCacheBruteForce c = new LC146LRUCache.LRUCacheBruteForce(capacity);
            return new Cache() {
                public int get(int key) { return c.get(key); }
                public void put(int key, int value) { c.put(key, value); }
            };
        });
        runAll("B", capacity -> {
            LC146LRUCache.LRUCacheLinkedHashMap c =
                    new LC146LRUCache.LRUCacheLinkedHashMap(capacity);
            return new Cache() {
                public int get(int key) { return c.get(key); }
                public void put(int key, int value) { c.put(key, value); }
            };
        });
        runAll("C", capacity -> {
            LC146LRUCache.LRUCache c = new LC146LRUCache.LRUCache(capacity);
            return new Cache() {
                public int get(int key) { return c.get(key); }
                public void put(int key, int value) { c.put(key, value); }
            };
        });
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 146. LRU Cache)
    // https://leetcode.com/problems/lru-cache/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the official example
    // plus edge cases checked against an OrderedDict-style reference model. A case is a
    // capacity plus a sequence like "put 1 1; get 1": the list of values returned by the
    // get calls is compared (put calls return nothing).
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // the two operations every variant offers
    interface Cache {
        int get(int key);

        void put(int key, int value);
    }

    interface Factory {
        Cache create(int capacity);
    }

    private static void runAll(String v, Factory f) {
        // official example
        check(v + " example 1", new int[]{1, -1, -1, 3, 4}, f, 2,
                "put 1 1; put 2 2; get 1; put 3 3; get 2; put 4 4; get 1; get 3; get 4");
        // edge: capacity one, every new key evicts the previous one; updating needs no eviction
        check(v + " capacity one", new int[]{-1, 2, 3}, f, 1,
                "put 1 1; put 2 2; get 1; get 2; put 2 3; get 2");
        // edge: overwriting an existing key makes it the most recently used
        check(v + " put refreshes", new int[]{-1, 10, 3}, f, 2,
                "put 1 1; put 2 2; put 1 10; put 3 3; get 2; get 1; get 3");
        // edge: key 0 and value 0 (not confused with -1), a missing key, the largest key and value
        check(v + " boundaries", new int[]{0, -1, 100000, -1, 100000, 5}, f, 2,
                "put 0 0; get 0; get 7; put 10000 100000; get 10000; put 5 5; "
                        + "get 0; get 10000; get 5");
    }

    // replays the operations on a fresh cache and returns the results of all get calls
    private static List<Integer> replay(Factory f, int capacity, String ops) {
        Cache cache = f.create(capacity);
        List<Integer> results = new ArrayList<>();
        for (String op : ops.split("; ")) {
            String[] t = op.split(" ");
            if (t[0].equals("get")) results.add(cache.get(Integer.parseInt(t[1])));
            else cache.put(Integer.parseInt(t[1]), Integer.parseInt(t[2]));
        }
        return results;
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, int[] expected, Factory f, int capacity, String ops) {
        total++;
        String got;
        boolean ok = false;
        try {
            List<Integer> results = replay(f, capacity, ops);
            ok = results.size() == expected.length;
            for (int i = 0; ok && i < expected.length; i++) {
                ok = results.get(i) == expected[i];
            }
            got = results.toString();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + Arrays.toString(expected) + ", got " + got));
    }

    // ============================== END TESTS ===============================
}

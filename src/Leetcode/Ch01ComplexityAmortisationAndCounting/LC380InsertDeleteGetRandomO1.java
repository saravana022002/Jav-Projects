package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 380. Insert Delete GetRandom O(1)   [Medium]
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * Topics: Array, Hash Table, Math, Design, Randomized
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #9
 *       also: ch. 23 Reservoir sampling and randomised streaming (#4),
 *             ch. 33 Designing data structures (#4)
 *
 * PROBLEM
 *   Design a set of integers (LeetCode calls the class RandomizedSet) with three operations,
 *   each required to run in O(1) on average:
 *     insert(val)  - add val if it is missing; return true if it was new, false otherwise
 *     remove(val)  - delete val if it is present; return true if it was there, false otherwise
 *     getRandom()  - return one element currently in the set, every element equally likely
 *                    (it is only called when the set is not empty)
 *
 * EXAMPLES
 *   calls:   insert(1) remove(2) insert(2) getRandom() remove(1) insert(2) getRandom()
 *   returns: true      false     true      1 or 2      true      false     2
 *   (the set is {1} -> {1,2} -> {2}; the last getRandom() can only answer 2)
 *
 * CONSTRAINTS
 *   -2^31 <= val <= 2^31 - 1,   at most 2 * 10^5 calls in total,
 *   getRandom() is only called while the set holds at least one element
 *
 * INTUITION
 *   Two classic structures each fail at one of the jobs:
 *     - a hash map finds or deletes a value in O(1), but cannot pick a random key in O(1);
 *     - an array picks a random element in O(1) (random index), but finding a value is O(n)
 *       and deleting from the middle shifts everything after it.
 *   Keep BOTH: the array holds the elements densely in slots 0..size-1, and the map remembers
 *   which slot every value sits in. Deleting from the middle is then a trick: a set has no
 *   order, so overwrite the hole with the LAST element, tell the map where that element lives
 *   now, and chop off the last slot - which is O(1).
 *
 * APPROACH (as implemented below)
 *   Fields: arr = the current elements;  map = value -> its index in arr.
 *   1. insert(val): if map has no val -> arr.add(val), map.put(val, index of the new last
 *      slot), return true; otherwise return false.
 *   2. remove(val): if map has val ->
 *        elementIndex = map.get(val),  lastIndex = arr.size() - 1
 *        arr.set(elementIndex, arr.get(lastIndex))   the last element fills the hole
 *        map.put(arr.get(lastIndex), elementIndex)   ... and the map learns its new slot
 *        map.remove(val);  arr.remove(lastIndex)     val is gone, the last slot is dropped
 *        return true;  otherwise return false.
 *   3. getRandom(): random index in [0, arr.size()) from ThreadLocalRandom; return arr.get(it).
 *
 * DRY RUN   insert(10), insert(20), insert(30), then remove(10)
 *   after the inserts   arr = [10, 20, 30]   map = {10:0, 20:1, 30:2}
 *   remove(10): elementIndex = 0, lastIndex = 2
 *     arr.set(0, 30)    arr = [30, 20, 30]
 *     map.put(30, 0)    map = {10:0, 20:1, 30:0}
 *     map.remove(10)    map = {20:1, 30:0}
 *     arr.remove(2)     arr = [30, 20]
 *   Now getRandom() picks index 0 or 1, so it returns 30 or 20 with equal probability.
 *
 * COMPLEXITY
 *   Time : O(1) average per call - HashMap get / put / remove are O(1) on average, ArrayList
 *          get / set are O(1), add at the end is amortised O(1), and removing the LAST
 *          element moves nothing
 *   Space: O(n) for n elements - one array slot and one map entry each
 *
 * GOTCHAS
 *   - arr.remove(lastIndex): lastIndex is an int, so this is ArrayList.remove(int index), which
 *     drops a slot. With an Integer it would remove by value instead.
 *   - When val is itself the last element (elementIndex == lastIndex), the map.put line
 *     re-adds val and the following map.remove(val) deletes it again. The order of those two
 *     lines matters: swapped, a stale entry for val would stay in the map.
 *   - Duplicates are not stored: insert of a present value returns false and changes nothing.
 *     (LC 381 allows duplicates; there each value needs a SET of indexes.)
 *   - getRandom() on an empty set throws IllegalArgumentException (nextInt(0, 0)); the
 *     problem guarantees that never happens.
 *
 * STATUS
 *   Correct. main() is empty, so running this file prints nothing.
 *   Signature differs from LeetCode: the class and constructor are named
 *   LC380InsertDeleteGetRandomO1; LeetCode expects the class to be called RandomizedSet.
 *
 * RELATED
 *   - LC 381 Insert Delete GetRandom O(1) - Duplicates allowed - the same array + map idea,
 *     but the map keeps a set of indexes for every value.
 * </pre>
 */
public class LC380InsertDeleteGetRandomO1 {
    public static void main(String[] args) {
        // intentionally empty - nothing is run here
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // value -> its current slot in arr (finds a value in O(1))
    Map<Integer, Integer> map;
    // the current elements, packed in slots 0..size-1 (a random slot = a random element)
    ArrayList<Integer> arr;
    public LC380InsertDeleteGetRandomO1() {
        this.map = new HashMap<>();
        this.arr = new ArrayList<>();
    }

    // true if val was new: append it and remember its slot
    public boolean insert(int val) {
        if(this.map.get(val) == null) {
            this.arr.add(val);
            this.map.put(val, this.arr.size() - 1);
            return true;
        }
        return false;
    }

    // true if val was present: swap-with-last trick so no slot in the middle stays empty
    public boolean remove(int val) {
        if(this.map.get(val) != null) {
            int elementIndex = this.map.get(val);
            int lastIndex = this.arr.size() - 1;

            // the last element moves into val's slot and the map is told its new slot
            this.arr.set(elementIndex, this.arr.get(lastIndex));
            this.map.put(this.arr.get(lastIndex), elementIndex);

            // forget val, then drop the (now duplicated) last slot
            this.map.remove(val);
            this.arr.remove(lastIndex);
            return true;
        }
        return false;
    }

    // uniform random slot in [0, size) -> every element is equally likely
    public int getRandom() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, this.arr.size());
        return this.arr.get(randomNum);
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 380. Insert Delete GetRandom O(1))
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the official operation sequence (one check per call) + edge cases. Expected values
    // come from the statement or from a plain java.util.HashSet model replayed offline (the
    // 36-call sequence below). getRandom() is random, so it is checked by what it may return:
    // always a current element, and over many draws every current element shows up.
    //   Expected result: 14/14 passed
    // ==========================================================================
    private static void runTests() {
        // official example: replay the calls and check every returned value
        LC380InsertDeleteGetRandomO1 s = new LC380InsertDeleteGetRandomO1();
        check("example: insert(1)", true, () -> s.insert(1));
        check("example: remove(2) - not present", false, () -> s.remove(2));
        check("example: insert(2)", true, () -> s.insert(2));
        check("example: getRandom() is 1 or 2", true, () -> {
            int v = s.getRandom();
            return v == 1 || v == 2;
        });
        check("example: remove(1)", true, () -> s.remove(1));
        check("example: insert(2) - already present", false, () -> s.insert(2));
        check("example: getRandom() -> 2 (the only element)", 2, () -> s.getRandom());

        // edge: removing the first element moves the LAST one into its slot; that moved element
        // must still be found and removable afterwards (the index bookkeeping)
        LC380InsertDeleteGetRandomO1 swap = new LC380InsertDeleteGetRandomO1();
        check("swap bookkeeping: i1 i2 i3 r1 r3 r2 r2 i7", "TTTTTTFT",
                () -> run(swap, "i1 i2 i3 r1 r3 r2 r2 i7"));
        check("swap bookkeeping: only 7 is left", 7, () -> swap.getRandom());

        // edge: extreme int values and 0
        LC380InsertDeleteGetRandomO1 ext = new LC380InsertDeleteGetRandomO1();
        check("extreme values: min, max, 0, remove min twice", "TTTTF",
                () -> run(ext, "i-2147483648 i2147483647 i0 r-2147483648 r-2147483648"));
        check("extreme values: min is gone, 0 and max remain", Arrays.asList(0, 2147483647),
                () -> seen(ext, 400));

        // edge: three elements must be drawn about equally often (10000 each of 30000 draws;
        // the allowed band 9000..11000 is about 12 standard deviations wide)
        LC380InsertDeleteGetRandomO1 uni = new LC380InsertDeleteGetRandomO1();
        uni.insert(1);
        uni.insert(2);
        uni.insert(3);
        check("uniform: 30000 draws over {1,2,3}", true, () -> {
            int[] count = new int[4];
            for (int i = 0; i < 30000; i++) count[uni.getRandom()]++;
            return count[1] >= 9000 && count[1] <= 11000 && count[2] >= 9000 && count[2] <= 11000
                    && count[3] >= 9000 && count[3] <= 11000;
        });

        // edge: a longer mixed sequence over a few values (hits, misses, duplicates, removals
        // from the front, middle and end); expected answers from a HashSet model
        LC380InsertDeleteGetRandomO1 mix = new LC380InsertDeleteGetRandomO1();
        check("36 mixed insert/remove calls vs a HashSet model",
                "TFFTFFTFTTTFFFTFTFTTFTTTFFFTFFTTFTTF",
                () -> run(mix, "i-1 r1 i-1 i2 r3 r3 i3 r-3 i1 r3 i-3 r-2 i-3 i1 i0 i-3 i3 i3 r0 "
                        + "r-1 i3 r3 r1 r-3 r-3 r-3 r3 i3 r-2 r0 r3 i-1 r0 i1 i-3 r-2"));
        check("36 mixed calls: getRandom covers exactly {-3,-1,1,2}", Arrays.asList(-3, -1, 1, 2),
                () -> seen(mix, 400));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs "i<v>" (insert v) and "r<v>" (remove v) calls; one letter per call: T = true, F = false
    private static String run(LC380InsertDeleteGetRandomO1 set, String calls) {
        StringBuilder out = new StringBuilder();
        for (String c : calls.split(" ")) {
            int v = Integer.parseInt(c.substring(1));
            boolean r = c.charAt(0) == 'i' ? set.insert(v) : set.remove(v);
            out.append(r ? 'T' : 'F');
        }
        return out.toString();
    }

    // the sorted distinct values that getRandom() returns over many draws
    private static List<Integer> seen(LC380InsertDeleteGetRandomO1 set, int draws) {
        TreeSet<Integer> values = new TreeSet<>();
        for (int i = 0; i < draws; i++) values.add(set.getRandom());
        return new ArrayList<>(values);
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}

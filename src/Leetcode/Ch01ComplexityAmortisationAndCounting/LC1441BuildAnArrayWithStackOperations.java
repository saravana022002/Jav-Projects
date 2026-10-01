package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1441. Build an Array With Stack Operations   [Medium]
 * https://leetcode.com/problems/build-an-array-with-stack-operations/
 * Topics: Array, Stack, Simulation
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #1
 *
 * PROBLEM
 *   The numbers 1, 2, ..., n are handed out one by one, in that order. You begin with an
 *   empty stack and may do "Push" (take the next number and put it on the stack) or "Pop"
 *   (remove the top number). Return a list of operations after which the stack, read from
 *   bottom to top, is exactly target. The moment the stack equals target you must stop -
 *   no more numbers may be read. target is strictly increasing and every value is in 1..n.
 *   If several operation lists work, any of them is accepted.
 *
 * EXAMPLES
 *   target = [1,3],   n = 3  ->  ["Push","Push","Pop","Push"]   2 is pushed, then thrown away
 *   target = [1,2,3], n = 3  ->  ["Push","Push","Push"]
 *   target = [1,2],   n = 4  ->  ["Push","Push"]                stop before reading 3
 *
 * CONSTRAINTS
 *   1 <= target.length <= 100,   1 <= n <= 100,   1 <= target[i] <= n,
 *   target is strictly increasing
 *
 * INTUITION
 *   Both the stream and target go upwards, so walk through target in order. Every stream
 *   number below the value we want next is useless: push it and pop it at once, so the stack
 *   still holds only the part of target built so far. The wanted number is pushed and stays.
 *   A number that has been passed never matters again, so the stack never needs to be
 *   inspected - the list of operations can be written out directly. The loop ends right after
 *   the last target value, which is exactly "stop as soon as the stack equals target".
 *
 * APPROACH (as implemented below)
 *   1. ans = empty list; streamNum = 1 (the number the stream hands out next).
 *   2. For each wanted value j in target, in order:
 *        - while streamNum < j: add "Push" and "Pop" (read it and discard it), streamNum++
 *        - then add "Push" (read j and keep it), streamNum++
 *   3. Return ans.
 *
 * DRY RUN   target = [1,3], n = 3 (the input main() uses)
 *   streamNum = 1
 *   j = 1: nothing to skip; Push, streamNum = 2                  ans = [Push]
 *   j = 3: streamNum 2 < 3 -> Push, Pop, streamNum = 3           ans = [Push, Push, Pop]
 *          3 < 3 is false -> Push (keep 3), streamNum = 4        ans = [Push, Push, Pop, Push]
 *   target is used up -> return [Push, Push, Pop, Push]
 *
 * COMPLEXITY
 *   Time : O(n) - one step per stream number up to the last target value (at most n), each
 *          step adds at most two operations
 *   Space: O(1) besides the answer, which has at most 2n entries
 *
 * GOTCHAS
 *   - The parameter n is never used: target is increasing and target[i] <= n, so the stream
 *     can never run out before the last wanted value.
 *   - Stopping early matters: for target = [1,2] with n = 4 the answer is just
 *     [Push, Push]. Reading 3 would be rejected - this code never goes past target's last value.
 *   - Several answers can be valid, e.g. target = [3] with n = 3 also accepts
 *     [Push, Push, Pop, Pop, Push]; the code returns [Push, Pop, Push, Pop, Push].
 *
 * STATUS
 *   Correct. main() calls buildArray but does not print the result (it returns
 *   [Push, Push, Pop, Push]).
 *
 * SEE ALSO
 *   src/Practice/Stack/LC1441BuildAnArrayWithStackOperationsV1.java - loops over the stream
 *     1..n instead of over target; breaks right after the last wanted push
 *   src/Practice/Stack/LC1441BuildAnArrayWithStackOperationsV2.java - same loop over 1..n, but
 *     checks "all of target pushed" at the top of the loop
 *
 * RELATED
 *   - LC 1047 Remove All Adjacent Duplicates In String (same chapter) - another stack
 *     problem where every element is pushed once and popped at most once, so O(n) overall.
 * </pre>
 */
public class LC1441BuildAnArrayWithStackOperations {

    public static void main(String[] args) {
        // Example 1 - the result is not printed; buildArray returns [Push, Push, Pop, Push]
        int[] target = {1,3};
        int n = 3;
        buildArray(target, n);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Returns the operations that build target from the stream 1, 2, 3, ... (n is not needed)
    public static List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        // the number the stream will hand out next
        int streamNum = 1;
        for (int j : target) {
            // every number below the wanted value j is read and discarded at once
            while (streamNum < j) {
                ans.add("Push");
                ans.add("Pop");
                streamNum++;
            }
            // streamNum == j: read it and keep it
            ans.add("Push");
            streamNum++;
        }
        // the loop ends right after target's last value, so the stream is not read any further
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1441. Build an Array With Stack Operations)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples (compared with the official output; a brute force over
    // all rule-abiding operation lists confirms each has exactly one valid answer) + edge cases.
    // Many inputs have several valid answers ("return any of them"), so the edge cases replay
    // the returned operations on a real stack and check the rules instead of one fixed list.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [1,3], n=3", Arrays.asList("Push", "Push", "Pop", "Push"),
                () -> LC1441BuildAnArrayWithStackOperations.buildArray(new int[]{1, 3}, 3));
        check("example 2: [1,2,3], n=3", Arrays.asList("Push", "Push", "Push"),
                () -> LC1441BuildAnArrayWithStackOperations.buildArray(new int[]{1, 2, 3}, 3));
        check("example 3: [1,2], n=4 stops early", Arrays.asList("Push", "Push"),
                () -> LC1441BuildAnArrayWithStackOperations.buildArray(new int[]{1, 2}, 4));
        // edge: the smallest input
        checkReplay("smallest: [1], n=1", new int[]{1}, 1);
        // edge: gaps in the middle (this input has two valid answers)
        checkReplay("gaps: [3,5], n=5", new int[]{3, 5}, 5);
        // edge: everything is wanted, at the maximum size - no Pop at all
        int[] all = new int[100];
        for (int i = 0; i < 100; i++) all[i] = i + 1;
        checkReplay("full range: [1..100], n=100", all, 100);
        // edge: one wanted value far away - 99 numbers are pushed and popped first
        checkReplay("far value: [100], n=100", new int[]{100}, 100);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs the solution and replays its answer on a real stack
    private static void checkReplay(String name, int[] target, int n) {
        check(name + " (replayed)", "ok", () -> {
            List<String> ops = LC1441BuildAnArrayWithStackOperations.buildArray(target, n);
            return replay(target, n, ops);
        });
    }

    // Replays ops on a real stack with the statement's rules. Returns "ok" when the stack ends
    // equal to target and was not equal to it earlier; otherwise a message saying what broke.
    private static String replay(int[] target, int n, List<String> ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        int next = 1;
        for (String op : ops) {
            if (matches(stack, target)) return "kept going after the stack already equalled target";
            if (op.equals("Push")) {
                if (next > n) return "pushed past the end of the stream";
                stack.addLast(next++);
            } else if (op.equals("Pop")) {
                if (stack.isEmpty()) return "popped an empty stack";
                stack.removeLast();
            } else {
                return "unknown operation " + op;
            }
        }
        return matches(stack, target) ? "ok" : "ended with stack " + stack;
    }

    // stack iterates from its bottom (first) to its top (last)
    private static boolean matches(Deque<Integer> stack, int[] target) {
        if (stack.size() != target.length) return false;
        int i = 0;
        for (int v : stack) {
            if (v != target[i++]) return false;
        }
        return true;
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

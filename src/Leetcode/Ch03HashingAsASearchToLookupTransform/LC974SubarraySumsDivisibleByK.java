package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 974. Subarray Sums Divisible by K   [Medium]
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * Topics: Array, Hash Table, Prefix Sum
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #8
 *       also: ch. 2 Prefix sums and difference arrays (#6)
 *
 * PROBLEM
 *   Given an int array nums and an int k, count the non-empty subarrays (contiguous parts)
 *   whose sum is divisible by k. Subarrays are counted by position, so equal-looking ones
 *   at different places count separately.
 *
 * EXAMPLES
 *   nums = [4,5,0,-2,-3,1], k = 5  ->  7    the subarrays [4,5,0,-2,-3,1] (sum 5), [5], [5,0],
 *                                          [5,0,-2,-3] (sum 0), [0], [0,-2,-3], [-2,-3]
 *   nums = [5], k = 9              ->  0    5 is not divisible by 9
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 3 * 10^4,   -10^4 <= nums[i] <= 10^4,   2 <= k <= 10^4
 *
 * INTUITION
 *   Let P[i] be the sum of the first i numbers (P[0] = 0). The subarray nums[l..r] sums to
 *   P[r+1] - P[l], and that is divisible by k exactly when P[r+1] and P[l] have the SAME
 *   remainder mod k. So the answer is the number of pairs of prefix sums (the empty prefix
 *   P[0] included) with equal remainders. Count them on the fly: keep a table
 *   rems[r] = how many prefix sums so far had remainder r. A new prefix with remainder r
 *   pairs up with every one of them, so add rems[r], then increment it.
 *   Remainders are only 0..k-1, so the "lookup table" can be a plain array of k counters.
 *
 * APPROACH (as implemented below)
 *   1. rems = new int[k]: rems[r] = how many prefix sums so far have remainder r.
 *      rems[0] = 1 stands for the empty prefix.
 *   2. sum is the running prefix sum, count the answer. For each num: sum += num.
 *   3. rem = ((sum % k) + k) % k. Java's % is negative for a negative sum (-1 % 5 is -1);
 *      adding k and taking % k again maps the result into 0..k-1.
 *   4. count += rems[rem] (each earlier prefix with this remainder closes one divisible
 *      subarray ending here), then rems[rem]++.
 *   5. Return count.
 *
 * DRY RUN   nums = [4,5,0,-2,-3,1], k = 5   (what main() runs; rems starts as rems[0] = 1)
 *   num= 4: sum=4  rem=4   count += rems[4]=0 -> 0    rems[4]=1
 *   num= 5: sum=9  rem=4   count += rems[4]=1 -> 1    rems[4]=2    new: [5]
 *   num= 0: sum=9  rem=4   count += rems[4]=2 -> 3    rems[4]=3    new: [0], [5,0]
 *   num=-2: sum=7  rem=2   count += rems[2]=0 -> 3    rems[2]=1
 *   num=-3: sum=4  rem=4   count += rems[4]=3 -> 6    rems[4]=4    new: [-2,-3], [0,-2,-3],
 *                                                                        [5,0,-2,-3]
 *   num= 1: sum=5  rem=0   count += rems[0]=1 -> 7    rems[0]=2    new: the whole array
 *   return 7
 *
 * COMPLEXITY
 *   Time : O(n + k) - one pass over nums with O(1) work per number, plus allocating rems
 *   Space: O(k) - the rems array
 *
 * GOTCHAS
 *   - Negative sums: Java's % keeps the sign of the dividend. Without the "+ k, % k" shift a
 *     negative sum would index rems[-1] and throw. [-1,4,1] with k = 5 -> 1: the prefix
 *     remainders are 4, 3, 4, and the two 4s pair up to give the subarray [4,1].
 *   - rems[0] = 1 is the empty prefix. Without it, subarrays that start at index 0 and are
 *     divisible by k would be missed.
 *   - rems needs k slots, not nums.length: a remainder can be as large as k - 1 even for a
 *     short array ([5] with k = 9 uses slot 5).
 *   - Pairs of positions are counted, so [0,0,0] with k = 2 gives 6, not 1.
 *   - int is enough: at most n(n+1)/2 = 450,015,000 subarrays, and |sum| <= 3 * 10^8.
 *
 * STATUS
 *   Correct (checked against an all-subarrays reference on random arrays with negatives).
 *   Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 523 Continuous Subarray Sum - the same remainder trick, asking only whether a
 *     subarray of length >= 2 exists (solved in this folder).
 *   - LC 560 Subarray Sum Equals K - prefix sums counted in a map, for an exact sum k.
 *
 * SEE ALSO
 *   src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC974SubarraySumsDivisibleByK.java
 *       - same idea, but its counter array has nums.length slots, not k (throws if k > length)
 * </pre>
 */
public class LC974SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        // Example 1 - 7 subarrays have a sum divisible by 5. Expected output: 7
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;
        System.out.println(subarraysDivByK(nums, k));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Counts the non-empty subarrays whose sum is divisible by k = the pairs of prefix sums
    // with the same remainder mod k. O(n + k).
    public static int subarraysDivByK(int[] nums, int k) {
        // rems[r] = how many prefix sums so far have remainder r; the empty prefix has remainder 0
        int[] rems = new int[k];
        rems[0] = 1;
        int count = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            // remainder in 0..k-1 (a negative sum would give a negative %, so shift it back)
            int rem = ((sum % k) + k) % k;
            // every earlier prefix with this remainder closes one divisible subarray here
            count += rems[rem];
            rems[rem]++;
        }
        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 974. Subarray Sums Divisible by K)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (all zeros, negative prefix sums, k larger than
    // the array, the largest possible answer for n = 30000); expected values from the statement
    // or checked against an all-subarrays brute force.
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: 7 subarrays", 7, () -> count(5, 4, 5, 0, -2, -3, 1));
        check("example 2: k larger than the array", 0, () -> count(9, 5));
        // edge: every subarray of [0,0,0] sums to 0 -> 3 + 2 + 1 = 6
        check("all zeros", 6, () -> count(2, 0, 0, 0));
        // edge: negative prefix sum -1 needs the remainder shifted to 4 (subarray [4,1])
        check("negative prefix sums", 1, () -> count(5, -1, 4, 1));
        // edge: k (7) larger than the array length (2); only [7] qualifies
        check("k larger than the array, one hit", 1, () -> count(7, 7, 1));
        // edge: n = 30000 zeros -> every one of the n(n+1)/2 = 450,015,000 subarrays counts
        int[] zeros = new int[30000];
        check("30000 zeros (largest answer)", 450015000,
                () -> LC974SubarraySumsDivisibleByK.subarraysDivByK(zeros, 10000));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // k first, then the array elements
    private static int count(int k, int... nums) {
        return LC974SubarraySumsDivisibleByK.subarraysDivByK(nums, k);
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

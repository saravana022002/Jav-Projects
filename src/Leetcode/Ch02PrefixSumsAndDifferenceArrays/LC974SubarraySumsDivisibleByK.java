package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.HashMap;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 974. Subarray Sums Divisible by K   [Medium]
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * Topics: Array, Hash Table, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #6
 *       also: ch. 3 Hashing as a search-to-lookup transform (#8)
 *
 * PROBLEM
 *   Count the non-empty subarrays (contiguous parts) of nums whose sum is divisible by k.
 *   Subarrays are counted by position, so equal-looking ones at different places count
 *   separately. The numbers may be negative.
 *
 * EXAMPLES
 *   nums = [4,5,0,-2,-3,1], k = 5  ->  7   [4,5,0,-2,-3,1] (sum 5), [5], [5,0],
 *                                          [5,0,-2,-3] (sum 0), [0], [0,-2,-3], [-2,-3]
 *   nums = [5], k = 9              ->  0   5 is not divisible by 9
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 3 * 10^4,   -10^4 <= nums[i] <= 10^4,   2 <= k <= 10^4
 *
 * INTUITION
 *   Let p[i] be the sum of the first i numbers (p[0] = 0). The subarray nums[l..r] sums to
 *   p[r+1] - p[l], which is divisible by k exactly when both prefix sums leave the same
 *   remainder mod k. So the answer is the number of PAIRS of prefix sums (the empty prefix
 *   included) with equal remainders. Count them on the fly: map[r] = how many prefix sums so
 *   far had remainder r. A new prefix with remainder r pairs up with every one of them, so
 *   add map[r] to the answer and then increment it.
 *   Remainders are only 0 .. k-1, so a plain array of k counters is enough as the "map".
 *
 * APPROACH (as implemented below)
 *   1. map[r] = how many prefix sums so far have remainder r; map[0] = 1 stands for the empty
 *      prefix. count = the running prefix sum, ans = the number of subarrays found.
 *      (The array is created with nums.length slots, but it needs k - see STATUS.)
 *   2. For each num: count += num.
 *   3. remainder = ((count % k) + k) % k. Java's % is negative for a negative count, and the
 *      "+ k, % k" brings the result back into 0 .. k-1.
 *   4. ans += map[remainder] (every earlier prefix with this remainder closes one divisible
 *      subarray ending here), then map[remainder]++.
 *   5. Return ans.
 *
 * DRY RUN   nums = [4,5,0,-2,-3,1], k = 5 (the input main() runs; map[0] = 1 to start)
 *   num= 4  count=4  remainder 4   ans += map[4]=0 -> 0   map[4]=1
 *   num= 5  count=9  remainder 4   ans += map[4]=1 -> 1   map[4]=2   new: [5]
 *   num= 0  count=9  remainder 4   ans += map[4]=2 -> 3   map[4]=3   new: [0], [5,0]
 *   num=-2  count=7  remainder 2   ans += map[2]=0 -> 3   map[2]=1
 *   num=-3  count=4  remainder 4   ans += map[4]=3 -> 6   map[4]=4
 *                                  new: [-2,-3], [0,-2,-3], [5,0,-2,-3]
 *   num= 1  count=5  remainder 0   ans += map[0]=1 -> 7   map[0]=2   new: the whole array
 *   return 7. Here nums.length = 6 >= k = 5, so the undersized array happens to be big enough.
 *
 * COMPLEXITY
 *   Time : O(n) - one pass, O(1) work per number
 *   Space: O(n) as written (nums.length counters); O(k) once the array is sized k
 *
 * GOTCHAS
 *   - Negative numbers: the "+ k, % k" shift is essential. Without it count % k could be
 *     negative and map[remainder] would throw. [-3,-3,5] with k = 3 -> 3.
 *   - map[0] = 1 is the empty prefix. Without it, subarrays that start at index 0 and are
 *     divisible by k would be missed.
 *   - Pairs of positions are counted, so [0,0,0] with k = 2 gives 6, not 1.
 *   - int is enough: at most n(n+1)/2 = 450,015,000 subarrays, and |sum| <= 3 * 10^8.
 *   - The import java.util.HashMap is unused.
 *
 * STATUS
 *   Known issue: the counter array has nums.length slots, but remainders go up to k - 1. As
 *   soon as a remainder is >= nums.length the method throws ArrayIndexOutOfBoundsException,
 *   which can happen whenever k > nums.length. Official Example 2, nums = [5], k = 9, throws
 *   (expected 0); so do [7] with k = 2 (expected 0) and [-1,4,1] with k = 5 (expected 1).
 *   It is correct whenever k <= nums.length. The fix would be "new int[k]".
 *   The code is left unchanged on purpose.
 *
 * SEE ALSO
 *   src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC974SubarraySumsDivisibleByK.java
 *     - the same algorithm with the counter array sized k (so no exception), written up from
 *       the hashing chapter's side
 *
 * RELATED
 *   - LC 523 Continuous Subarray Sum (same chapter) - the same remainder trick, asking only
 *     whether a subarray of length >= 2 exists.
 *   - LC 560 Subarray Sum Equals K (solved in src/Practice/PrefixSum) - counting pairs of
 *     prefix sums whose difference is exactly k.
 * </pre>
 */
public class LC974SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        // Example 1: 7 subarrays have a sum divisible by 5 - prints 7
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;
        System.out.println(subArraysDivByK(nums, k));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }



    // p[r] - p[l] = t ; where t is divisible by k
    // in Modulo arithmetic it is, p[r] - p[l] =_= 0 (mod K)
    // (Mod k)p[r] = p[l] (Mod k)
    // Counts the pairs of prefix sums with the same remainder mod k. O(n)
    public static int subArraysDivByK(int[] nums, int k) {
        // map[r] = how many prefix sums so far have remainder r. BUG: needs k slots, not length
        int[] map = new int[nums.length];
        // the empty prefix has sum 0, remainder 0
        map[0] = 1;
        // running prefix sum
        int count = 0;
        int ans = 0;
        for (int num : nums) {
            count += num;
            // remainder in 0..k-1 (a negative count would give a negative %, so shift it back)
            int remainder = ((count % k) + k) % k;
            // each earlier prefix with this remainder closes one divisible subarray ending here
            ans += map[remainder];
            map[remainder]++;
        }
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 974. Subarray Sums Divisible by K)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases. Expected values come from the statement,
    // from the reasoning noted per case, and (checked offline) from a brute force that tests the
    // sum of every subarray. An exception thrown by the solution counts as a FAIL.
    //   Expected result: 4/6 passed
    //   The 2 failures are the known issue described in the STATUS of
    //   LC974SubarraySumsDivisibleByK.java (the counter array is too short when k > length).
    // ==========================================================================
    private static void runTests() {
        // official examples
        int[] example1 = {4, 5, 0, -2, -3, 1};
        check("example 1: [4,5,0,-2,-3,1], k=5", 7,
                () -> LC974SubarraySumsDivisibleByK.subArraysDivByK(example1, 5));
        // example 2 has k = 9 > length 1: known issue, the solution throws
        // ArrayIndexOutOfBoundsException instead of returning 0
        check("example 2: [5], k=9", 0,
                () -> LC974SubarraySumsDivisibleByK.subArraysDivByK(new int[]{5}, 9));
        // edge: negative prefix sums (-3, -6, -1) - the remainder must be shifted into 0..k-1
        check("negative prefix sums: [-3,-3,5], k=3", 3,
                () -> LC974SubarraySumsDivisibleByK.subArraysDivByK(new int[]{-3, -3, 5}, 3));
        // edge: negative number with k > length (same known issue): expected 1, the subarray [4,1]
        check("negatives, k > length: [-1,4,1], k=5", 1,
                () -> LC974SubarraySumsDivisibleByK.subArraysDivByK(new int[]{-1, 4, 1}, 5));
        // edge: every subarray qualifies - pairs of positions count: 3 * 4 / 2 = 6, not 1
        check("[0,0,0], k=2", 6,
                () -> LC974SubarraySumsDivisibleByK.subArraysDivByK(new int[]{0, 0, 0}, 2));
        // edge: the largest input, 30000 zeros: 30000 * 30001 / 2 = 450015000 (fits an int)
        int[] zeros = new int[30000];
        check("30000 zeros, k=2", 450015000,
                () -> LC974SubarraySumsDivisibleByK.subArraysDivByK(zeros, 2));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

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

package Practice.BinarySearch;

// ============================================================================
// LC 2528. Maximize the Minimum Powered City   [Hard]
// https://leetcode.com/problems/maximize-the-minimum-powered-city/
// Topics: Array, Binary Search, Greedy, Queue, Sliding Window, Prefix Sum
// ============================================================================
//
// PROBLEM
//   n cities lie in a row and stations[i] power stations stand in city i. A station in
//   city i supplies every city j with |i - j| <= r (its own city included). The POWER of
//   a city is the number of stations that reach it. You may build k more stations, in
//   any cities (even all in one), with the same range r. Build them as cleverly as you
//   can and return the largest possible value of the WEAKEST city's power.
//
// EXAMPLES
//   stations = [1,2,4,5,0], r = 1, k = 2  ->  5
//       both new stations in city 1: stations [1,4,4,5,0], powers [5,9,13,9,5], minimum 5
//   stations = [4,4,4,4], r = 0, k = 3  ->  4
//       with r = 0 a city only has its own stations, and 3 new ones cannot lift all four
//       cities above 4
//
// CONSTRAINTS
//   n == stations.length,   1 <= n <= 10^5,   0 <= stations[i] <= 10^5,
//   0 <= r <= n - 1,   0 <= k <= 10^9
//
// INTUITION   (the plan for the full solution - only step 1 is coded, see STATUS)
//   1. Range add with a difference array (line sweep). A station in city i adds its count
//      to every city in i-r .. i+r. Instead of touching each of them, add at the start
//      of the range and subtract one past its end; a running sum over cnt then gives
//      every city's power in O(n).
//   2. "Maximize the minimum" is a binary search on the answer X: can every city reach
//      power >= X with at most k new stations? If X works, every smaller value works, so
//      the answers go yes, yes, ..., no, no. X lies between the current weakest power and
//      that power + k.
//   3. The yes / no check is greedy. Sweep the cities left to right; when a city is below
//      X it needs (X - its power) more stations, and the best place to build them is the
//      farthest city that still reaches it, city i + r, because that helps the most
//      cities still to come. Add up the stations used (answer "no" if it exceeds k) and
//      keep a running sum / second difference array of the extra power already handed out.
//
// APPROACH (as implemented below)
//   1. cnt = long[n + 1], the difference array; the spare slot absorbs a range that runs
//      to the last city.
//   2. For every city i: left = max(0, i - r), right = min(n, i + r + 1) (one past the last
//      city its stations reach); cnt[left] += stations[i]; cnt[right] -= stations[i].
//   3. NOT WRITTEN: the running sum of cnt, the binary search over X and the greedy check.
//   4. return 0 - a placeholder, so maxPower always returns 0.
//
// DRY RUN   stations = [1,2,4,5,0], r = 1   (what the code computes, then what is missing)
//   i=0: left=0 right=2   cnt[0] += 1   cnt[2] -= 1
//   i=1: left=0 right=3   cnt[0] += 2   cnt[3] -= 2
//   i=2: left=1 right=4   cnt[1] += 4   cnt[4] -= 4
//   i=3: left=2 right=5   cnt[2] += 5   cnt[5] -= 5
//   i=4: left=3 right=5   cnt[3] += 0   cnt[5] -= 0
//   cnt = [3,4,4,-2,-4,-5]; its running sum gives the powers [3,7,11,9,5], weakest 3.
//   The code stops here and returns 0. The finished search would try X in [3, 3 + k]:
//   X = 5 needs 2 new stations (city 0 is short by 2) - fine; X = 6 needs 3 - too many.
//   So the answer is 5.
//
// STATUS
//   Unfinished: maxPower() only fills the difference array cnt and then returns 0. The
//   running sum (the real city powers), the binary search and the greedy placement of the
//   k new stations are missing. For the example in main() the correct answer is 5; this
//   code returns 0 (and main() does not print the result anyway).
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   As written: Time O(n) - one loop over the cities; Space O(n) - the cnt array.
//   Finished solution: Time O(n log k) - the search has at most k + 1 candidate values
//   (about 30 rounds), each check is one O(n) sweep; Space O(n).
//
// GOTCHAS
//   - Powers can reach 10^5 stations * 10^5 cities = 10^10, which overflows int, hence
//     the long array and the long return type.
//   - right is one PAST the last covered city, so the range is half-open [left, right);
//     that is why cnt has n + 1 slots.
//
// RELATED
//   - LC 2071 Maximum Number of Tasks You Can Assign - binary search on the answer with a
//     greedy feasibility check, the same frame as step 2 and 3 above.
//   - LC 2616 Minimize the Maximum Difference of Pairs (in this repo) - another "binary
//     search the answer, greedy check" problem.
// ============================================================================
public class LC2528MaximizeTheMinimumPoweredCity {

    public static void main(String[] args) {
        // LC example 1: the correct answer is 5, but maxPower is unfinished and returns 0;
        // the result is not printed either, so there is no output (see STATUS)
        int[] stations = {1,2,4,5,0};
        int r = 1;
        int k = 2;
        maxPower(stations, r, k);
    }
    // UNFINISHED - should return the largest possible minimum city power (see STATUS).
    public static long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        // difference array over the cities: powers come from its running sum
        long[] cnt = new long[n + 1];

        for (int i = 0; i < n; i++) {
            // the stations in city i reach cities left .. right - 1
            int left = Math.max(0, i - r);
            int right = Math.min(n, i + r + 1);
            cnt[left] += stations[i];
            cnt[right] -= stations[i];
        }
        // placeholder: the running sum, binary search and greedy check are not written yet
        return 0;
    }
}

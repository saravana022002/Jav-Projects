package Practice.Math;

// ============================================================================
// LC 2929. Distribute Candies Among Children II   [Medium]
// https://leetcode.com/problems/distribute-candies-among-children-ii/
// Topics: Math, Combinatorics, Enumeration
// ============================================================================
//
// PROBLEM
//   Share n candies among 3 children so that every candy is given out and no child
//   gets more than limit (a child may get 0). Order matters: (1,2,2) and (2,1,2) are
//   different. Return how many distributions are possible.
//
// EXAMPLES
//   n = 5, limit = 2  ->  3      (1,2,2), (2,1,2), (2,2,1)
//   n = 3, limit = 3  ->  10     the cap never bites, so every split of 3 counts
//
// CONSTRAINTS
//   1 <= n <= 10^6,   1 <= limit <= 10^6
//
// INTUITION
//   No cap: splitting x candies among 3 children = placing 2 dividers among x candies
//   ("stars and bars"): C(x + 2, 2) = (x + 2)(x + 1) / 2 ways.
//   With the cap, subtract the bad splits. "Child A gets more than limit": hand A
//   limit + 1 candies first, then split the rest freely -> C(n - (limit+1) + 2, 2).
//   Do that for each of the 3 children. Splits where TWO children are over were
//   subtracted twice, so add them back (3 pairs), and remove the all-three case once
//   more. That is inclusion-exclusion.
//
// APPROACHES IN THIS FILE
//   A. distributeCandies1() - try every (i, j) for children 1 and 2, child 3 gets the
//      rest - O(limit^2), only fast enough for small inputs (part I, LC 2928)
//   B. distributeCandies()  - stars and bars + inclusion-exclusion - O(1)
//      <- main() runs this one
//
// APPROACH (as implemented below, method B)
//   1. combination(x) = (x + 2)(x + 1) / 2 = uncapped splits of x candies, 0 if x < 0.
//   2. answer = combination(n)
//             - 3 * combination(n - 1 * (limit + 1))   one child over the cap
//             + 3 * combination(n - 2 * (limit + 1))   two children over the cap
//             -     combination(n - 3 * (limit + 1))   all three over the cap
//
// DRY RUN
//   n = 3, limit = 3 (main): combination(3) = 5 * 4 / 2 = 10, and 3 - 4 < 0 so every
//                            correction term is 0 -> 10
//   n = 5, limit = 2       : limit + 1 = 3
//     combination(5)  = 7 * 6 / 2 = 21
//     combination(2)  = 4 * 3 / 2 = 6     -> - 3 * 6 = -18
//     combination(-1) = combination(-4) = 0
//     answer = 21 - 18 + 0 - 0 = 3
//
// COMPLEXITY
//   Time : O(1) for distributeCandies - four closed-form terms
//          (distributeCandies1: O(limit^2), two nested loops over 0..limit)
//   Space: O(1)
//
// GOTCHAS
//   - combination must return 0 for a negative argument: if n < limit + 1 no child can
//     be over the cap at all.
//   - Needs long: n = limit = 10^6 gives 500001500001. The (long) cast sits on (n + 2)
//     so the multiplication itself happens in long.
//   - distributeCandies1 counts in an int and runs (limit + 1)^2 iterations - fine for
//     part I, far too slow (and would overflow) at 10^6.
//
// RELATED
//   - LC 2928 Distribute Candies Among Children I - the same question with small
//     inputs, where the double loop of distributeCandies1 is enough.
// ============================================================================
public class LC2929DistributeCandiesAmongChildrenII {
    public static void main(String[] args) {
        // Example 2 - the cap is never reached. Expected output: 10
        int n = 3;
        int limit = 3;
        System.out.println(distributeCandies(n, limit));
    }
    // Approach A (brute force, part I): fix child 1 (i) and child 2 (j), child 3 takes the
    // rest; count it when 0 <= k <= limit. O(limit^2). Not called by main().
    public static long distributeCandies1(int n, int limit) {
        int cnt = 0;
        for (int i = 0; i <= limit; i++) {
            for (int j = 0; j <= limit; j++) {
                int k = n - i - j;
                if(k <= limit && k >= 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // Approach B: all splits - (1 child over) + (2 children over) - (3 children over),
    // where "over" = forced to take limit + 1 candies up front. O(1).
    public static long distributeCandies(int n, int limit) {
        return combination(n) - 3 * combination(n - (limit + 1)) + 3 * combination(n - 2 * (limit + 1)) - combination(n - 3 * (limit + 1));
    }

    // stars and bars: ways to split n candies among 3 children with no cap = C(n + 2, 2)
    private static long combination(int n) {
        // fewer candies than the forced limit + 1: no such distribution
        if (n < 0) return 0;
        return (long)(n + 2) * (n + 1) / 2;
    }

}

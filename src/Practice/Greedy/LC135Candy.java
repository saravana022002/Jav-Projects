package Practice.Greedy;

import java.util.Arrays;

// ============================================================================
// LC 135. Candy   [Hard]
// https://leetcode.com/problems/candy/
// Topics: Array, Greedy
// ============================================================================
//
// PROBLEM
//   n children stand in a row and ratings[i] is child i's rating. Hand out candies so that
//     - every child gets at least 1 candy, and
//     - a child rated higher than a neighbour gets more candies than that neighbour.
//   Return the smallest total number of candies that satisfies both rules.
//
// EXAMPLES
//   ratings = [1,0,2]  ->  5     candies [2,1,2]
//   ratings = [1,2,2]  ->  4     candies [1,2,1] - the last child ties with the middle
//                                one, and a tie needs no extra candy
//
// CONSTRAINTS
//   1 <= n <= 5 * 10^4,   0 <= ratings[i] <= 5 * 10^4
//
// INTUITION
//   Each child has up to two neighbours, and each side sets its own lower bound:
//     left bound : if I beat my LEFT neighbour, I need (its candies + 1)
//     right bound: if I beat my RIGHT neighbour, I need (its candies + 1)
//   One sweep in each direction computes each bound. A child must satisfy both, so it
//   takes the larger of the two - and nothing more, which keeps the total minimal.
//
// APPROACH (as implemented below)
//   1. Give everyone 1 candy (Arrays.fill).
//   2. Sweep left -> right: if ratings[i] > ratings[i-1], candies[i] = candies[i-1] + 1.
//   3. Sweep right -> left: if ratings[i] > ratings[i+1],
//      candies[i] = max(candies[i], candies[i+1] + 1)   <- max keeps what step 2 gave.
//   4. Add up the array.
//
// DRY RUN   ratings = [1,0,2]
//   start           candies = [1,1,1]
//   left -> right   i=2: 2 > 0  -> candies[2] = 1 + 1 = 2        => [1,1,2]
//   right -> left   i=0: 1 > 0  -> candies[0] = max(1, 1 + 1) = 2 => [2,1,2]
//   sum = 2 + 1 + 2 = 5
//
// COMPLEXITY
//   Time : O(n) - two linear sweeps plus one summing loop
//   Space: O(n) - the candies array
//
// GOTCHAS
//   - Equal neighbours impose no rule, so a tie does not get +1 (example 2).
//   - Pass 2 must use max(). For ratings [1,2,3,1], pass 1 gives the "3" child 3 candies;
//     plain assignment in pass 2 would drop it to 2 and break the rule with its left side.
//
// RELATED
//   - LC 42 Trapping Rain Water - same idea: compute a bound from the left and a bound
//     from the right, then combine them per index.
// ============================================================================
public class LC135Candy {
    public static void main(String[] args) {
        // Example 2 - the tie at the end needs no extra candy. Expected output: 4
        int[] ratings = {1,2,2};
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        // every child starts with the mandatory 1 candy
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        // pass 1 (left -> right): beat the LEFT neighbour -> one more than it
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]){
                candies[i] = candies[i - 1] + 1;
            }
        }
        // pass 2 (right -> left): beat the RIGHT neighbour -> one more than it,
        // but keep pass 1's value when that is already larger
        for (int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]){
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        // the answer is the total number of candies handed out
        int sum = 0;
        for (int candy : candies) {
            sum = sum + candy;
        }
        return sum;
    }
}

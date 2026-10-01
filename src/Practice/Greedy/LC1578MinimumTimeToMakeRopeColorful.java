package Practice.Greedy;

// ============================================================================
// LC 1578. Minimum Time to Make Rope Colorful   [Medium]
// https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
// Topics: Array, String, Dynamic Programming, Greedy
// ============================================================================
//
// PROBLEM
//   A rope holds n balloons; colors[i] is the colour (a lowercase letter) of balloon i and
//   neededTime[i] is how many seconds it takes to remove that balloon. Bob removes some
//   balloons so that no two neighbouring balloons on the rope share a colour. Return the
//   smallest total time he needs.
//
// EXAMPLES
//   colors = "abaac", neededTime = [1,2,3,4,5]  ->  3
//        only the two a's at indices 2 and 3 clash; remove the cheaper one (3 seconds)
//   colors = "abc", neededTime = [1,2,3]  ->  0
//        no two neighbours match, so nothing has to go
//   colors = "aabaa", neededTime = [1,2,3,4,1]  ->  2
//        each "aa" pair loses its cheaper balloon: 1 + 1
//
// CONSTRAINTS
//   n == colors.length == neededTime.length,   1 <= n <= 10^5,   1 <= neededTime[i] <= 10^4
//   colors contains only lowercase English letters
//
// INTUITION
//   Only a block of equal colours sitting side by side is a problem. A block of k balloons
//   has to shrink to ONE survivor (two left next to each other would clash, and removing the
//   whole block never pays - it costs more and could glue two equal neighbours together).
//   Keep the balloon that is most expensive to remove and remove the rest, so a block costs
//   (sum of its times) - (its largest time). The blocks do not interact, so the answer is
//   the total over all blocks.
//
// APPROACH (as implemented below)
//   1. arr = colors as a char[]; ans = 0.
//   2. The outer loop stands at the first balloon of a block: max = count = neededTime[i]
//      (max = largest time in the block, count = sum of the times in the block).
//   3. While the next balloon has the same colour, fold it into max and count, set
//      isContinuity = true and advance i - so i ends on the LAST balloon of the block.
//   4. If the block had at least two balloons, add count - max to ans and reset the flag.
//   5. The for loop's i++ steps to the first balloon of the next block. Return ans.
//
// DRY RUN   colors = "abaac", neededTime = [1,2,3,4,5]   (main() prints 3)
//   i=0 'a': next is 'b'                      -> block of one, cost 0
//   i=1 'b': next is 'a'                      -> block of one, cost 0
//   i=2 'a': max = count = 3; next is 'a'     -> max = 4, count = 7, i = 3
//            next is 'c', block ends          -> ans += 7 - 4 = 3
//   i=4: the loop condition 4 < 4 fails       -> return 3
//
// COMPLEXITY
//   Time : O(n) - the inner while moves the same i as the outer loop, so every balloon is
//          looked at once.
//   Space: O(n) - only because toCharArray() copies the string; charAt would need O(1).
//
// GOTCHAS
//   - i is advanced inside the while loop on purpose: the outer i++ then skips past the block.
//   - The loop stops at arr.length - 1. A lone last balloon forms a block of one and costs
//     nothing, so skipping it is fine; a last balloon that ends a longer block is handled
//     inside the while loop.
//   - isContinuity is only a guard for blocks of two or more; for a single balloon
//     count - max would be 0 anyway.
// ============================================================================
public class LC1578MinimumTimeToMakeRopeColorful {

    public static void main(String[] args) {
        // Example 1: the two adjacent 'a's (times 3 and 4) clash; removing the 3 costs 3.
        // Expected output: 3
        String colors = "abaac";
        int[] neededTime = {1,2,3,4,5};
        System.out.println(minCost(colors, neededTime));
    }
    // Returns the least total removal time so that no two neighbouring balloons match.
    public static int minCost(String colors, int[] neededTime) {
        char[] arr = colors.toCharArray();
        // max = largest time in the current block, count = sum of the times in it
        int max;
        int count = 0;
        int ans = 0;
        boolean isContinuity = false;
        for (int i = 0; i < arr.length - 1; i++) {
            max = neededTime[i];
            count = neededTime[i];
            // extend the block while the next balloon has the same colour
            while (i + 1 < arr.length && arr[i] == arr[i + 1] ){
                max = Math.max(max, neededTime[i + 1]);
                count += neededTime[i + 1];
                isContinuity = true;
                i++;
            }
            // keep the most expensive balloon of the block, pay for removing all the others
            if(isContinuity) {
                count -= max;
                ans += count;
                isContinuity = false;
            }
        }
        return ans;
    }
}

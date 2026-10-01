package Practice.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// ============================================================================
// LC 60. Permutation Sequence   [Hard]
// https://leetcode.com/problems/permutation-sequence/
// Topics: Math, Recursion
// ============================================================================
//
// PROBLEM
//   List all n! orderings of the digits 1..n in sorted (dictionary) order and return the
//   k-th one (1-based) as a string - without generating the whole list.
//
// EXAMPLES
//   n = 3, k = 3  ->  "213"     order: 123, 132, 213, 231, 312, 321
//   n = 4, k = 9  ->  "2314"
//   n = 3, k = 1  ->  "123"
//
// CONSTRAINTS
//   1 <= n <= 9,   1 <= k <= n!
//
// INTUITION
//   Group the permutations by their first digit: each group has (n-1)! members. So the
//   first digit is decided by which group k falls into: index (k-1) / (n-1)!. Take that
//   digit away, keep only k's position inside its group ((k-1) % (n-1)!), and repeat
//   with the n-1 digits that are left. (This is the "factorial number system".)
//
// APPROACH (as implemented below)
//   1. k-- (switch to a 0-based rank), nums = [1, 2, ..., n].
//   2. While more than one digit is left:
//        divisor = (size - 1)!,  index = k / divisor  -> append nums[index]
//        k = k % divisor, and remove that digit from nums
//   3. Append the last digit and join everything into a string.
//
// DRY RUN   n = 4, k = 17 (the input main() uses) -> k = 16, nums = [1,2,3,4]
//   divisor 3! = 6: index 16 / 6 = 2 -> "3",   k = 16 % 6 = 4,   nums = [1,2,4]
//   divisor 2! = 2: index 4 / 2 = 2  -> "4",   k = 0,            nums = [1,2]
//   divisor 1! = 1: index 0          -> "1",   k = 0,            nums = [2]
//   one digit left -> "2"                                        answer "3412"
//
// COMPLEXITY
//   Time : O(n^2) - n rounds, each with an O(n) list removal and an O(n) factorial
//   Space: O(n)   - the two lists of digits
//
// GOTCHAS
//   - Use k - 1: with the 1-based k, k = n! would pick index n, which does not exist.
//   - nums.remove(nums.get(index)) removes by VALUE (an Integer), not by position.
//   - An int factorial is enough because n <= 9 (9! = 362880).
//
// RELATED
//   - LC 31 Next Permutation - steps to the next permutation instead of jumping to one.
//   - LC 46 Permutations - generates all of them (what this problem avoids).
// ============================================================================
public class LC60PermutationSequence {
    public static void main(String[] args) {
        // 17th permutation of 1..4 -> expected output: 3412
        int K = 17;
        int N = 4;
        System.out.println(getPermutation(N, K));
    }
    public static String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        // 0-based rank makes the group arithmetic exact
        k--;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        List<Integer> ans = new ArrayList<>();
        int divisor = 0;
        int index = 0;
        while(true){
            if(nums.size() == 1) {
                // only one digit is left - it must come last
                ans.add(nums.get(0));
                break;
            }
            // each remaining digit leads a block of (size - 1)! permutations
            divisor = factorial(nums.size() - 1);
            // the block that holds rank k decides the next digit
            index = k / divisor;
            ans.add(nums.get(index));
            // rank inside that block
            k = k % divisor;
            // Integer argument -> remove(Object): drops that digit value
            nums.remove(nums.get(index));
        }
        // join the digits into the answer string
        String result = ans.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));

        return result;
    }

    // n! with a simple loop (n <= 9 here, so int is enough)
    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

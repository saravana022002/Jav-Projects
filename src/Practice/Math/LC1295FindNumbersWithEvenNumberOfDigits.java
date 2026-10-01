package Practice.Math;

// ============================================================================
// LC 1295. Find Numbers with Even Number of Digits   [Easy]
// https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
// Topics: Array, Math
// ============================================================================
//
// PROBLEM
//   You get an array of positive integers. Count how many of them are written with an
//   even number of digits (2 digits, 4 digits, ...) and return that count.
//
// EXAMPLES
//   nums = [12,345,2,6,7896]   ->  2     only 12 (2 digits) and 7896 (4 digits) qualify
//   nums = [555,901,482,1771]  ->  1     only 1771 has 4 digits, the rest have 3
//
// CONSTRAINTS
//   1 <= nums.length <= 500,   1 <= nums[i] <= 10^5
//
// INTUITION
//   Integer division by 10 drops the last digit. So the number of times you can divide
//   by 10 before the value reaches 0 is exactly its digit count. Check that count's
//   parity for every number and tally the even ones.
//
// APPROACH (as implemented below)
//   1. findNumbers walks nums and increments count whenever isEven(num) is true.
//   2. isEven(num) divides num by 10 until it becomes 0, counting the steps
//      (= digits), and returns count % 2 == 0.
//
// DRY RUN   nums = [12,345,2,6,7896]
//   12   -> 1 -> 0          2 digits  even  count = 1
//   345  -> 34 -> 3 -> 0    3 digits  odd
//   2    -> 0               1 digit   odd
//   6    -> 0               1 digit   odd
//   7896 -> 789 -> 78 -> 7 -> 0   4 digits  even  count = 2
//   answer = 2
//
// COMPLEXITY
//   Time : O(n * d) - d = digits per number, at most 6 here (10^5 has 6 digits)
//   Space: O(1) - only counters
//
// GOTCHAS
//   - isEven means "has an even number of DIGITS", not "the value is even": 7897 is odd
//     but has 4 digits, so it counts; 2 is even but has 1 digit, so it does not.
//   - isEven(0) returns true (0 divisions -> 0 digits), but 0 cannot appear here because
//     nums[i] >= 1.
//
// RELATED
//   - LC 9 Palindrome Number - uses the same divide-by-10 loop to count digits.
// ============================================================================
public class LC1295FindNumbersWithEvenNumberOfDigits {
    public static void main(String[] args) {
        // Example 1 - expected output: 2
        int[] nums = {12,345,2,6,7896};
        System.out.println(findNumbers(nums));
    }

    // returns how many numbers in nums have an even digit count
    public static int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums)
            if(isEven(num))
                count++;
        return count;
    }

    // true when num has an even number of digits (not when num itself is even)
    public static boolean isEven(int num){
        int count = 0;
        // each division by 10 removes one digit, so count ends as the digit count
        while (num > 0){
            num = num / 10;
            count++;
        }
        return count % 2 == 0;
    }
}

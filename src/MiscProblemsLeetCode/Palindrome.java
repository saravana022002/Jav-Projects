package MiscProblemsLeetCode;

/**
 * Example 1:
 *
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * Example 2:
 *
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
public class Palindrome {
    public static void main(String[] args) {
        int x = 13425;
        System.out.println(isPalindrome(x));
    }

    public static boolean isPalindrome(int x) {
        int nums = x;
        int noOfDigits = 0;
        float sign = Math.signum(nums);

        if(sign == -1L){
            return false;
        }

        while (nums > 0){
            nums /= 10;
            noOfDigits++;
        }

        double divedInt;
        double value;
        int digit;
        int sum = 0;
        for (int i = 0; i < noOfDigits; i++) {
            divedInt = Math.pow(10, noOfDigits - i - 1);
            value = x / divedInt;
            digit = (int) value % 10;

            sum += digit * (int) Math.pow(10, i);
        }
        return sum == x;
    }
}

package Practice.Strings;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 13. Roman to Integer   [Easy]
// https://leetcode.com/problems/roman-to-integer/
// Topics: Hash Table, Math, String
// ============================================================================
//
// PROBLEM
//   Convert the Roman numeral s to an integer. The symbols are I=1, V=5, X=10, L=50, C=100,
//   D=500, M=1000. They are written from largest to smallest and added up, except for six
//   subtractive pairs where a smaller symbol stands in front of a larger one:
//   IV=4, IX=9, XL=40, XC=90, CD=400, CM=900.
//
// EXAMPLES
//   s = "III"      ->  3       1 + 1 + 1
//   s = "LVIII"    ->  58      50 + 5 + 1 + 1 + 1
//   s = "MCMXCIV"  ->  1994    1000 + 900 (CM) + 90 (XC) + 4 (IV)
//
// CONSTRAINTS
//   1 <= s.length <= 15,   s uses only the characters I V X L C D M,
//   s is guaranteed to be a valid Roman numeral in the range [1, 3999]
//
// INTUITION
//   A symbol is subtracted exactly when it is smaller than the symbol on its RIGHT (that is
//   what the six pairs above say); in every other case it is added. Walking the string from
//   right to left keeps that right neighbour at hand as `prev`, so each decision is a single
//   comparison: curr < prev -> subtract, otherwise add.
//
// APPROACH (as implemented below)
//   1. charMap = symbol -> value for the seven symbols.
//   2. res = 0, prev = 0. Visit s from the last character to the first.
//   3. curr = value of s.charAt(i). If curr < prev, res -= curr; otherwise res += curr.
//   4. prev = curr and move one step left. Return res.
//
// DRY RUN   s = "MCMXCIV"   (visited right to left)
//   i  char  curr  prev  action               res
//   6   V       5     0   add (5 >= 0)           5
//   5   I       1     5   subtract (1 < 5)       4
//   4   C     100     1   add                  104
//   3   X      10   100   subtract              94
//   2   M    1000    10   add                 1094
//   1   C     100  1000   subtract             994
//   0   M    1000   100   add                 1994
//   return 1994
//
// COMPLEXITY
//   Time : O(n) - one pass over s; filling the map is a fixed 7 puts
//   Space: O(1) - the map always holds the same 7 symbols
//
// GOTCHAS
//   - The comparison is a strict `<`, so equal neighbours (II, XX, MM) are added, never
//     subtracted.
//   - The input is trusted to be valid. An unknown character makes charMap.get() return null
//     and the unboxing into int would throw a NullPointerException.
//
// STATUS
//   The method is correct; main() prints 1994.
//
// RELATED
//   - LC 12 Integer to Roman - the reverse conversion.
// ============================================================================
public class LC13RomanToInteger {
    public static void main(String[] args) {

        // 1000 + 900 + 90 + 4. Expected output: 1994
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    // Returns the integer value of the Roman numeral s, scanning right to left
    public static int romanToInt(String s) {
        // symbol -> value (rebuilt on every call, which is harmless for seven entries)
        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('I', 1);
        charMap.put('V', 5);
        charMap.put('X', 10);
        charMap.put('L', 50);
        charMap.put('C', 100);
        charMap.put('D', 500);
        charMap.put('M', 1000);

        int res = 0;
        // value of the symbol to the right of the current one (0 before the first step)
        int prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = charMap.get(s.charAt(i));
            // smaller than its right neighbour -> subtractive pair (IV, IX, XL, XC, CD, CM)
            if(curr < prev){
                res -= curr;
            }else {
                res += curr;
            }
            prev = curr;
        }
        return res;
    }






}

package Practice.Arrays;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 118. Pascal's Triangle   [Easy]
// https://leetcode.com/problems/pascals-triangle/
// Topics: Array, Dynamic Programming
// ============================================================================
//
// PROBLEM
//   Given numRows, return the first numRows rows of Pascal's triangle as a list of lists.
//   The top row is [1]. Every row starts and ends with 1, and each number in between is
//   the sum of the two numbers just above it (same position and one to the left, in the
//   row above). So row i (0-based) holds i + 1 numbers.
//
// EXAMPLES
//   numRows = 5  ->  [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//                    e.g. the 6 in the last row is 3 + 3 from the row above it
//   numRows = 1  ->  [[1]]      only the top row
//
// CONSTRAINTS
//   1 <= numRows <= 30
//
// INTUITION
//   The number at row i, position j is the binomial coefficient C(i, j) ("i choose j").
//   Two neighbours in the SAME row are linked by a simple ratio:
//     C(i, j) = C(i, j-1) * (i - j + 1) / j
//   So a row can be built left to right from its own previous entry, starting from the
//   leading 1, without looking at the row above at all. The division is always exact,
//   because C(i, j-1) * (i - j + 1) equals C(i, j) * j.
//
// APPROACH (as implemented below)
//   1. For each row index i = 0 .. N-1, start a new list `row` holding the leading 1.
//   2. For j = 1 .. i-1: val = row.get(j - 1) * (i - j + 1) / j, append it to row.
//   3. If i > 0, append the trailing 1 (row 0 is just [1]).
//   4. Add row to `array`; after N rows return array.
//
// DRY RUN   N = 5, last row (i = 4)
//   row = [1]
//   j=1: 1 * (4-1+1) / 1 = 4           => [1,4]
//   j=2: 4 * (4-2+1) / 2 = 12 / 2 = 6  => [1,4,6]
//   j=3: 6 * (4-3+1) / 3 = 12 / 3 = 4  => [1,4,6,4]
//   i > 0 -> append 1                  => [1,4,6,4,1]
//   Rows 0..3 come out the same way: [1], [1,1], [1,2,1], [1,3,3,1].
//
// STATUS
//   main() calls generate(5) but does not print the result, and its local `max` is
//   unused. generate() itself is correct for the whole constraint range.
//
// COMPLEXITY
//   Time : O(N^2) - row i costs O(i) steps, and 1 + 2 + ... + N = O(N^2)
//   Space: O(N^2) for the returned triangle (N(N+1)/2 numbers); O(1) extra besides it
//
// GOTCHAS
//   - Multiply first, then divide. For i=5, j=3: 10 * 3 / 3 = 10 is right, but
//     (10 / 3) * 3 = 9 is wrong because 10 / 3 truncates to 3.
//   - int is just big enough: the product row.get(j - 1) * (i - j + 1) peaks at about
//     1.16 * 10^9 on row 29 (numRows = 30), below Integer.MAX_VALUE (about 2.1 * 10^9).
//     From numRows = 31 on it overflows (checked) - use long for bigger inputs.
//   - Row 0 must not get a trailing 1, hence the `if (i > 0)` check.
//
// RELATED
//   - LC 119 Pascal's Triangle II - return a single row; this same-row formula builds it
//     in O(rowIndex) time without computing the rows above.
//   - LC 3463 Check If Digits Are Equal in String After Operations II - the two final
//     digits are sums weighted by a row of this triangle (binomial coefficients mod 10).
//
// SEE ALSO
//   LC118PascalsTrianglePreviousRow.java - builds each cell as the sum of the two cells
//   above it (the classic row-by-row DP)
// ============================================================================
public class LC118PascalsTriangleCombinationFormula {
    public static void main(String[] args) {
        // Example 1: generate(5) returns [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]],
        // but the result is not printed, so the program prints nothing
        int n = 5;
        generate(n);
        // leftover local, not used
        int max = Integer.MIN_VALUE;

    }


    // Returns the first N rows; row i holds C(i,0), C(i,1), ..., C(i,i).
    public static List<List<Integer>> generate(int N) {
        List<List<Integer>> array = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1); // First element is always 1

            // inner cells j = 1 .. i-1 (row i has i + 1 cells, the two ends are 1)
            for (int j = 1; j < i; j++) {
                // Use previous row's values to compute next value
                // (it is the previous value of THIS row: C(i,j) = C(i,j-1) * (i-j+1) / j;
                //  multiply before dividing so the division is exact)
                int val = (row.get(j - 1) * (i - j + 1)) / j;
                row.add(val);
            }

            if (i > 0) {
                row.add(1); // Last element is always 1 for i > 0
            }

            array.add(row);
        }

        return array;
    }
}

package Practice.Matrix;

// ============================================================================
// LC 2125. Number of Laser Beams in a Bank   [Medium]
// https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
// Topics: Array, Math, String, Matrix
// ============================================================================
//
// PROBLEM
//   bank is a floor plan given as m strings of '0' and '1' (one string per row); '1' is a
//   security device. A laser beam joins two devices when they are on different rows r1 < r2
//   and every row strictly between them holds no device at all. Beams do not interfere with
//   each other. Return the total number of beams.
//
// EXAMPLES
//   bank = ["011001","000000","010100","001000"]  ->  8
//       device counts per row are 3,0,2,1: row 0 to row 2 gives 3 * 2 = 6 beams (the empty
//       row 1 is skipped), row 2 to row 3 gives 2 * 1 = 2. Row 0 and row 3 are not joined
//       because row 2 has devices in between.
//   bank = ["000","111","000"]                    ->  0    only one row has devices
//
// CONSTRAINTS
//   m == bank.length,   n == bank[i].length,   1 <= m, n <= 500,   bank[i][j] is '0' or '1'
//
// INTUITION
//   A beam leaves a device and hits the nearest row below that has any device, and every
//   device in the upper row is joined to every device in the lower row. So only the NUMBER
//   of devices per row matters: for two consecutive non-empty rows with a and b devices
//   there are a * b beams. Sum a * b over all such neighbouring pairs, skipping empty rows.
//
// APPROACH (as implemented below)
//   1. arr[j] = number of '1' characters in row j.
//   2. Sweep the rows with j = 0 .. banks.length-2. When arr[j] != 0, let k = arr[j]; the
//      while loop then moves j forward over the empty rows that follow (it stops at the last
//      row at the latest), and m = arr[j + 1] is the device count of the next row that has
//      devices.
//   3. count += k * m; the for loop's j++ then steps to that next non-empty row, so every
//      neighbouring pair is used exactly once. Return count.
//
// DRY RUN   banks = ["011001","000000","010100","001000"],  arr = [3,0,2,1]
//   j=0: arr[0] = 3 -> k = 3; row 1 is empty -> j = 1; m = arr[2] = 2;  count = 6
//   j=2: arr[2] = 2 -> k = 2; no empty row to skip; m = arr[3] = 1;     count = 6 + 2 = 8
//   j=3 is not < banks.length - 1 = 3, so the loop ends                -> returns 8
//
// COMPLEXITY
//   Time : O(m * n) - every cell is read once in step 1; the sweep is O(m)
//   Space: O(m) - the arr array of row counts
//
// GOTCHAS
//   - An empty row is see-through, but a row with even one device blocks the beam: there is
//     no beam between rows 0 and 3 of example 1.
//   - The inner while advances the for loop's own index j. That is intended: the skipped
//     rows are empty, so the outer loop should not visit them again.
//   - The bound j + 1 < banks.length - 1 keeps j + 1 a valid row, so arr[j + 1] never reads
//     past the end; if only empty rows remain, m is 0 and nothing is added.
//   - int is enough: the most beams possible is 499 * 500 * 500 = 124,750,000 (all cells '1').
// ============================================================================
public class LC2125NumberOfLaserBeamsInABank {
    public static void main(String[] args) {
        // Example 1 (the trailing note is the device count of each row). Prints 8
        String[] banks = {"011001","000000","010100","001000"}; // {3,0,2,1}
        System.out.println(numberOfBeams(banks));
    }

    // Returns the total number of beams, from the number of devices in each row.
    public static int numberOfBeams(String[] banks) {
        // step 1: arr[j] = devices ('1') in row j
        int[] arr = new int[banks.length];
        for(int j = 0; j < banks.length; j++){
            int count = 0;
            for (int i = 0; i < banks[j].length(); i++) {
                if(banks[j].charAt(i) == '1'){
                    count++;
                }
            }
            arr[j] = count;
        }

        // step 2: join each non-empty row with the next non-empty row below it
        int k = 0;
        int m = 0;
        int count = 0;
        for(int j = 0; j < banks.length - 1; j++){
            if(arr[j] != 0){
                k = arr[j];
                // skip the empty rows below (this moves the loop index j itself)
                while (j + 1 < banks.length - 1 && arr[j + 1] == 0){
                    j++;
                }
                // devices in the next row (0 if only empty rows were left)
                m = arr[j + 1];
                // every device above is joined to every device below
                count = count + k * m;
            }
        }
        return count;
    }
}

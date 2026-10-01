package Practice.BinarySearch;

// ============================================================================
// LC 69. Sqrt(x)   [Easy]
// https://leetcode.com/problems/sqrtx/
// Topics: Math, Binary Search, Newton's Method
// ============================================================================
//
// PROBLEM
//   Given a non-negative integer x, return its square root rounded DOWN to an integer
//   (the largest s with s * s <= x). No built-in power / exponent function or operator
//   may be used.
//
// EXAMPLES
//   x = 4           ->  2       exactly 2
//   x = 8           ->  2       sqrt(8) = 2.828..., rounded down
//   x = 2147395599  ->  46339   what main() runs: 46340 * 46340 = 2147395600 is one more
//
// CONSTRAINTS
//   0 <= x <= 2^31 - 1
//
// INTUITION
//   Squares grow with the number, so the integers 0, 1, 2, ... split into a block with
//   s * s <= x followed by a block with s * s > x. The answer is the last number of the
//   first block, and a binary search finds such a boundary in O(log x) probes.
//   The answer never exceeds x / 2 for x >= 2, so the search starts in [0, x / 2].
//   Each probe mid looks at its square:
//     mid^2 >= x        -> mid is too big (or the exact root): search the left part
//     mid^2 <  x        -> look one step ahead at (mid+1)^2:
//                            > x   -> mid is the largest root that fits: return mid
//                            == x  -> x is a perfect square: return mid + 1
//                            < x   -> the answer is larger: search the right part
//
// APPROACH (as implemented below)
//   1. l = 0, r = x / 2.
//   2. While l <= r: mid = (l + r) / 2 and square = (long) mid * mid.
//   3. square < x: square1 = (long) (mid + 1) * (mid + 1).
//        square1 > x  -> return mid
//        square1 == x -> return mid + 1
//        otherwise    -> l = mid + 1
//   4. square >= x: r = mid - 1.
//   5. The final return mid is only reached for x = 0: the single probe mid = 0 has
//      0 < 0 false, so r becomes -1 and the loop ends with mid = 0.
//
// DRY RUN   x = 26   (r = 13)
//   l=0 r=13 mid=6:  6^2 = 36 >= 26      -> r = 5
//   l=0 r=5  mid=2:  2^2 = 4 < 26, 3^2 = 9 < 26   -> l = 3
//   l=3 r=5  mid=4:  4^2 = 16 < 26, 5^2 = 25 < 26 -> l = 5
//   l=5 r=5  mid=5:  5^2 = 25 < 26, 6^2 = 36 > 26 -> return 5
//
// COMPLEXITY
//   Time : O(log x) - the range [0, x / 2] halves every round (about 30 rounds at most)
//   Space: O(1)
//
// GOTCHAS
//   - mid * mid overflows int once mid >= 46341 (46341^2 = 2147488281 > 2^31 - 1), so the
//     squares are computed as long. main()'s 2147395599 sits right at that boundary.
//   - x = 1 needs the square1 == x test: r = 0, the only probe is mid = 0, and
//     (0 + 1)^2 == 1 is what returns 1.
//   - x = 0 returns 0 through the final return, after r drops to -1.
//   - (mid + 1) is added as an int before the multiplication; that is safe because mid
//     never exceeds x / 2, about 1.07 * 10^9.
//
// RELATED
//   - LC 367 Valid Perfect Square - the same binary search on squares, only asking
//     whether the root comes out exact.
//   - LC 50 Pow(x, n) (in this repo) - another classic arithmetic function built by hand,
//     there with repeated squaring.
// ============================================================================
public class LC69SqrtX {
    public static void main(String[] args) {
        // prints 46339 (2147395599 is one less than 46340^2, so the root rounds down)
        System.out.println(mySqrt(2147395599));
    }
    // Returns the integer square root of x, rounded down.
    public static int mySqrt(int x) {
        // the root is at most x / 2 (for x >= 2), so search 0 .. x / 2
        int l = 0;
        int r = x / 2;
        int mid = 0;
        long square = 0;
        long square1 = 0;
        while(l <= r){
            mid = (l + r) / 2;
            // long: mid * mid does not fit in an int for large x
            square = (long) mid * mid;
            if(square < x){
                // mid still fits; check whether mid + 1 fits as well
                square1 = (long) (mid + 1) * (mid + 1);
                if(square1 > x){
                    // mid^2 < x < (mid+1)^2: mid is the rounded-down root
                    return mid;
                }else if(square1 == x){
                    // x is exactly (mid+1)^2
                    return mid + 1;
                }
                // even mid + 1 fits: the root is further right
                l = mid + 1;
            }else{
                // mid^2 >= x: too big, look left
                r = mid - 1;
            }
        }
        // only reached for x = 0
        return mid;
    }



}

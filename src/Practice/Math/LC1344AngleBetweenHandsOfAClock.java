package Practice.Math;

// ============================================================================
// LC 1344. Angle Between Hands of a Clock   [Medium]
// https://leetcode.com/problems/angle-between-hands-of-a-clock/
// Topics: Math
// ============================================================================
//
// PROBLEM
//   Given a time as hour and minutes on an analog clock, return the SMALLER of the two
//   angles (in degrees) between the hour hand and the minute hand. A result within
//   10^-5 of the true value is accepted.
//
// EXAMPLES
//   hour = 12, minutes = 30  ->  165     minute hand at 180, hour hand at 15
//   hour = 3,  minutes = 30  ->  75      minute hand at 180, hour hand at 105
//   hour = 3,  minutes = 15  ->  7.5     minute hand at 90,  hour hand at 97.5
//
// CONSTRAINTS
//   1 <= hour <= 12,   0 <= minutes <= 59
//
// INTUITION
//   Measure both hands clockwise from 12:
//     minute hand: 360 degrees / 60 minutes = 6 degrees per minute
//     hour hand  : 360 / 12 = 30 degrees per hour, PLUS it creeps 30 / 60 = 0.5 degrees
//                  per minute as the hour passes (the tricky part)
//   Their difference d is one of the two angles; the other one is 360 - d.
//   Return whichever is smaller (at most 180).
//
// APPROACH (as implemented below)
//   1. hour = hour % 12 so that 12 o'clock counts as 0 degrees.
//   2. ans = minutes * 6 - (hour * 30 + minutes * 0.5)  (minute angle - hour angle,
//      may be negative).
//   3. ans > 0 : return ans if it is < 180, else 360 - ans.
//      ans <= 0: the gap is -ans; return -ans if it is < 180, else 360 + ans
//                (= 360 - gap).
//
// DRY RUN   hour = 8, minutes = 7   (the input main() uses)
//   hour % 12 = 8
//   minute hand = 7 * 6 = 42,   hour hand = 8 * 30 + 7 * 0.5 = 243.5
//   ans = 42 - 243.5 = -201.5   -> negative branch, -201.5 > -180 is false
//   return 360 + (-201.5) = 158.5
//
// COMPLEXITY
//   Time : O(1) - a fixed handful of arithmetic operations
//   Space: O(1)
//
// GOTCHAS
//   - Forgetting the hour hand's 0.5 degrees per minute gives 3:15 -> 0 instead of 7.5.
//   - minutes * 0.5 makes the expression a double, so half degrees are kept.
//   - When the hands overlap (e.g. 12:00) ans is 0, the else branch returns -ans, which
//     prints as -0.0. It equals 0 numerically, so the judge accepts it.
//
// STATUS
//   main() calls angleClock(8, 7) but does not print the result (it is 158.5).
// ============================================================================
public class LC1344AngleBetweenHandsOfAClock {
    public static void main(String[] args) {
        // 8:07 -> expected 158.5 (the return value is not printed)
        int hour = 8;
        int minutes = 7;
        angleClock(hour, minutes);
    }
    // returns the smaller angle in degrees between the two hands at hour:minutes
    public static double angleClock(int hour, int minutes) {
        // 12 o'clock sits at 0 degrees, like 0 o'clock
        hour = hour % 12;
        // minute hand: 6 deg per minute; hour hand: 30 deg per hour + 0.5 deg per minute
        double ans = (minutes * 6) - (hour*30 + minutes * 0.5);
        // the two angles between the hands are |ans| and 360 - |ans|: return the smaller
        if(ans > 0){
            return ans < 180 ? ans : 360 - ans;
        }else {
            // ans <= 0, so the gap is -ans (ans == 0 comes back as -0.0, still equal to 0)
            return ans > -180 ? -ans : 360 + ans;
        }
    }
}

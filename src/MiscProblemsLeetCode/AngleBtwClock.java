package MiscProblemsLeetCode;

public class AngleBtwClock {
    public static void main(String[] args) {
        int hour = 8;
        int minutes = 7;
        angleClock(hour, minutes);
    }
    public static double angleClock(int hour, int minutes) {
        hour = hour % 12;
        double ans = (minutes * 6) - (hour*30 + minutes * 0.5);
        if(ans > 0){
            return ans < 180 ? ans : 360 - ans;
        }else {
            return ans > -180 ? -ans : 360 + ans;
        }
    }
}

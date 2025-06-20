package SDE.DailyChallenge;

public class FindNumberOfEvenDigits {
    public static void main(String[] args) {
        int[] nums = {12,345,2,6,7896};
        System.out.println(findNumbers(nums));
    }

    public static int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums)
            if(isEven(num))
                count++;
        return count;
    }

    public static boolean isEven(int num){
        int count = 0;
        while (num > 0){
            num = num / 10;
            count++;
        }
        return count % 2 == 0;
    }
}

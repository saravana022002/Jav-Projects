package MiscProblemsLeetCode;

public class PlusOne {
    public static void main(String[] args) {
        int[] nums = {4,3,2,1};
        plusOne(nums);
    }
    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        int i = length - 1;
        int lastNumber = digits[length - 1];
        boolean allNine = false;
        while(lastNumber == 9 && i >= 0){
           digits[i] = 0;
           --i;
           if(i == -1) {
               lastNumber = 0;
               allNine = true;
           }else {
               lastNumber = digits[i];
           }
        }
        if(allNine) {
            digits = new int[length + 1];
            digits[0] = 1;
        }else {
            digits[i] = digits[i] + 1;
        }
        return digits;
    }
}

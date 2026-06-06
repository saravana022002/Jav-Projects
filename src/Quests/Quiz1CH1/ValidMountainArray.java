package Quests.Quiz1CH1;

public class ValidMountainArray {
    public static void main(String[] args) {
        int[] arr = {2,0,2};
        System.out.println(validMountainArray(arr));
    }
    public static boolean validMountainArray(int[] arr) {
        boolean isValid = true;
        boolean isIncrease = false;
        boolean isDecrease = false;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i - 1] == arr[i]){
                isValid = false;
            }
            if(arr[i - 1] < arr[i]){
                isIncrease = true;
                if(isDecrease){
                    return false;
                }
            }
            if(arr[i - 1] > arr[i]){
                isDecrease = true;
                if(!isIncrease){
                    return false;
                }
            }
        }
        return isValid && isIncrease && isDecrease;
    }
}

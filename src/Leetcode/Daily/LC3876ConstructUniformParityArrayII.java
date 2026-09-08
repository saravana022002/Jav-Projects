package Leetcode.Daily;

public class LC3876ConstructUniformParityArrayII {
    public static void main(String[] args) {
        int[] nums1 = {1,4,7};
        System.out.println(uniformArray(nums1));
    }
    public static boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            if(nums1[i] <= min){
                min = nums1[i];
            }
        }

        boolean isEven = min % 2 == 0;

        for (int i = 0; i < nums1.length; i++) {
            if(isEven){
                if((nums1[i] % 2) == 0){

                }else if((nums1[i] - min) >= 1 && (nums1[i] - min) % 2 == 0 ){

                }else {
                    return false;
                }
            }else {
                if((nums1[i] % 2) == 1){

                }else if((nums1[i] - min) >= 1 && (nums1[i] - min) % 2 == 1){

                }else {
                    return false;
                }
            }
        }
        return true;
    }
}

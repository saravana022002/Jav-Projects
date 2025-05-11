package SDE.BinarySearch;

public class SingleNonDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,1,2,3,3,4,4,8,8};
//        System.out.println(singleNonDuplicate(nums));
        System.out.println(singleNonDuplicate1(nums));
    }
    public static int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 2;
        int mid;
        while(l <= r){
            mid = (l + r) >> 1;
            if((mid % 2 == 0)){
                if(nums[mid] == nums[mid + 1]){
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }else{
                if(nums[mid] == nums[mid - 1]){
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }
        }
        return nums[l];
    }

    public static int singleNonDuplicate1(int[] nums) {
        int l = 0;
        int r = nums.length - 2;
        int mid;
        while(l <= r){
            mid = (l + r) >> 1;
            if(nums[mid] == nums[mid ^ 1]){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return nums[l];
    }
}

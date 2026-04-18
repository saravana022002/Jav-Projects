package NeetCode250.ArraysAndHashing;

public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;
        System.out.println(removeElementOptimal(nums, val));
    }
    public static int removeElement(int[] nums, int val) {
        int l = 0;
        for (int num : nums) {
            if (num == val) {
                continue;
            }
            nums[l] = num;
            l++;
        }
        return l;
    }


    public static int removeElementOptimal(int[] nums, int val) {
        int i = 0;
        int n = nums.length - 1;
        while (i <= n){
            if(nums[i] == val){
                nums[i] = nums[n--];
            }else {
                i++;
            }
        }
        return i;
    }


    public static int removeElementBrute(int[] nums, int val) {
        int k = 0;
        int[] anss = new int[nums.length];
        for (int num : nums) {
            if (num == val) {
                continue;
            }
            anss[k] = num;
            k++;
        }
        int l = 0;
        for (int ans : anss) {
            if(l >= k){
              break;
            }
            nums[l] = ans;
            l++;
        }
        return l;
    }
}

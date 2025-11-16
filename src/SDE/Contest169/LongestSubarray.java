package SDE.Contest169;

public class LongestSubarray {
    public static void main(String[] args) {
        int[] nums = {3,-4,-2};
        System.out.println(longestSubarray(nums));
    }
    public static int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = 1;
        int len;
        boolean isAltered;
        int prev;
        for (int i = 0; i < n; i++) {
            prev = nums[i];
            isAltered = false;
            len = 1;
            for (int j = i + 1; j < n; j++) {
                if(prev <=  nums[j]){
                    prev = nums[j];
                    len++;
                } else if (!isAltered){
                    isAltered = true;
                    len++;


                } else {
                    break;
                }
            }
            max = Math.max(max, len);
        }
        return max;
    }

    public static int longestSubarray1(int[] nums) {
        int n = nums.length;
        int max = 1;
        int len;
        boolean isAltered;
        int prev;

        for (int i = 0; i < n; i++) {
            prev = nums[i];
            isAltered = false;
            len = 1;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] >= prev) {
                    prev = nums[j];
                    len++;
                } else if (!isAltered) {
                    // Use our one replacement
                    isAltered = true;
                    len++;

                    // simulate replacement:
                    // replace nums[j] with prev (so next comparisons still valid)
                    // OR if j >= 2 and nums[j] >= nums[j-2], simulate replacing nums[j-1]
                    if (j >= 2 && nums[j] >= nums[j - 2]) {
                        prev = nums[j]; // replacing nums[j-1]
                    } // else keep prev same (replacing nums[j])
                } else {
                    break;
                }
            }
            max = Math.max(max, len);
        }
        return max;
    }

}

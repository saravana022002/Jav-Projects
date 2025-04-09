package Arryas4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSome {
    public static void main(String[] args) {
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;
        System.out.println(optimalSolution(nums, target));
    }

    private static List<List<Integer>> optimalSolution(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ansFinal = new ArrayList<>();

        int size = nums.length;
        for (int i = 0; i < size - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < size - 2; j++) {
                if (j > i+1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = size - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ansFinal.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return ansFinal;
    }
}

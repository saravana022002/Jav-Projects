package Arryas4;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {1,-1,-1,0};
        System.out.println("Better:-->"+betterSolution(nums));

        System.out.println("Optimal:-->"+optimalSolution(nums));
    }

    private static List<List<Integer>> optimalSolution(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ansFinal = new ArrayList<>();

        int size = nums.length;
        for (int i = 0; i < size - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int left = i + 1;
            int right = size - 1;
            while(left < right ){
                int sum = nums[left] + nums[right] + nums[i];
                if(sum == 0){
                    ansFinal.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
                    right --;
                    left ++;
                }else if(sum > 0){
                    right --;
                }else {
                    left ++;
                }
            }
        }
        return ansFinal;
    }

    private static int minusRight(int right, int[] nums) {
        int prevRight = nums[right];
        for (int i = right - 1; i > 0; i--) {
            if (nums[i] != prevRight) {
                return i;
            }
            right = i;
        }
        right = right - 1;
        return right;
    }

    private static int addI(int i, int[] nums) {
        int prevI = nums[i];
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] != prevI) {
                return j;
            }
            i = j;
        }
        i = i + 1;
        return i;
    }

    private static int addLeft(int left, int[] nums) {
        int prevLeft = nums[left];
        for (int i = left + 1; i < nums.length; i++) {
            if (nums[i] != prevLeft) {
                return i;
            }
            left = i;
        }
        left = left + 1;
        return left;
    }
    //nums[i] + nums[j] + nums[k] = 0

    public static List<List<Integer>> betterSolution(int[] nums) {
        Map<Integer, Integer> ansMap = new HashMap<>();
        List<List<Integer>> ansFinal = new ArrayList<>();
        int temp;
        for (int j = 0; j < nums.length; j++) {
            for (int i = j+1; i < nums.length; i++) {
                temp = - (nums[j] + nums[i]);
                if(ansMap.containsKey(temp)){
                    int[] ans = new int[3];
                    ans[0] = nums[j];
                    ans[1] = temp;
                    ans[2] = nums[i];
                    Arrays.sort(ans);
                    List<Integer> triplet = Arrays.asList(ans[0], ans[1], ans[2]);
                    if(!ansFinal.contains(triplet)) {
                        ansFinal.add(triplet);
                    }
                }
                ansMap.put(nums[i], i);
            }
            ansMap = new HashMap<>();
        }
        return ansFinal;
    }
}

package SDE.StackAndQueue;

import java.util.*;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums = {1,-1};
//        System.out.println(Arrays.toString(maxSlidingWindow(nums, 1)));
        System.out.println(Arrays.toString(maxWindow(nums, 1)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        Queue<Integer> integers = new LinkedList<>();
        int max = 0;
        int i = 0;
        int[] ans = new int[nums.length - k + 1];
        for (i = 0; i < k; i++) {
            integers.add(nums[i]);
            max = Math.max(nums[i], max);
        }
        ans[0] = max;
        for (int j = i; j < nums.length; j++) {
            if(!integers.isEmpty() && integers.peek() == max){
                integers.poll();
                max = reCompute(integers);
            }else {
                integers.poll();
            }
            max = Math.max(max, nums[j]);
            integers.add(nums[j]);
            ans[j - k + 1] = max;
        }
        return ans;
    }




    public static int reCompute(Queue<Integer> integers){
        int max = Integer.MIN_VALUE;
        if(integers.isEmpty()){
            return max;
        }
        for (Integer i : integers){
            max = Math.max(i, max);
        }
        return max;
    }

    public static int[] maxWindow(int[] nums, int k){
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && dq.peek() < i - k + 1){
                dq.poll();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i] ){
                dq.pollLast();
            }
            dq.offer(i);
            if(i >= k - 1){
                ans[i - k + 1] = nums[dq.peek()];
            }
        }
        return ans;
    }
}

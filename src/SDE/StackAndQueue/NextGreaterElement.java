package SDE.StackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {
    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        nextGreaterElement(nums1, nums2);
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> integerIntegerMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.empty() && stack.peek() <= nums2[i]){
                stack.pop();
            }
            if(stack.empty()){
                integerIntegerMap.put(nums2[i], -1);
            }else {
                integerIntegerMap.put(nums2[i], stack.peek());
            }
            stack.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = integerIntegerMap.get(nums1[i]);
        }
        return nums1;
    }
}

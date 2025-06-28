package SDE.BinaryTree;

import java.util.*;

public class FindAllKArray {

    public static void main(String[] args) {
        int[] nums = {3,4,9,1,3,9,5};
        int key = 9;
        int k = 1;
        findKDistantIndices(nums, key, k);
    }
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> indexOfKey = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(key == nums[i]){
                indexOfKey.add(i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int kai : indexOfKey){
                if(Math.abs(kai - i) <= k){
                    integers.add(i);
                    break;
                }
            }
        }
        return integers;
    }

}

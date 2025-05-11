package SDE.Arryas4;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestConsecutiveSeq {
    public static void main(String[] args) {
         int[] nums = {1,0,1,2};
        System.out.println(bruteSolution(nums));
        int[] kums = {1,0,1,2};
        System.out.println(optimalSolution(kums));
    }

    public static int optimalSolution(int[] nums) {
        int arrayLength = nums.length;
        if(arrayLength == 0){
            return 0;
        }
        int currentElement;
        int longestSeq = 1;
        int currentSeq;

        Set<Integer> hashedNums = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());

        for (int num : hashedNums) {
            if (!hashedNums.contains(num - 1)) {
                currentSeq = 0;
                currentElement = num;
                while (hashedNums.contains(currentElement)) {
                    currentSeq++;
                    currentElement = currentElement + 1;
                }
                if (currentSeq > longestSeq) {
                    longestSeq = currentSeq;
                }
            }
        }

        return longestSeq;

    }
    public static int bruteSolution(int[] nums) {
        Arrays.sort(nums);
        int arrayLength = nums.length;
        if(arrayLength == 0){
            return 0;
        }
        int prevElement = -99;
        int longestSeq = 1;
        int currentSeq = longestSeq;
        for (int i = 0; i < arrayLength; i++) {
            if(prevElement == nums[i] ){
                continue;
            }
            if(nums[i] == prevElement + 1 ){
                currentSeq ++;
            }else {
                currentSeq = 1;
            }
            prevElement = nums[i];
            if(currentSeq > longestSeq){
                longestSeq = currentSeq;
            }
        }

        return longestSeq;

    }
}

package Arrays3;

import java.util.HashMap;
import java.util.Map;

public class FindMajorityHalfTimesElement {

    public static void main(String[] args) {
        int[] nums = {1,3,1,1,4,1,1,5,1,1,6,2,2};
//        System.out.println(findMajorityBrute(nums));
//        System.out.println(findMajorityBetter(nums));
        System.out.println(findMajorityOptimal(nums));
    }

    private static int findMajorityOptimal(int[] nums) {
        int cnt = 0;
        int element = nums[0];
        for (int num : nums) {
            if(cnt == 0){
                element = num;
                cnt ++;
                continue;
            }
            cnt = element == num ? cnt + 1 : cnt - 1;
        }
        return element;
    }

    private static int findMajorityBrute(int[] nums) {

        int cnt = 0;
        int cntMax = 0;
        int cntMaxElement = 0;
        for (int i : nums) {
            for (int j : nums) {
                if (j == i) {
                    cnt++;
                }
            }
            if (cnt > cntMax) {
                cntMax = cnt;
                cntMaxElement = i;
            }
            cnt = 0;
        }
        return cntMaxElement;
    }

    private static int findMajorityBetter(int[] nums) {
        Map<Integer, Integer> solns = new HashMap<>();
        for (int num : nums) {
            if (!solns.containsKey(num)) {
                solns.put(num, 1);
            } else {
                solns.put(num, solns.get(num) + 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : solns.entrySet()){
            if(entry.getValue() > nums.length / 2){
                return entry.getKey();
            }
        }
        return 0;
    }


}

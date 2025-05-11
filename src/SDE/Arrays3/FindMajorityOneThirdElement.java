package SDE.Arrays3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindMajorityOneThirdElement {

    public static void main(String[] args) {
        int[] nums = {2,2};
//        System.out.println(findMajorityBrute(nums));
//        System.out.println(findMajorityBetter(nums));
        System.out.println(findMajorityOptimal(nums));
    }

    private static List<Integer> findMajorityOptimal(int[] nums) {
        int cnt1 = 0;
        int cnt2 = 0;
        int element1 = Integer.MIN_VALUE;
        int element2 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (cnt1 == 0 && num != element2) {
                element1 = num;
                cnt1++;
            } else if (cnt2 == 0 && num != element1) {
                element2 = num;
                cnt2++;
            } else if (num == element1) {
                cnt1++;
            } else if (num == element2) {
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        List<Integer> res = new ArrayList<>();

        cnt1 = 0; cnt2 = 0;
        for (int num : nums){
            if(num == element1)
                cnt1++;
            if (num == element2)
                cnt2++;
        }

        if(cnt1 > nums.length / 3)
            res.add(element1);

        if(cnt2 > nums.length / 3)
            res.add(element2);

        return res;
    }

    private static List<Integer> findMajorityBrute(int[] nums) {
        int cnt = 0;
        List<Integer> res = new ArrayList<>();
        for (int i : nums) {
            for (int j : nums) {
                if (j == i) {
                    cnt++;
                }
            }
            if (cnt > (nums.length / 3) && !res.contains(i)) {
                res.add(i);
            }
            cnt = 0;
        }
        return res;
    }

    private static List<Integer> findMajorityBetter(int[] nums) {
        Map<Integer, Integer> solns = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            if (!solns.containsKey(num)) {
                solns.put(num, 1);
            } else {
                solns.put(num, solns.get(num) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : solns.entrySet()) {
            if (entry.getValue() > nums.length / 3 && !res.contains(entry.getKey())) {
                res.add(entry.getKey());
            }
        }
        return res;
    }
}

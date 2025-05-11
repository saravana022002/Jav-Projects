package SDE.Recursion;

import java.util.ArrayList;
import java.util.List;

public class CandidateSum {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        combinationSum(res, ans, candidates, target, 0, 0);
        System.out.println(res);
    }
    public static void combinationSum(List<List<Integer>> res, List<Integer> ans, int[] candidates, int target, int sum, int index) {
        if(sum >= target || index == candidates.length){
            if(sum == target){
                res.add(new ArrayList<>(ans));
            }
            return;
        }
        ans.add(candidates[index]);
        combinationSum(res, ans, candidates, target, sum + candidates[index], index);
        ans.remove(ans.size() - 1);
        combinationSum(res, ans, candidates, target, sum, index + 1);
    }

}

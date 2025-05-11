package SDE.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PermutationSequence {
    public static void main(String[] args) {
        int K = 17;
        int N = 4;
        System.out.println(getPermutation(N, K));
    }
    public static String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        k--;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        List<Integer> ans = new ArrayList<>();
        int divisor = 0;
        int index = 0;
        while(true){
            if(nums.size() == 1) {
                ans.add(nums.get(0));
                break;
            }
            divisor = factorial(nums.size() - 1);
            index = k / divisor;
            ans.add(nums.get(index));
            k = k % divisor;
            nums.remove(nums.get(index));
        }
        String result = ans.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));

        return result;
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

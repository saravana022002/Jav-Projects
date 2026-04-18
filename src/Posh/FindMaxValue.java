package Posh;

import java.util.HashMap;
import java.util.Map;

public class FindMaxValue {
    public static void main(String[] args) {
        int n = 10;
        int[][] restrictions = {{3,1},{8,1}};
        int[] diff = {2,2,3,1,4,5,1,1,2};
        findMaxVal(n, restrictions, diff);
    }
    public static int findMaxVal(int n, int[][] restrictions, int[] diff) {
        int[] a = new int[n];
        a[0] = 0;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> restrict = new HashMap<>();
        for (int[] restriction : restrictions) {
            restrict.put(restriction[0], restriction[1]);
        }
        for (int i = 1; i < n - 1; i++) {
            if(!restrict.containsKey(i)){
                a[i] = Math.abs(a[i - 1] + diff[i - 1]);
            }else {
                a[i] = restrict.get(i);
            }
        }
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }
}

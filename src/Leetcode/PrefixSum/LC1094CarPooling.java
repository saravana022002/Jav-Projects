package Leetcode.PrefixSum;

import java.util.ArrayList;
import java.util.List;

public class LC1094CarPooling {
    public static void main(String[] args) {
        int[][] trips = {{2,1,5},{3,5,7}};
        int capacity = 3;
        carPooling(trips, capacity);
    }
    public static boolean carPooling(int[][] trips, int capacity) {
        List<Integer> diff = new ArrayList<>();
        diff.add(0);
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            while (diff.size() <= to) {
                diff.add(0);
            }
            diff.set(from, diff.get(from) + passengers);
            diff.set(to, diff.get(to) - passengers);
        }

        int prefixSum = 0;
        for (Integer dif : diff) {
            prefixSum += dif;
            if (prefixSum > capacity){
                return false;
            }
        }
        return true;
    }


    public static boolean carPoolingOpt(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            diff[from] = diff[from] + passengers;
            diff[to] = diff[to] - passengers;
        }
        int prefixSum = 0;
        for (Integer dif : diff) {
            prefixSum += dif;
            if (prefixSum > capacity){
                return false;
            }
        }
        return true;
    }
}

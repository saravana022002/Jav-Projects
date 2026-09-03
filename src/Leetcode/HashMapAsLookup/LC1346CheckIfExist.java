package Leetcode.HashMapAsLookup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LC1346CheckIfExist {
    public static void main(String[] args) {
        int[] arr = {10,2,5,3};
        System.out.println(checkIfExist(arr));
    }
    public static boolean checkIfExist(int[] arr) {
        HashSet<Integer> map = new HashSet<>();
        for (int j : arr) {
            if (map.contains(j * 2) || (j % 2 == 0 && map.contains(j / 2))) {
                return true;
            }
            map.add(j);
        }
        return false;
    }
}

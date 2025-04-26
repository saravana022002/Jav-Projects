package GreedyAlgorithm;

import java.util.Arrays;

public class MinimumPlatforms {
    public static void main(String[] args) {
        int[] arr = {100, 200};
        int[] dep = {200, 300};
        System.out.println(findPlatform(arr, dep));
    }

    static int findPlatform(int arr[], int dep[]) {
        // add your code here

        int size = arr.length;
        Arrays.sort(arr);
        Arrays.sort(dep);
        int cnt = 0;
        int maxCnt = 0;
        int i = 0;
        int j = 0;
        while (i < size && j < size){
            if(arr[i] <= dep[j]){
                cnt++;
                i++;
            }else {
                cnt--;
                j++;
            }
            maxCnt = Math.max(cnt, maxCnt);
        }
        return maxCnt;
    }
}

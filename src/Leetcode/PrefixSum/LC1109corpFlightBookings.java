package Leetcode.PrefixSum;

import java.util.Arrays;

public class LC1109corpFlightBookings {
    public static void main(String[] args) {
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        int n = 5;
        System.out.println(Arrays.toString(corpFlightBookings(bookings, n)));
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        int[] diff = new int[n];
        for(int[] arr : bookings){
            int start = arr[0];
            int end  = arr[1];
            int count = arr[2];
            diff[start - 1] += count;
            if(end < n){
                diff[end] -= count;
            }
        }
        int count = 0;
        for(int j = 0; j < n; j++){
            count += diff[j];
            ans[j] = count;
        }
        return ans;
    }
}

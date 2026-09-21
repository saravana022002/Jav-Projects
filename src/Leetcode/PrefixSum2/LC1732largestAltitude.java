package Leetcode.PrefixSum2;

public class LC1732largestAltitude {

    public static void main(String[] args) {
        int[] gain = {-5,1,5,0,-7};
        System.out.println(largestAltitude(gain));
    }
    public static int largestAltitude(int[] gain) {
        int[] prefixSum = new int[gain.length + 1];
        prefixSum[0] = 0;
        int count = 0;
        int max = prefixSum[0];
        for(int i = 0; i < gain.length; i++){
            count += gain[i];
            prefixSum[i + 1] = count;
            max = Math.max(max, prefixSum[i + 1]);
        }
        return max;
    }

}



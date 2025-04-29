package GreedyAlgorithm;

import java.util.Arrays;

public class AssignCookies {
    public static void main(String[] args) {
        int[] g = {1,2};
        int[] s = {1,2,3};
        System.out.println(findContentChildren(g, s));
    }
    public static int findContentChildren(int[] g, int[] s) {
        int count = 0;
        int gSize = g.length;
        int sSize = s.length;
        Arrays.sort(g);
        Arrays.sort(s);

        int i=0;
        int j=0;
        while (i < gSize && j < sSize){
            if( s[j] >= g[i] ){
                count ++;
                i++;
            }
            j++;
        }
        return count;
    }
}

package Leetcode.Daily;

public class RectangleOverLap {
    public static void main(String[] args) {
        int[] rec1 = {0,0,1,1};
        int[] rec2 = {1,0,2,1};
        System.out.println(isRectangleOverlap(rec1, rec2));
    }

    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // if R rectangle are completely right
        if(rec1[2] <= rec2[0]){
            return false;

        }
        // if R rectangle are completely Up
        if(rec1[3] <= rec2[1]){
            return false;
        }
        // if R rectangle are completely left
        if(rec1[0] >= rec2[2]){
            return false;
        }
        // if R rectangle are completely down
        if(rec1[1] >= rec2[3]){
            return false;
        }
        return true;
    }
}

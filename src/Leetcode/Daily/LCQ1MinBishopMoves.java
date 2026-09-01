package Leetcode.Daily;

public class LCQ1MinBishopMoves {
    public static void main(String[] args) {
        int[] source = {4,2};
        int[] target = {1,3};
        System.out.println(minBishopMoves(source, target));
    }

    public static int minBishopMoves(int[] source, int[] target) {
        boolean sourcePos = (source[0] + source[1]) % 2 == 0;
        boolean targetPos = (target[0] + target[1]) % 2 == 0;
        if(sourcePos != targetPos){
            return -1;
        }
        int distanceRow = Math.abs(source[0] - target[0]);
        int distanceCol = Math.abs(source[1] - target[1]);
        if(distanceRow == distanceCol){
            return 1;
        } else{
            return 2;
        }
    }
}

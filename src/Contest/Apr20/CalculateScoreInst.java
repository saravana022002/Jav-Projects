package Contest.Apr20;

import java.util.HashSet;
import java.util.Set;

public class CalculateScoreInst {
    public static void main(String[] args) {
        String[] instructions = {"jump","add","add","jump","add","jump"};
        int[] values = {2,1,3,1,-2,-3};
        System.out.println(calculateScore(instructions, values));
    }

    public static long calculateScore(String[] instructions, int[] values) {
        long score = 0L;
        Set<Integer> visitedOnes = new HashSet<>();
        int i = 0;
        while(!visitedOnes.contains(i) && i >= 0 && i < values.length){
            visitedOnes.add(i);
            if(instructions[i].equals("jump")){
                i = i + values[i];
            }else if (instructions[i].equals("add")){
                score = score + values[i];
                i = i + 1;
            }
        }
        return score;
    }
}

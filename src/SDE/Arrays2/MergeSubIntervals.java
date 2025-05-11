package SDE.Arrays2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSubIntervals {
    public static void main(String[] args) {
        int [][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    public static int[][] merge(int[][] intervals) {
        int arrSize = intervals.length;
        if(arrSize <= 1){
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[] currentPair = intervals[0];
        List<int[]> arrays = new ArrayList<>();
        arrays.add(currentPair);
        for (int i = 1; i < arrSize; i++) {
            int currentEnd = currentPair[1];
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if(currentEnd >= nextStart){
                currentPair[1] = Math.max(currentEnd, nextEnd);
            }else {
                currentPair = intervals[i];
                arrays.add(currentPair);
            }

        }
        return arrays.toArray(new int[arrays.size()][]);

    }
}

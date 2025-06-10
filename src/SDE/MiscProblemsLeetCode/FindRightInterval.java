package SDE.MiscProblemsLeetCode;

import java.util.*;

public class FindRightInterval {
    public static void main(String[] args) {
        int[][] intervals = {{3,4},{2,3},{1,2}};
        findRightInterval(intervals);
    }
    public static int[] findRightInterval(int[][] intervals) {
        List<Interval> intervalList = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalList.add(new Interval(intervals[i][0], intervals[i][1], i));
        }
        intervalList.sort(Comparator.comparingInt(a -> a.start));
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int target = intervals[i][1];
            result[i] = binarySearch(intervalList, target);
        }
        return result;
    }

    public static int binarySearch(List<Interval> intervalList, int target){
        int left = 0;
        int right = intervalList.size() - 1;
        int middle;
        int result = -1;
        while (left <= right){
            middle = (left + right) / 2;
            if (intervalList.get(middle).start >= target) {
                result = intervalList.get(middle).index;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return result;
    }



    public static class Interval{
        int start;
        int end;
        int index;
        Interval(int start, int end, int index){
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }


}

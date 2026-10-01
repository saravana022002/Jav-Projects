package Practice.Intervals;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// ============================================================================
// LC 56. Merge Intervals   [Medium]
// https://leetcode.com/problems/merge-intervals/
// Topics: Array, Sorting, Quicksort
// ============================================================================
//
// PROBLEM
//   intervals[i] = [start_i, end_i]. Merge every group of overlapping intervals into a single
//   interval and return the resulting list of non-overlapping intervals. Intervals that only
//   touch at an end point, like [1,4] and [4,5], count as overlapping.
//
// EXAMPLES
//   [[1,3],[2,6],[8,10],[15,18]]  ->  [[1,6],[8,10],[15,18]]   [1,3] and [2,6] overlap
//   [[1,4],[4,5]]                 ->  [[1,5]]                  touching intervals merge
//   [[4,7],[1,4]]                 ->  [[1,7]]                  the input need not be sorted
//
// CONSTRAINTS
//   1 <= intervals.length <= 10^4,   intervals[i].length == 2,   0 <= start_i <= end_i <= 10^4
//
// INTUITION
//   Sort by start, and intervals that belong together end up next to each other. Sweep from
//   left to right while growing one "current" interval. The next interval starts no earlier
//   than the current one, so it overlaps exactly when its start is <= the current end; then
//   the current interval just stretches its end to the larger of the two ends (the next one
//   may even lie completely inside). If it does not overlap, nothing later can reach back
//   either (later intervals start even further right), so the current interval is final and
//   the next one starts a new group.
//
// APPROACH (as implemented below)
//   1. arrSize = intervals.length; with 0 or 1 intervals there is nothing to merge, so
//      return the input as it is.
//   2. Sort intervals by their start: Comparator.comparingInt(a -> a[0]).
//   3. currentPair = intervals[0] is the interval being built; arrays (the result list)
//      starts with it.
//   4. For i = 1 .. arrSize-1, with currentEnd = currentPair[1] and nextStart / nextEnd
//      from intervals[i]:
//        currentEnd >= nextStart -> overlap or touch: currentPair[1] = max(currentEnd, nextEnd)
//        otherwise               -> intervals[i] becomes the new currentPair and is added to
//                                   arrays
//   5. Return arrays as an int[][].
//
// DRY RUN   intervals = [[1,3],[2,6],[8,10],[15,18]]   (already sorted)
//   start   currentPair = [1,3], arrays = [[1,3]]
//   i=1     next [2,6]:   3 >= 2 -> end = max(3,6) = 6     currentPair = [1,6]
//   i=2     next [8,10]:  6 >= 8 is false -> new interval  arrays = [[1,6],[8,10]]
//   i=3     next [15,18]: 10 >= 15 is false -> new interval arrays = [[1,6],[8,10],[15,18]]
//
// COMPLEXITY
//   Time : O(n log n) - the sort dominates; the sweep is O(n)
//   Space: O(n) - the result list (plus the sort's scratch space); the merged intervals reuse
//          the input's own int[] objects
//
// GOTCHAS
//   - The test is >= (not >): [1,4] and [4,5] must merge.
//   - Take max(currentEnd, nextEnd), not just nextEnd: [1,10] followed by [2,3] must keep
//     the end at 10.
//   - The sort is what lets one sweep do the job; without it [[4,7],[1,4]] would not merge.
//   - The input is changed: its rows are reordered by the sort, and the first row of each
//     merged group has its end extended in place (arrays holds the same int[] objects).
//     For an input of 0 or 1 interval the very same array is returned, not a copy.
//
// RELATED
//   - LC 57 Insert Interval - insert one interval into a sorted, non-overlapping list and
//     merge what it touches.
//   - LC 253 Meeting Rooms II - the same sort-by-start idea, used to count overlaps.
//   - LC 986 Interval List Intersections - two sorted interval lists, and what they share.
// ============================================================================
public class LC56MergeIntervals {
    public static void main(String[] args) {
        // Example 1 - prints [[1, 6], [8, 10], [15, 18]]
        int [][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    // Returns the merged, non-overlapping intervals (see GOTCHAS: it modifies the input).
    public static int[][] merge(int[][] intervals) {
        int arrSize = intervals.length;
        // nothing to merge
        if(arrSize <= 1){
            return intervals;
        }
        // order by start so that overlapping intervals are neighbours
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // currentPair = the merged interval being built; it is already in the result list
        int[] currentPair = intervals[0];
        List<int[]> arrays = new ArrayList<>();
        arrays.add(currentPair);
        for (int i = 1; i < arrSize; i++) {
            int currentEnd = currentPair[1];
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if(currentEnd >= nextStart){
                // overlap or touch: stretch the current interval (the list sees it too)
                currentPair[1] = Math.max(currentEnd, nextEnd);
            }else {
                // gap: the current interval is final, start a new one
                currentPair = intervals[i];
                arrays.add(currentPair);
            }

        }
        return arrays.toArray(new int[arrays.size()][]);

    }
}

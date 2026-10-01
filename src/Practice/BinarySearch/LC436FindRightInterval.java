package Practice.BinarySearch;

import java.util.*;

// ============================================================================
// LC 436. Find Right Interval   [Medium]
// https://leetcode.com/problems/find-right-interval/
// Topics: Array, Binary Search, Sorting
// ============================================================================
//
// PROBLEM
//   intervals[i] = [start_i, end_i] and every start is different. The RIGHT interval of
//   interval i is the interval j whose start is >= end_i and is the smallest such start
//   (j may be i itself). For every interval return the index of its right interval, or
//   -1 when no interval starts at or after its end.
//
// EXAMPLES
//   [[3,4],[2,3],[1,2]]  ->  [-1,0,1]
//       [3,4] needs a start >= 4: none.  [2,3] needs >= 3: start 3 is interval 0.
//       [1,2] needs >= 2: starts 2 and 3 qualify, the smaller one is interval 1.
//   [[1,2]]              ->  [-1]     the only interval starts before its own end
//   [[1,4],[2,3],[3,4]]  ->  [-1,2,-1]
//
// CONSTRAINTS
//   1 <= intervals.length <= 2 * 10^4,   intervals[i].length == 2,
//   -10^6 <= start_i <= end_i <= 10^6,   all starts are unique
//
// INTUITION
//   For each interval the question is "what is the smallest start that is >= my end?".
//   That is a lower-bound lookup, and a lower-bound lookup is a binary search - as soon as
//   the intervals are sorted by start. Sorting moves the intervals around, but the answer
//   must be an ORIGINAL index, so every interval travels in a small wrapper that carries
//   its original position (Interval.index). Starts are unique, so the smallest start
//   >= end identifies exactly one interval.
//
// APPROACH (as implemented below)
//   1. Wrap each interval as Interval(start, end, i), i = its index in the input, and put
//      the wrappers in intervalList.
//   2. Sort intervalList by start.
//   3. For every input position i: target = intervals[i][1] (its end), and
//      result[i] = binarySearch(intervalList, target).
//   4. binarySearch: result = -1; while left <= right, probe middle. If that start is
//      >= target, it is a candidate: remember its original index and search further left
//      for a smaller start that still qualifies (right = middle - 1). Otherwise
//      left = middle + 1. Return result.
//
// DRY RUN   intervals = [[3,4],[2,3],[1,2]]
//   sorted by start: [1,2] (index 2), [2,3] (index 1), [3,4] (index 0)
//   i=0: target 4: probe start 2 < 4 -> right part, probe start 3 < 4 -> nothing left, -1
//   i=1: target 3: probe start 2 < 3 -> right part, probe start 3 >= 3 -> candidate index 0
//   i=2: target 2: probe start 2 >= 2 -> candidate index 1, go left: start 1 < 2 -> stop
//   result = [-1, 0, 1]
//
// STATUS
//   Known issue (speed, not correctness): intervalList is a LinkedList, so every get()
//   inside binarySearch walks the list - O(n) instead of O(1). The answers are correct,
//   but the work grows to O(n^2 log n): n = 20000 (the maximum) took about 3.3 s here,
//   against about 6 ms when the same code uses an ArrayList. The code is left unchanged
//   on purpose.
//   Also: main() discards the result, so nothing is printed (it returns [-1, 0, 1] for
//   its input), and the end field of Interval is stored but never read.
//
// COMPLEXITY
//   Time : as written O(n^2 log n) - n searches x log n probes x O(n) per LinkedList.get.
//          With an ArrayList it would be O(n log n): the sort plus n searches of O(log n).
//   Space: O(n) - the wrapper list and the result array
//
// GOTCHAS
//   - The answer is the ORIGINAL index, not the position in the sorted list - hence the
//     index field in Interval.
//   - An interval can be its own right interval: [5,5] has end 5 and start 5, so
//     findRightInterval([[5,5]]) returns [0].
//   - The search looks for the first start >= target, so it keeps going left after a hit
//     instead of returning at once.
//   - The input array itself is not sorted or changed; only the wrapper list is.
//
// RELATED
//   - LC 2300 Successful Pairs of Spells and Potions (in this repo) - the same "sort, then
//     binary search the first element that qualifies" pattern.
//   - LC 352 Data Stream as Disjoint Intervals - another problem built on intervals kept
//     in order of their starts.
// ============================================================================
public class LC436FindRightInterval {
    public static void main(String[] args) {
        // Example 2: findRightInterval returns [-1, 0, 1], but the result is discarded,
        // so nothing is printed (see STATUS)
        int[][] intervals = {{3,4},{2,3},{1,2}};
        findRightInterval(intervals);
    }
    // For every interval returns the original index of its right interval, or -1.
    public static int[] findRightInterval(int[][] intervals) {
        // wrap each interval with its original index, then sort the wrappers by start
        List<Interval> intervalList = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            intervalList.add(new Interval(intervals[i][0], intervals[i][1], i));
        }
        intervalList.sort(Comparator.comparingInt(a -> a.start));
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            // look for the smallest start that is >= the end of interval i
            int target = intervals[i][1];
            result[i] = binarySearch(intervalList, target);
        }
        return result;
    }

    // Returns the original index of the interval with the smallest start >= target, or -1.
    // (intervalList.get is O(n) on a LinkedList - see STATUS)
    public static int binarySearch(List<Interval> intervalList, int target){
        int left = 0;
        int right = intervalList.size() - 1;
        int middle;
        int result = -1;
        while (left <= right){
            middle = (left + right) / 2;
            if (intervalList.get(middle).start >= target) {
                // qualifies: remember it, then look left for a smaller start that also qualifies
                result = intervalList.get(middle).index;
                right = middle - 1;
            } else {
                // start too small: the answer is further right
                left = middle + 1;
            }
        }
        return result;
    }



    // interval plus its position in the input (sorting changes positions, the answer needs
    // the original one); end is stored but never read
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

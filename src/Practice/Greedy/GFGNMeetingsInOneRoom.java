package Practice.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// ============================================================================
// GFG - N Meetings in One Room   [Medium]
// https://www.geeksforgeeks.org/problems/activity-selection-1587115620/1
// Topics: Greedy, Sorting, Intervals
// ============================================================================
//
// PROBLEM
//   Meeting i runs from start[i] to finish[i] and the room can hold only one meeting at a
//   time. Choose as many meetings as possible so that no two chosen ones overlap. Touching
//   counts as overlapping: if a chosen meeting ends at time x, the next chosen one must start
//   at a time strictly greater than x. Return how many meetings can be held.
//
// EXAMPLES
//   start = [1,3,0,5,8,5], finish = [2,4,6,7,9,9]  ->  4
//        hold (1,2), (3,4), (5,7), (8,9) - each starts after the previous one ends
//   start = [10,12,20], finish = [20,25,30]  ->  1
//        the first ends at 20 and the third starts at 20, which is not allowed, and the
//        middle one overlaps both - so only one meeting fits
//
// CONSTRAINTS
//   1 <= start.size() = finish.size() <= 2 * 10^5
//   0 <= start[i] <= finish[i] <= 10^9
//
// INTUITION
//   To fit the most meetings, free the room again as early as possible. Among the meetings
//   still allowed, the one that FINISHES first leaves the longest stretch of time for the
//   rest, so picking it can never be worse: in any best plan, swap its first meeting for the
//   earliest-finishing one and every later meeting still fits. Repeat on what is left - that
//   is one sort followed by a single sweep.
//
// APPROACH (as implemented below)
//   1. Wrap each meeting as Meeting(start, end, pos = i + 1); pos is its original 1-based
//      number.
//   2. Sort by end time, earliest first (equal end: earlier start first).
//   3. The first meeting in that order is always taken: count = 1, print its pos, and
//      remember it in `compare`.
//   4. For every later meeting: if its start > compare.end (strictly greater), take it -
//      count++, print its pos, compare = this meeting. Otherwise skip it.
//   5. Return count.
//
// DRY RUN   example 1: start = [1,3,0,5,8,5], finish = [2,4,6,7,9,9]
//   sorted by end: #1 (1,2)  #2 (3,4)  #3 (0,6)  #4 (5,7)  #6 (5,9)  #5 (8,9)
//                  (#6 and #5 both end at 9, so the earlier start comes first)
//   take #1 (1,2)                      count 1   last end = 2
//   #2 starts 3 > 2      -> take       count 2   last end = 4
//   #3 starts 0 > 4? no  -> skip
//   #4 starts 5 > 4      -> take       count 3   last end = 7
//   #6 starts 5 > 7? no  -> skip
//   #5 starts 8 > 7      -> take       count 4   last end = 9
//   prints 1 2 4 5 (one per line), returns 4
//
// COMPLEXITY
//   Time : O(n log n) - the sort; building the list and the sweep are O(n).
//   Space: O(n) - the list of Meeting objects.
//
// GOTCHAS
//   - The test is a strict `>`: a meeting that starts exactly when the last chosen one ends
//     is skipped (example 2). With `>=` touching meetings would be allowed.
//   - The comparator subtracts ints; times are 0..10^9, so the difference cannot overflow.
//   - meetings.get(0) needs at least one meeting (the constraints guarantee n >= 1); an empty
//     input would throw IndexOutOfBoundsException.
//
// STATUS
//   The code is correct (counts match both GFG examples). Two quirks: maxMeetings() also
//   PRINTS the 1-based position of every meeting it picks (in finish-time order), and main()
//   discards the returned count. For main()'s data it prints 7 and 4 and returns 2.
//
// RELATED
//   - GFGMinimumPlatforms.java (this folder) - also sorts times and sweeps, but it looks
//     for the most trains present at once instead of the most non-overlapping picks.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet). GFG now serves this problem as
//   "Activity Selection" (the same task).
//   Closest LeetCode problem: LC 435 Non-overlapping Intervals - the same greedy (it asks
//   for the minimum number to remove), but there intervals that only touch may both stay.
// ============================================================================
public class GFGNMeetingsInOneRoom {
    public static void main(String[] args) {
        // Seven meetings. Prints the chosen positions 7 and 4 (count 2 is returned, not shown).
        // Meeting 5 (18..25) only touches meeting 7 (17..18) at time 18, so it is not allowed.
        int[] start = {25, 0, 14, 24, 18, 3, 17};
        int[] end = {29, 25, 24, 26, 25, 23, 18};
        maxMeetings(start, end);
    }
    // Returns the largest number of meetings that fit; also prints the 1-based position of
    // each meeting it picks.
    public static int maxMeetings(int start[], int end[]) {
        int size = start.length;
        // step 1: keep each meeting's original 1-based position
        List<Meeting> meetings = new ArrayList<>();
        for(int i = 0; i < size; i++){
            meetings.add(new Meeting(start[i], end[i], i + 1));
        }

        // step 2: earliest finish first; equal finish -> earlier start first
        Collections.sort(meetings, (a, b) -> a.end != b.end ? a.end - b.end : a.start - b.start);

        // step 3: the meeting that finishes first is always taken
        int count = 1;
        System.out.println(meetings.get(0).pos);
        // compare = the last chosen meeting; its end decides what may come next
        Meeting compare = meetings.get(0);
        for (int i = 1; i < size; i++) {
            // step 4: starts strictly after the last chosen one ends -> take it
            if(meetings.get(i).start > compare.end){
                count ++;
                System.out.println(meetings.get(i).pos);
                compare = meetings.get(i);
            }
        }
        return count;
    }
    // one meeting: start time, end time and its original 1-based position
    static class Meeting {
        int start;
        int end;
        int pos;

        Meeting(int start, int end, int pos){
            this.start = start;
            this.end = end;
            this.pos = pos;
        }
    }
}

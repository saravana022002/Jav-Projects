package Practice.Greedy;

import java.util.Arrays;

// ============================================================================
// GFG - Minimum Platforms   [Medium]
// https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
// Topics: Greedy, Sorting, Two Pointers
// ============================================================================
//
// PROBLEM
//   arr[i] and dep[i] are the arrival and departure times of train i on one day, written as
//   24-hour HHMM numbers (900 = 09:00, 1235 = 12:35). A platform holds one train at a time.
//   Return the fewest platforms that let every train be served without waiting.
//
// EXAMPLES
//   arr = [900,940,950,1100,1500,1800], dep = [910,1200,1120,1130,1900,2000]  ->  3
//        at 11:00 the trains that came at 9:40 and 9:50 are still in, and a third arrives
//   arr = [900,1235,1100], dep = [1000,1240,1200]  ->  1
//        the three stays (9:00-10:00, 11:00-12:00, 12:35-12:40) never overlap
//   arr = [1000,935,1100], dep = [1200,1240,1130]  ->  3
//        all three trains are in the station together from 11:00 to 11:30
//
// CONSTRAINTS
//   Not part of the saved problem text - see the link.
//
// INTUITION
//   The answer is the largest number of trains in the station at one moment. To find it you
//   only need WHEN the head-count changes, not which train is which: +1 at every arrival,
//   -1 at every departure, and the peak of that running count is the number of platforms.
//   Sorting the arrivals and the departures separately gives two time-ordered event lists;
//   walking them together replays the day in time order. Pairing a train's own arrival with
//   its own departure is unnecessary - the head-count depends only on the times.
//
// APPROACH (as implemented below)
//   1. Sort arr[] and dep[] independently (Arrays.sort).
//   2. Keep i = next arrival, j = next departure, cnt = trains in the station right now,
//      maxCnt = the largest cnt seen so far.
//   3. While both pointers are inside the arrays, look at the next arrival and the next
//      departure:
//        arr[i] <= dep[j]  -> an arrival comes first (a tie counts as arrival first):
//                             cnt++, i++
//        otherwise         -> a train leaves first: cnt--, j++
//      After every step maxCnt = max(cnt, maxCnt).
//   4. Return maxCnt. Once i runs off the end only departures are left, so cnt can only
//      fall and the peak has already been recorded.
//
// DRY RUN   example 1, after sorting
//   arr = [900,940,950,1100,1500,1800]     dep = [910,1120,1130,1200,1900,2000]
//   compare        event       cnt  maxCnt
//   900  <= 910    arrival      1     1
//   940  >  910    departure    0     1
//   940  <= 1120   arrival      1     1
//   950  <= 1120   arrival      2     2
//   1100 <= 1120   arrival      3     3
//   1500 >  1120   departure    2     3
//   1500 >  1130   departure    1     3
//   1500 >  1200   departure    0     3
//   1500 <= 1900   arrival      1     3
//   1800 <= 1900   arrival      2     3     (i == size, the loop stops)  -> return 3
//   main(): arr = {100,200}, dep = {200,300}: 100 <= 200 -> cnt 1; 200 <= 200 is a tie and
//   counts as an arrival -> cnt 2; i == size, stop -> prints 2.
//
// COMPLEXITY
//   Time : O(n log n) - the two sorts; the merge loop moves i or j on every step, so it
//          runs at most 2n times.
//   Space: O(1) extra - a few counters; the arrays are sorted in place (the sort's own
//          internal working space is not counted).
//
// GOTCHAS
//   - The caller's arrays are sorted IN PLACE, so afterwards arr[k] and dep[k] no longer
//     belong to the same train.
//   - Tie rule: arr[i] <= dep[j] treats a train that arrives in the very minute another one
//     leaves as overlapping, so it needs its own platform. The saved statement does not spell
//     this case out - check the link. Using < instead would let them share a platform.
//   - HHMM numbers compare correctly as plain ints (959 < 1000), so no time parsing is needed.
//   - "// add your code here" inside the method is GFG's template text, left in place.
//
// RELATED
//   - GFGNMeetingsInOneRoom.java (this folder) - another "sort by time and sweep" interval
//     problem (how many meetings fit in one room).
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
//   Closest LeetCode problem: LC 253 Meeting Rooms II (premium) - the same sweep over sorted
//   start/end times; the tie rule differs there (a meeting may start exactly when another ends).
// ============================================================================
public class GFGMinimumPlatforms {
    public static void main(String[] args) {
        // Tie case: the second train arrives at 200, the minute the first one leaves.
        // Prints 2 - the tie counts as an overlap (see GOTCHAS).
        int[] arr = {100, 200};
        int[] dep = {200, 300};
        System.out.println(findPlatform(arr, dep));
    }

    // Returns the minimum number of platforms needed so that no train waits.
    static int findPlatform(int arr[], int dep[]) {
        // add your code here

        int size = arr.length;
        // step 1: sort both lists on their own (the arr[k]-dep[k] pairing is lost, and not needed)
        Arrays.sort(arr);
        Arrays.sort(dep);
        // cnt = trains in the station right now, maxCnt = the peak = platforms needed
        int cnt = 0;
        int maxCnt = 0;
        // i = next arrival to process, j = next departure to process
        int i = 0;
        int j = 0;
        while (i < size && j < size){
            // whichever event is earlier; on a tie the arrival goes first, so that train
            // counts as overlapping with the one that is leaving
            if(arr[i] <= dep[j]){
                cnt++;
                i++;
            }else {
                cnt--;
                j++;
            }
            // remember the peak head-count
            maxCnt = Math.max(cnt, maxCnt);
        }
        return maxCnt;
    }
}

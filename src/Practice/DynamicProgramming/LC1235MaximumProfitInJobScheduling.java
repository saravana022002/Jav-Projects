package Practice.DynamicProgramming;

import java.util.*;

// ============================================================================
// LC 1235. Maximum Profit in Job Scheduling   [Hard]
// https://leetcode.com/problems/maximum-profit-in-job-scheduling/
// Topics: Array, Binary Search, Dynamic Programming, Sorting
// ============================================================================
//
// PROBLEM
//   There are n jobs; job i runs from startTime[i] to endTime[i] and pays profit[i].
//   You may do several jobs as long as no two of them overlap in time. A job that ends at
//   time X and another that starts at time X do NOT overlap. Return the largest total
//   profit you can collect.
//
// EXAMPLES
//   startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]  ->  120
//     jobs 1 and 4: [1-3] then [3-6] touch but do not overlap, 50 + 70
//   startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]  ->  150
//     jobs 1, 4, 5: [1-3], [4-6], [6-9] = 20 + 70 + 60 (the 100-profit job [3-10] only fits
//     together with job 1, and 20 + 100 = 120 is less)
//
// CONSTRAINTS
//   1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
//   1 <= startTime[i] < endTime[i] <= 10^9
//   1 <= profit[i] <= 10^4
//
// INTUITION
//   Walk through the jobs in order of END time and let best(t) mean "the most profit from
//   jobs that all finish by time t". For a job that starts at s and pays p:
//       doing it is worth  p + best(s)
//   If that beats the best total so far, the new best(end) is that value; if not, the job
//   is not worth doing and nothing changes.
//   Times go up to 10^9, so best() cannot be kept per time unit. A TreeMap stores only the
//   end times where the best profit actually went UP. Its values therefore grow with its
//   keys, and floorEntry(s) - the last recorded end time <= s - is exactly best(s).
//   (The official hint goes the other way: sort by START time and run a suffix DP with
//   binary search. This file is the mirror image of that.)
//
// APPROACH (as implemented below)
//   1. Wrap each (startTime, endTime, profit) triple in a Job (its END time lives in the
//      field called deadline) and sort the jobs by deadline.
//   2. dpMap = TreeMap: end time -> best total profit from jobs finishing by that time.
//      Seed it with 0 -> 0 ("nothing done"). Start times are >= 1, so floorEntry can
//      never come back null.
//   3. For each job: val = dpMap.floorEntry(job.startTime).getValue() + job.profit.
//      floorEntry takes the greatest key <= startTime, so a job that ends exactly when
//      this one starts counts as compatible.
//   4. Only if val > dpMap.lastEntry().getValue() (beats the best so far) store
//      dpMap.put(job.deadline, val). Otherwise skip the job.
//   5. The answer is the value of the last entry, the best profit overall.
//
// DRY RUN   example 2, as main() runs it
//   Jobs sorted by end time, written start-end:profit
//     1-3:20, 2-5:20, 4-6:70, 6-9:60, 3-10:100
//   Map entries are written end:profit, and start with dpMap = {0:0}.
//   job        floorEntry(start)   val              beats last?    dpMap afterwards
//   1-3:20     0:0                 0 + 20 = 20      20 > 0         {0:0, 3:20}
//   2-5:20     0:0                 0 + 20 = 20      20 > 20, no    unchanged
//   4-6:70     3:20                20 + 70 = 90     90 > 20        {0:0, 3:20, 6:90}
//   6-9:60     6:90                90 + 60 = 150    150 > 90       {0:0, 3:20, 6:90, 9:150}
//   3-10:100   3:20                20 + 100 = 120   120 > 150, no  unchanged
//   return lastEntry value = 150
//
// COMPLEXITY
//   Time : O(n log n) - the sort, plus one floorEntry and at most one put per job
//          (each O(log n))
//   Space: O(n) - the Job list and the TreeMap
//
// GOTCHAS
//   - "Ends at X, next starts at X" is allowed, so the lookup must be floorEntry (<=),
//     not lowerEntry (<).
//   - Store a value only when it improves the best. A worse value under a later key would
//     make floorEntry return that smaller profit instead of the true best.
//   - No overflow: the total is at most 5 * 10^4 * 10^4 = 5 * 10^8, which fits in an int.
//
// RELATED
//   - LC 2008 Maximum Earnings From Taxi - the same pick-non-overlapping-intervals DP,
//     with rides instead of jobs.
//   - LC 2054 Two Best Non-Overlapping Events - also sorts events and looks up the best
//     compatible one, but picks at most two.
// ============================================================================
public class LC1235MaximumProfitInJobScheduling {
    public static void main(String[] args) {
        // Example 2 from the statement. Expected output: 150
        int[] startTime = {1,2,3,4,6};
        int[] endTime = {3,5,10,6,9};
        int[] profit = {20,20,100,70,60};
        System.out.println(jobScheduling(startTime, endTime, profit));
    }
    // Returns the largest total profit from a set of non-overlapping jobs.
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int size = endTime.length;
        // bundle the three parallel arrays into Job objects so they can be sorted together
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        // process jobs by END time (the field "deadline" is the end time)
        jobs.sort(Comparator.comparingInt(a -> a.deadline));

        // dpMap: end time -> best total profit from jobs finishing by then. Only the times
        // where the best profit improved are stored, so the values grow with the keys.
        TreeMap<Integer, Integer> dpMap = new TreeMap<>();
        // base case: nothing done at time 0 is worth 0 (also keeps floorEntry non-null)
        dpMap.put(0, 0);
        for (Job job : jobs) {
            // best profit among jobs ending at or before this start, plus this job's profit
            int val = dpMap.floorEntry(job.startTime).getValue() + job.profit;
            // keep it only when it beats the best total so far
            if(val > dpMap.lastEntry().getValue()) {
                dpMap.put(job.deadline, val);
            }
        }
        // the last entry holds the largest profit
        return dpMap.lastEntry().getValue();
    }

    // One job. Note: "deadline" is the job's END time.
    static class Job{
        int startTime;
        int deadline;
        int profit;
        Job(int startTime, int deadline, int profit){
            this.startTime = startTime;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
}

package Practice.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// ============================================================================
// GFG - Job Sequencing Problem   [Medium]
// https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
// Topics: Greedy, Sorting
// ============================================================================
//
// PROBLEM
//   Job i has a deadline[i] and pays profit[i], but only if it is finished by its deadline.
//   Every job takes exactly 1 unit of time and only one job can run at a time, so the time
//   slots are 1, 2, 3, ... and a job with deadline d may use any slot from 1 to d.
//   Pick the jobs so that the total profit is as large as possible. Return two numbers:
//   how many jobs were done and the total profit, as [count, profit].
//
// EXAMPLES
//   deadline = [4,1,1,1], profit = [20,10,40,30]  ->  [2, 60]
//        three jobs all need slot 1 and only one fits, so keep the richest of them (40)
//        and add the job with deadline 4 (20)
//   deadline = [3,1,2,2], profit = [50,10,20,30]  ->  [3, 100]
//        slots 1, 2, 3 end up holding the jobs worth 20, 30, 50; the job worth 10 (deadline
//        1) finds slot 1 already taken and is left out
//
// CONSTRAINTS
//   1 <= deadline.size() = profit.size() <= 10^5
//   1 <= deadline[i] <= deadline.size()
//   1 <= profit[i] <= 500
//
// INTUITION
//   Time is the scarce thing: there are only D = max(deadline) slots, and a job with
//   deadline d can sit in any free slot 1..d. So look at the jobs from richest to poorest
//   and keep each one if a slot is still free for it - keeping the richest job that still
//   fits never hurts, because a plan that leaves it out could swap it in for a cheaper job
//   without losing profit. A kept job goes into the LATEST free slot at or before its
//   deadline, which leaves the early slots open for jobs with tight deadlines. If every
//   slot from 1 to its deadline is taken, the job is skipped.
//
// APPROACH (as implemented below)
//   1. Wrap every job as Job(jobId = i + 1, deadline, profit) and find maxDeadline.
//   2. Sort the jobs by profit, largest first (equal profit: later deadline first).
//   3. result[] is the timetable: result[t] is the jobId sitting in slot t, 0 = slot free
//      (ids start at 1 so 0 is never a real job). It has maxDeadline + 1 cells, index 0 unused.
//   4. For each job in that order:
//        - if result[deadline] is free, put the job there;
//        - otherwise walk j down from the deadline and take the first free slot;
//        - if no slot is free, the job is dropped.
//      Every placed job adds its profit to maxProfit and 1 to count.
//   5. Return [count, maxProfit].
//
// DRY RUN   main(): deadline = [4,1,1,1], profit = [20,10,40,30]
//   jobs (id, deadline, profit): (1,4,20) (2,1,10) (3,1,40) (4,1,30);  maxDeadline = 4
//   sorted by profit: job 3 (40), job 4 (30), job 1 (20), job 2 (10)
//   result = [_,0,0,0,0]       (cell 0 unused)
//   job 3: slot 1 free               -> result = [_,3,0,0,0]   profit 40, count 1
//   job 4: slot 1 taken, j = 1, 0    -> no free slot, dropped
//   job 1: slot 4 free               -> result = [_,3,0,0,1]   profit 60, count 2
//   job 2: slot 1 taken, nothing below -> dropped
//   return [2, 60]
//   Sliding down, example 2 above: sorted 50(d3) 30(d2) 20(d2) 10(d1) -> 50 takes slot 3,
//   30 takes slot 2, 20 finds slot 2 taken and moves down to slot 1, 10 is dropped -> [3, 100].
//
// COMPLEXITY
//   Time : O(n log n + n * D) - the sort, then each job may scan down through up to D slots.
//          D <= n by the constraints, so the worst case is O(n^2) (for example n jobs that
//          all have deadline n: the k-th job scans k slots).
//   Space: O(n) - the list of Job objects plus the result array (D + 1 cells, D <= n).
//
// GOTCHAS
//   - Slot t lives at result[t], so the array needs maxDeadline + 1 cells; cell 0 is unused.
//   - The while loop starts at j = deadline, a slot already known to be taken, so its first
//     check is redundant (harmless).
//   - The comparator subtracts ints (b.profit - a.profit). That is safe for these limits;
//     with large values use Integer.compare to avoid overflow.
//   - The list is [count, profit] in that order, not [profit, count].
//
// STATUS
//   The code is correct (checked against the three GFG examples). main() calls
//   jobSequencing() but does not print the returned list (it is [2, 60]).
//
// RELATED
//   - LC 1235 Maximum Profit in Job Scheduling - also "pick jobs for the most profit", but
//     jobs are start/end intervals that must not overlap, which needs DP
//     (solved in src/Practice/DynamicProgramming/LC1235MaximumProfitInJobScheduling.java).
//   - LC 630 Course Schedule III - jobs with deadlines again, but each has its own length
//     and the goal is the number of jobs; needs a max-heap instead of fixed slots.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
// ============================================================================
public class GFGJobSequencingProblem {
    public static void main(String[] args) {
        // GFG example 1: prints nothing - the returned list [2, 60] is discarded
        // (job 3 runs in slot 1 for 40, job 1 runs in slot 4 for 20).
        int[] deadline = {4, 1, 1, 1};
        int[] profit = {20, 10, 40, 30};
        jobSequencing(deadline, profit);
    }
    // Returns [number of jobs done, total profit] for the best timetable.
    public static ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int size = deadline.length;
        // step 1: wrap each job; ids start at 1 so that 0 can mean "slot is free" below
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            jobs.add(new Job(i + 1, deadline[i], profit[i]));
        }

        // the latest deadline = the number of time slots that can ever be used
        int maxDeadline = 0;
        for (int d : deadline) {
            maxDeadline = Math.max(maxDeadline, d);
        }

        // step 2: richest job first; equal profit -> later deadline first
        Collections.sort(jobs, (a, b) -> a.profit != b.profit ? b.profit - a.profit : b.deadline - a.deadline);

        // result[t] = id of the job placed in slot t (0 = free); cell 0 is never used
        int[] result = new int[maxDeadline + 1];
        int maxProfit = 0;
        int count = 0;
        for(int i = 0; i < size; i++){
            // step 3: the job's own deadline slot is free -> take it
            if(result[jobs.get(i).deadline] == 0){
                result[jobs.get(i).deadline] = jobs.get(i).jobId;
                maxProfit += jobs.get(i).profit;
                count++;
            }else {
                // step 4: that slot is taken -> walk down to the latest free earlier slot;
                // if the loop runs out of slots the job is simply dropped
                int j = jobs.get(i).deadline;
                while (j > 0){
                    if(result[j] == 0){
                        result[j] = jobs.get(i).jobId;
                        maxProfit += jobs.get(i).profit;
                        count++;
                        break;
                    }
                    j--;
                }
            }
        }
        // step 5: GFG wants [count, profit] in that order
        ArrayList<Integer> res = new ArrayList<>();
        res.add(count);
        res.add(maxProfit);
        return res;
    }
    // one job: its 1-based id, its deadline slot and its profit
    static class Job{
        int jobId;
        int deadline;
        int profit;
        Job(int jobId, int deadline, int profit){
            this.jobId = jobId;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
}

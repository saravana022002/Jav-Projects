package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JobSequencingProblem {
    public static void main(String[] args) {
        int[] deadline = {4, 1, 1, 1};
        int[] profit = {20, 10, 40, 30};
        jobSequencing(deadline, profit);
    }
    public static ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int size = deadline.length;
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            jobs.add(new Job(i + 1, deadline[i], profit[i]));
        }

        int maxDeadline = 0;
        for (int d : deadline) {
            maxDeadline = Math.max(maxDeadline, d);
        }

        Collections.sort(jobs, (a, b) -> a.profit != b.profit ? b.profit - a.profit : b.deadline - a.deadline);

        int[] result = new int[maxDeadline + 1];
        int maxProfit = 0;
        int count = 0;
        for(int i = 0; i < size; i++){
            if(result[jobs.get(i).deadline] == 0){
                result[jobs.get(i).deadline] = jobs.get(i).jobId;
                maxProfit += jobs.get(i).profit;
                count++;
            }else {
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
        ArrayList<Integer> res = new ArrayList<>();
        res.add(count);
        res.add(maxProfit);
        return res;
    }
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

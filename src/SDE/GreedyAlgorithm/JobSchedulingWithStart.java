package SDE.GreedyAlgorithm;

import java.util.*;

public class JobSchedulingWithStart {
    public static void main(String[] args) {
        int[] startTime = {1,2,3,4,6};
        int[] endTime = {3,5,10,6,9};
        int[] profit = {20,20,100,70,60};
        System.out.println(jobScheduling(startTime, endTime, profit));
    }
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int size = endTime.length;
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        jobs.sort(Comparator.comparingInt(a -> a.deadline));

        TreeMap<Integer, Integer> dpMap = new TreeMap<>();
        dpMap.put(0, 0);
        for (Job job : jobs) {
            int val = dpMap.floorEntry(job.startTime).getValue() + job.profit;
            if(val > dpMap.lastEntry().getValue()) {
                dpMap.put(job.deadline, val);
            }
        }
        return dpMap.lastEntry().getValue();
    }

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

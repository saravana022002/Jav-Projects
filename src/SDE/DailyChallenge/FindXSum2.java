package SDE.DailyChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindXSum2 {
    public static long[] findXSum(int[] nums, int k, int x) {
        long[] answer = new long[nums.length - k + 1];

        for(int i = 0; i < answer.length; i++){
            answer[i] = getTopK(nums, i, i + k - 1, x);
        }
        return answer;
    }


    public static long getTopK(int[] nums, int start, int end, int x){
        Map<Integer, Integer> integerMap = new HashMap<>();

        for (int i = start; i <= end; i++) {
            integerMap.put(nums[i], integerMap.getOrDefault(nums[i], 0) + 1);
        }
        Queue<FrequencyMap> list = new PriorityQueue<>((a, b) ->
        {
            if(a.frequency != b.frequency ){
                return Integer.compare(b.frequency, a.frequency);
            }else {
                return Integer.compare(b.value, a.value);
            }
        });

        for (Map.Entry<Integer, Integer> entry : integerMap.entrySet()) {
            list.add(new FrequencyMap(entry.getValue(), entry.getKey()));
        }

        long answer = 0;
        FrequencyMap frequencyMap = null;
        while (!list.isEmpty() && x > 0 ){
            frequencyMap = list.poll();
            answer += (long) frequencyMap.frequency * frequencyMap.value;
            x--;
        }
        return answer;
    }

    static class FrequencyMap{
        int value;
        int frequency;
        FrequencyMap(int frequency, int value){
            this.frequency = frequency;
            this.value = value;
        }
    }
}

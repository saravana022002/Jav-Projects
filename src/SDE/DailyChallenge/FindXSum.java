package SDE.DailyChallenge;

import java.util.*;

public class FindXSum {

    public static void main(String[] args) {

        int[] nums = {9,2,2};
        int k = 3;
        int x = 3;
        System.out.println(Arrays.toString(findXSum(nums, k, x)));
    }
    public static int[] findXSum(int[] nums, int k, int x) {
        int[] answer = new int[nums.length - k + 1];

        for(int i = 0; i < answer.length; i++){
            answer[i] = getTopK(nums, i, i + k - 1, x);
        }
        return answer;
    }


    public static int getTopK(int[] nums, int start, int end, int x){
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

        int answer = 0;
        FrequencyMap frequencyMap = null;
        while (!list.isEmpty() && x > 0 ){
            frequencyMap = list.poll();
            answer = answer + frequencyMap.frequency * frequencyMap.value;
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

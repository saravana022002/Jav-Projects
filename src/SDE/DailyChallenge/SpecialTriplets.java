package SDE.DailyChallenge;

import java.util.HashMap;
import java.util.Map;

public class SpecialTriplets {

    public static void main(String[] args) {
        int[] nums = {0,1,0,0};
        System.out.println(specialTriplets(nums));
    }

//    class Freq{
//        Freq(){
//        }
//       int freqAbove;
//       int freqBelow;
//       void setFreqAbove(int freqAbove){
//          this.freqAbove = freqAbove;
//       }
//        void setFreqBelow(int freqBelow){
//            this.freqBelow = freqBelow;
//        }
//    }
    public static int specialTriplets(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int[] freqAbove = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            freqAbove[i] = freq.get(nums[i] * 2) != null ? freq.get(nums[i] * 2) : 0;
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        Map<Integer, Integer> freq1 = new HashMap<>();
        int[] freqBelow = new int[nums.length];
        for(int i = nums.length - 1; i >= 0; i--){
            freqBelow[i] = freq1.get(nums[i] * 2) != null ? freq1.get(nums[i] * 2) : 0;
            freq1.put(nums[i], freq1.getOrDefault(nums[i], 0) + 1);
        }

        long count = 0;
        long MODULO = 1_000_000_007;
        for(int i = 1; i <= nums.length - 2; i++){
            long frqAbove = freqAbove[i];
            long frqBelow = freqBelow[i];
            if( frqAbove > 0 && frqBelow > 0){
                count = (count + frqAbove * frqBelow) % MODULO;
            }
        }
        return (int) count;
    }
}

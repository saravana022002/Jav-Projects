package SDE.DailyChallenge;

import java.util.HashMap;
import java.util.Map;

public class FruitInBaskets {
    public static void main(String[] args) {
        int[] fruits = {1, 2, 3, 2, 2, 3};
        totalFruit(fruits);
    }

    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> integerMap = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int right = 0; right < fruits.length; right++) {
            integerMap.put(fruits[right],  integerMap.getOrDefault(fruits[right], 0) + 1);
            while (integerMap.size() > 2){
                integerMap.put(fruits[left], integerMap.get(fruits[left]) - 1);
                if(integerMap.get(fruits[left]) == 0){
                    integerMap.remove(fruits[left]);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}

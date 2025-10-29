package SDE.DailyChallenge;

import java.util.Arrays;

public class SuccessfulPairs {
    public static void main(String[] args) {

        int[] spells = {5, 1, 3};
        int[] potions = {1, 2, 3, 4, 5};
        long success = 7;

        int[] result = betterApproach(spells, potions, success);

        System.out.print("Successful Pairs: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        //spells - n
        // potions - m
        // success - atleast
        //return - n sized product array count whose size is greater than success

        long tempsuccess;
        int tempCount = 0;
        int[] result = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            for (int j = 0; j < potions.length; j++) {
                tempsuccess = (long)spells[i] * (long)potions[j];
                if (tempsuccess >= success){
                    tempCount++;
                }
            }
            result[i] = tempCount;
            tempCount = 0;
        }
        return result;
    }

    public static int[] betterApproach(int[] spells, int[] potions, long success) {
        //spells - n
        // potions - m
        // success - atleast
        //return - n sized product array count whose size is greater than success

        Arrays.sort(potions);

        int spellCount = spells.length;
        int[] result = new int[spellCount];

        for (int i = 0; i < spellCount; i++) {
            int idx = binarySearch(spells[i], potions, success);
            result[i] = potions.length - idx;
        }
        return result;
    }

    public static int binarySearch(int spell, int[] potions, long success){
        int left = 0;
        int right = potions.length - 1;
        int mid;
        while(left <= right){
            mid = left + (right - left) / 2;
            long product = (long) potions[mid] * spell;
            if(product < success){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }
}

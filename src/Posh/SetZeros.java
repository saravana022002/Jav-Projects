package Posh;

import java.util.ArrayList;
import java.util.List;

public class SetZeros {
    public static void main(String[] args) {
        int n = 5;
        generate(n);
        int max = Integer.MIN_VALUE;

    }


    public static List<List<Integer>> generate(int N) {
        List<List<Integer>> array = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1); // First element is always 1

            for (int j = 1; j < i; j++) {
                // Use previous row's values to compute next value
                int val = (row.get(j - 1) * (i - j + 1)) / j;
                row.add(val);
            }

            if (i > 0) {
                row.add(1); // Last element is always 1 for i > 0
            }

            array.add(row);
        }

        return array;
    }
}

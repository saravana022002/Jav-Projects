package SDE.Arrays2;

public class MissingRepeatingNumbers {
    public static void main(String[] args) {
        int[] a = {1, 4, 3, 4};
        repeatingAndMissing(a);

        int[][] grid = {{1,3},{2,2}};
        findMissingAndRepeatedValues(grid);

    }

    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        long length = 0;
        long sSumDash = 0;
        long sSum = 0;
        long s2SumDash = 0;
        long s2Sum = 0;

        long sumOfRM = 0;
        long differenceOfRM = 0;
        for (int[] gridRow : grid) {
            for (int j : gridRow) {
                sSumDash += j;
                s2SumDash += (long) j * j;
                length++;
            }
        }

        sSum = ( length * (length + 1) ) / 2;
        s2Sum = ( length * (length + 1) * (2* length + 1) ) / 6;


        long missing = 0;
        long repeating = 0;
        differenceOfRM = sSum - sSumDash;
        sumOfRM = (s2Sum - s2SumDash) / differenceOfRM;
        missing = (differenceOfRM + sumOfRM) / 2;
        repeating = missing - differenceOfRM ;

        int[] ans = new int[2];
        ans[0] = Math.toIntExact(repeating);
        ans[1] = Math.toIntExact(missing);

        return ans;
    }

    private static int[] repeatingAndMissing(int[] a) {
        long length = a.length;
        long sSumDash = 0;
        long sSum = 0;
        long s2SumDash = 0;
        long s2Sum = 0;

        long sumOfRM = 0;
        long differenceOfRM = 0;
        for (int j : a) {
            sSumDash += j;
            s2SumDash += (long) j * j;
        }

        sSum = ( length * (length + 1) ) / 2;
        s2Sum = ( length * (length + 1) * (2* length + 1) ) / 6;


        long missing = 0;
        long repeating = 0;
        differenceOfRM = sSum - sSumDash;
        sumOfRM = (s2Sum - s2SumDash) / differenceOfRM;
        missing = (differenceOfRM + sumOfRM) / 2;
        repeating = missing - differenceOfRM ;

        int[] ans = new int[2];
        ans[0] = Math.toIntExact(repeating);
        ans[1] = Math.toIntExact(missing);

        return ans;
    }
}

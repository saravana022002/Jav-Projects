package SDE.DailyChallenge;

public class NumberOfBeams {
    public static void main(String[] args) {
        String[] banks = {"011001","000000","010100","001000"}; // {3,0,2,1}
        System.out.println(numberOfBeams(banks));
    }

    public static int numberOfBeams(String[] banks) {
        int[] arr = new int[banks.length];
        for(int j = 0; j < banks.length; j++){
            int count = 0;
            for (int i = 0; i < banks[j].length(); i++) {
                if(banks[j].charAt(i) == '1'){
                    count++;
                }
            }
            arr[j] = count;
        }

        int k = 0;
        int m = 0;
        int count = 0;
        for(int j = 0; j < banks.length - 1; j++){
            if(arr[j] != 0){
                k = arr[j];
                while (j + 1 < banks.length - 1 && arr[j + 1] == 0){
                    j++;
                }
                m = arr[j + 1];
                count = count + k * m;
            }
        }
        return count;
    }
}

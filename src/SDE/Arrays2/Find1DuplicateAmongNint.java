package SDE.Arrays2;

import java.util.Arrays;

public class Find1DuplicateAmongNint {
    public static void main(String[] args) {
        int[] arr = {1,3,4,2,2};
        int duplicateWithFrequency = findDuplicateWithFrequency(arr);
        int dupWithSorting = findDuplicateWithSorting(arr);
        int dupWithNoExtraSpace = findDuplicateWithNoExtraSpace(arr);


        System.out.println("The Duplicate Element with frequency,"+duplicateWithFrequency);
        System.out.println("The Duplicate Element with sorting,"+dupWithSorting);
        System.out.println("The Duplicate Element with NoExtraSpace,"+dupWithNoExtraSpace);
    }

    private static int findDuplicateWithNoExtraSpace(int[] arr) {

        int hare = 0;
        int turtle = 0;
        do {
            turtle = arr[turtle];
            hare = arr[arr[hare]];
        } while (turtle != hare);

        hare = 0;

        do {
            turtle = arr[turtle];
            hare = arr[hare];
        } while (turtle != hare);

        return turtle;
    }

    private static int findDuplicateWithFrequency(int[] arr) {
        int size = arr.length;
        int[] freq = new int[size+1];
        for (int i = 0; i < size ; i++){
            if(freq[arr[i]] == 0){
                freq[arr[i]]++;
            }else {
                return arr[i];
            }
        }
        return 0;
    }

    private static int findDuplicateWithSorting(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);

        int comparingElement = arr[0];
        for (int i = 1; i < size; i++) {
            if(comparingElement == arr[i]){
                return comparingElement;
            }else {
                comparingElement = arr[i];
            }
        }
        return 0;
    }

}

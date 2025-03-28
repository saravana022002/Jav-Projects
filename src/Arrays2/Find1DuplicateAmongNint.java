package Arrays2;

public class Find1DuplicateAmongNint {
    public static void main(String[] args) {
        int[] arr = {1,3,4,2,2};
        int dup = findDuplicateWithFrequency(arr);
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

}

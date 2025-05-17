package SDE.Heaps;

public class FindKthLargest {
    public static void main(String[] args) {
        int[] array = {3,2,3,1,2,4,5,5,6};
        int K = 9;

        buildMaxHeap(array);
        findKthLargest(array, K);
    }


    public static int findKthLargest(int[] array, int K){
        int max = 0;
        for (int i = 0; i < K; i++) {
            max = array[0];
            array[0] = array[array.length - i - 1];
            heapify(array, array.length - i - 1, 0);
        }
        return max;
    }
    public static void buildMaxHeap(int[] array){
        for(int i = array.length / 2; i >= 0; i --){
            heapify(array, array.length, i);
        }
    }
    public static void heapify(int[] array, int heapSize, int i){
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = i;
        if(left < heapSize && array[left] > array[i]){
            largest = left;
        }
        if(right < heapSize && array[right] > array[largest]){
            largest = right;
        }
        if(largest != i){
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, heapSize, largest);
        }
    }
}

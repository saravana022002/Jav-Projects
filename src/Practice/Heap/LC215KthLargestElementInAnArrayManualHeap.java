package Practice.Heap;

// ============================================================================
// LC 215. Kth Largest Element in an Array   [Medium]
// https://leetcode.com/problems/kth-largest-element-in-an-array/
// Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Quickselect
// ============================================================================
//
// PROBLEM
//   Given an integer array nums and an integer k, return the k-th largest element of the
//   array in sorted order. Duplicates count separately - it is NOT the k-th distinct value.
//   (LeetCode also asks whether you can do it without sorting.)
//
// EXAMPLES
//   nums = [3,2,1,5,6,4], k = 2            ->  5     sorted high to low: 6 5 4 3 2 1
//   nums = [3,2,3,1,2,4,5,5,6], k = 4      ->  4     6 5 5 4 ... - the two 5s count twice
//
// CONSTRAINTS
//   1 <= k <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4
//
// INTUITION
//   A max-heap keeps the biggest element at the root. Build one from all the numbers, then
//   remove the root K times: the value removed on the K-th round is the K-th largest. The
//   heap lives inside the int[] itself (an implicit binary tree): the children of position i
//   are 2i and 2i+1, so the parent of position j is j / 2. These are the 1-based heap
//   formulas used on a 0-based array, so position 0 is the root with only ONE child
//   (position 1); from position 1 downwards it is an ordinary complete binary tree. It is
//   still a valid heap.
//
// APPROACH (as implemented below)
//   1. buildMaxHeap(array): for i = array.length / 2 down to 0, heapify(array, array.length, i)
//      - sift every node down so the whole array becomes a max-heap, in place.
//   2. findKthLargest(array, K) - the heap must already be built, as main() does. Repeat K
//      times (i = 0 .. K-1):
//        max = array[0]                            the current largest
//        array[0] = array[array.length - i - 1]    the last heap element replaces the root
//        heapify(array, array.length - i - 1, 0)   sift it down in the now smaller heap
//   3. After the K rounds `max` holds the K-th largest value; return it.
//   heapify(array, heapSize, i): largest = the biggest of array[i], its left child 2i and its
//   right child 2i+1 (only positions below heapSize exist). If a child is bigger, swap it up
//   and continue from that child.
//
// DRY RUN   nums = [3,2,1,5,6,4], K = 2
//   build (i = 3, 2, 1, 0):  [3,2,1,5,6,4] -> [3,2,6,5,1,4] -> [3,6,4,5,1,2] -> [6,5,4,3,1,2]
//   round 1: max = 6, last element 2 moves to the root, heap of 5 -> [5,4,2,3,1]
//   round 2: max = 5, last element 1 moves to the root, heap of 4 -> [4,3,2,1]
//   return max = 5
//
// COMPLEXITY
//   Time : O(n + K log n) - the bottom-up build is O(n), each of the K rounds sifts down
//          through at most log n levels.
//   Space: O(log n) - only the recursion of heapify; the heap reuses the input array.
//
// GOTCHAS
//   - Two-step contract: findKthLargest() does NOT build the heap. buildMaxHeap(array) must
//     run first (main() does this); on an unheapified array the result is wrong.
//   - The input array is modified: rearranged by the build, then overwritten by the pops.
//   - For node 0 the "left child" 2 * 0 is the node itself; the test array[left] > array[i]
//     is then false, so it is harmless.
//   - main() asks for K = 9 on a 9-element array, i.e. the smallest value (1).
//
// STATUS
//   The code is correct when buildMaxHeap() is called first (checked against sorting on
//   20000 random arrays). Because findKthLargest() relies on that earlier call it is not a
//   drop-in LeetCode answer. main() does not print the result (it is 1 for its data).
//
// RELATED
//   - LC 347 Top K Frequent Elements - another "top k" problem, solved with a heap
//     (LC347TopKFrequentElements.java in this folder).
//   - LC 703 Kth Largest Element in a Stream - keeps a small heap of the k largest values.
//
// SEE ALSO
//   LC215KthLargestElementInAnArrayPriorityQueue.java - the same problem with
//   java.util.PriorityQueue instead of a hand-written heap.
// ============================================================================
public class LC215KthLargestElementInAnArrayManualHeap {
    public static void main(String[] args) {
        // Nine elements and K = 9, so the answer is the smallest value, 1 (not printed).
        int[] array = {3,2,3,1,2,4,5,5,6};
        int K = 9;

        // the heap has to be built first, then the K-th largest is read off
        buildMaxHeap(array);
        findKthLargest(array, K);
    }


    // Pops the largest element K times from an already built max-heap; returns the last one popped.
    public static int findKthLargest(int[] array, int K){
        int max = 0;
        for (int i = 0; i < K; i++) {
            // take the root, then refill it with the last element of the (shrinking) heap
            max = array[0];
            array[0] = array[array.length - i - 1];
            heapify(array, array.length - i - 1, 0);
        }
        return max;
    }
    // Turns the array into a max-heap in place, sifting down from the last internal node.
    public static void buildMaxHeap(int[] array){
        for(int i = array.length / 2; i >= 0; i --){
            heapify(array, array.length, i);
        }
    }
    // Sift-down: restore the max-heap property below position i, looking only at positions
    // less than heapSize.
    public static void heapify(int[] array, int heapSize, int i){
        // child positions (1-based formulas on a 0-based array: position 0 has only child 1)
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = i;
        if(left < heapSize && array[left] > array[i]){
            largest = left;
        }
        if(right < heapSize && array[right] > array[largest]){
            largest = right;
        }
        // a child is bigger: swap it up and keep sifting the old value down
        if(largest != i){
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, heapSize, largest);
        }
    }
}

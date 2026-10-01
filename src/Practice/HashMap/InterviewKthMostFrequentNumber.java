package Practice.HashMap;

import java.util.*;

// ============================================================================
// Interview - Kth Most Frequent Number in an Array   [Medium]
// Topics: Hash Table, Counting, Bucket Sort
// ============================================================================
//
// PROBLEM
//   You get an int array A and a number k. Count how often each number appears, then rank
//   the DIFFERENT frequencies from highest to lowest - numbers that appear equally often
//   share one rank. Print a number whose frequency holds rank k; when several numbers share
//   that rank, any one of them is accepted. The prompt (kept verbatim above main) does not
//   say what to do when there are fewer than k different frequencies - this code returns 0.
//
// EXAMPLES   (from the prompt)   A = [1,7,7,7,4,4,4,9,9]
//   k = 1  ->  7 or 4    both appear 3 times, the highest frequency
//   k = 2  ->  9         appears 2 times - 7 and 4 used up rank 1 together
//   k = 3  ->  1         appears once
//
// CONSTRAINTS
//   None given in the interview prompt.
//
// INTUITION
//   A frequency is always between 1 and n (the array length), so there is no need to sort
//   by frequency. Make one bucket per possible frequency and drop every distinct number into
//   the bucket of its count. Reading the buckets from the top (count n) down visits the
//   frequency levels from highest to lowest, one non-empty bucket per level - so the k-th
//   non-empty bucket is exactly rank k, and every number in it is a valid answer.
//
// APPROACH (as implemented below)
//   1. Count each value in map (value -> count).
//   2. Create A.length + 2 empty lists in freqencyMapBasedOnCount; list f collects the
//      values that occur exactly f times (a count is at most A.length).
//   3. For every map entry, add the key to the list at index value (its count).
//   4. Walk i from A.length + 1 down to 0 and bump count for every non-empty list.
//      As soon as count == k, take the first value of list i as topKth and stop.
//   5. Return topKth - it keeps its start value 0 when there are fewer than k levels.
//
// DRY RUN   main(): A = [1,7,7,4,4,9,9], k = 3
//   counts   map = {1=1, 4=2, 7=2, 9=2}
//   buckets  [2] = [4,7,9], [1] = [1], all other lists empty   (indices 0..8)
//   walk     i=8..3 empty    -> count 0
//            i=2 non-empty   -> count 1
//            i=1 non-empty   -> count 2
//            i=0 empty       -> loop ends, count never reached 3
//   returns 0 (there is no third level). k = 1 would return 4, k = 2 would return 1.
//
// COMPLEXITY
//   Time : O(n) - one counting pass, n + 2 list creations, one pass over the distinct values
//          and one walk over the n + 2 buckets; nothing is sorted
//   Space: O(n) - the map and the buckets each hold at most n distinct values
//
// GOTCHAS
//   - Ranks count distinct frequencies, not positions in a sorted list: in the example,
//     9 is rank 2 even though two numbers (7 and 4) appear more often.
//   - Which tied value comes back depends on HashMap iteration order (4 before 7 above).
//   - Fewer than k levels returns 0, which cannot be told apart from a real answer of 0.
//   - count == k is tested even on empty buckets, so k = 0 would call get(0) on an empty
//     list and throw. k is 1-based.
//
// SOURCE
//   Source: Interview question (no public link). Difficulty is an estimate. The statement
//   pasted in the file (above main) is the source.
//   Closest LeetCode problem: LC 347 Top K Frequent Elements
//
// RELATED
//   - LC 347 Top K Frequent Elements (src/Practice/Heap/LC347TopKFrequentElements.java) -
//     returns the k most frequent values; the same frequency buckets solve it in O(n).
// ============================================================================
public class InterviewKthMostFrequentNumber {


    // Original interview prompt, kept verbatim (it is the only copy of the problem):
//    Print the k'th most frequent number from the array A.
//    input: A = [1, 7, 7, 7, 4, 4, 4, 9, 9]
//    k = 1, ouptut: 7 (or) 4
//    k = 2, ouptut: 9
//    k = 3, ouptut: 1
    public static void main(String[] args) {
        // This array has only two frequency levels (2 and 1), so rank k = 3 does not exist.
        // Prints 0 (the "not found" value). The prompt's array with k = 3 would print 1.
        int[] A = {1, 7, 7, 4, 4, 9, 9};
        int k =  3;
        System.out.println(giveTopKElements(A, k));
    }

    // Returns a value whose frequency is the k-th highest DISTINCT frequency in A,
    // or 0 when A has fewer than k distinct frequencies.
    private static int giveTopKElements(int[] A, int k) {
        // step 1: value -> number of occurrences
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        // step 2: list f will hold every value that occurs exactly f times.
        // A count is at most A.length, so indices 0..A.length+1 are always enough.
        List<List<Integer>> freqencyMapBasedOnCount = new ArrayList<>();

        for (int i = 0; i < A.length + 2; i++) {
            freqencyMapBasedOnCount.add(new ArrayList<>());
        }
        // step 3: drop each distinct value into the bucket of its count
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            freqencyMapBasedOnCount.get(value).add(key);
        }

        // stays 0 if rank k is never reached
        int topKth = 0;

        // how many frequency levels we have passed so far
        int count = 0;

        // step 4: highest count first; every non-empty bucket is the next level,
        // and all values inside it share that level
        for (int i = A.length + 1; i >= 0; i--) {
            if (!freqencyMapBasedOnCount.get(i).isEmpty()) {
                count++;
            }
            // k-th level reached: any value in this bucket is an answer, take the first
            if(count == k){
                topKth = freqencyMapBasedOnCount.get(i).get(0);
                break;
            }
        }

        return topKth;
    }


}

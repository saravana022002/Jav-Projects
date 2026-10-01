package Practice.Heap;

import java.util.*;

// ============================================================================
// LC 295. Find Median from Data Stream   [Hard]
// https://leetcode.com/problems/find-median-from-data-stream/
// Topics: Two Pointers, Design, Sorting, Heap (Priority Queue), Data Stream
// ============================================================================
//
// PROBLEM
//   Design a MedianFinder that receives numbers one at a time. addNum(num) adds a number;
//   findMedian() returns the median of everything added so far - the middle value of the
//   sorted numbers, or the average of the two middle values when the count is even.
//   Answers within 10^-5 are accepted.
//
// EXAMPLES
//   addNum(1), addNum(2), findMedian()  ->  1.5     (1 + 2) / 2
//   addNum(3), findMedian()             ->  2.0     sorted [1,2,3], the middle is 2
//   (main() replays a longer command list and prints a median after every addNum.)
//
// CONSTRAINTS
//   -10^5 <= num <= 10^5
//   findMedian is only called when at least one number has been added
//   at most 5 * 10^4 calls to addNum and findMedian in total
//
// INTUITION
//   The median is the boundary between the smaller half and the larger half of the numbers,
//   so the full sorted order is not needed. Keep the smaller half in a MAX-heap (its top is
//   the largest of the small numbers) and the larger half in a MIN-heap (its top is the
//   smallest of the large numbers), and keep their sizes within 1 of each other. Then the
//   median is the top of the bigger heap, or the average of the two tops when the sizes are
//   equal. A new number goes to the correct half, and at most one number moves across to
//   restore the balance - so each insert is O(log n) and reading the median is O(1).
//
// APPROACH (as implemented below)
//   1. The constructor creates smallHeap = PriorityQueue(Collections.reverseOrder()) - a
//      max-heap for the lower half - and largeHeap = PriorityQueue() - a min-heap for the
//      upper half.
//   2. addNum(num): if smallHeap is empty or num <= smallHeap.peek(), num belongs to the lower
//      half, so smallHeap.add(num); otherwise largeHeap.add(num). Then rebalance: if smallHeap
//      has more than one element extra, move its top to largeHeap; else if largeHeap has more
//      than one extra, move its top to smallHeap.
//   3. findMedian(): with an even total, return (smallHeap.peek() + largeHeap.peek()) / 2.0;
//      with an odd total, return the top of whichever heap is bigger.
//
// DRY RUN   addNum(1), addNum(2), findMedian(), addNum(3), findMedian()
//   add 1   smallHeap empty -> smallHeap {1}                   sizes 1 / 0
//   add 2   2 <= 1? no -> largeHeap {2}                        sizes 1 / 1
//   median  total 2 is even -> (1 + 2) / 2.0 = 1.5
//   add 3   3 <= 1? no -> largeHeap {2,3}                      sizes 1 / 2 (differ by 1, ok)
//   median  total 3 is odd, largeHeap is bigger -> its top 2 -> 2.0
//
// COMPLEXITY
//   addNum    : O(log n) - one heap insert plus at most one poll/insert to rebalance
//   findMedian: O(1) - only peeks at the tops
//   Space     : O(n) - every number is stored in one of the two heaps
//
// GOTCHAS
//   - The names are easy to mix up: smallHeap holds the SMALL numbers but is a max-heap;
//     largeHeap holds the LARGE numbers and is a min-heap. The tops are the two middle values.
//   - With an odd count the median comes from the bigger heap, which can be either one.
//   - (a + b) / 2.0 adds two ints first (fine for |num| <= 10^5) and divides as a double, so
//     the .5 is kept.
//   - The field `List<Integer> allInt` is declared but never used.
//
// STATUS
//   The code is correct (checked against a sorted-list median on random streams). main()
//   prints 11 medians: 6.0, 8.0, 6.0, 6.0, 6.0, 5.5, 6.0, 5.5, 5.0, 4.0, 3.0.
//
// RELATED
//   - LC 480 Sliding Window Median - the same two-heap median, with numbers also leaving.
//   - LC 703 Kth Largest Element in a Stream - another heap over a data stream.
// ============================================================================
public class LC295FindMedianFromDataStream {

    public static void main(String[] args) {
        // Replays a LeetCode-style command list: create the MedianFinder, then add
        // 6, 10, 2, 6, 5, 0, 6, 3, 1, 0, 0 and print the median after each add.
        MedianFinder medianFinder = null;

        String[] commands = {
                "MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian",
                "addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian",
                "addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"
        };

        int[][] parameters = {
                {}, {6}, {}, {10}, {}, {2}, {}, {6}, {}, {5}, {}, {0}, {}, {6}, {}, {3}, {}, {1}, {}, {0}, {}, {0}, {}
        };

        for (int i = 0; i < commands.length; i++) {
            String cmd = commands[i];

            switch (cmd) {
                case "MedianFinder":
                    medianFinder = new MedianFinder();
                    break;
                case "addNum":
                    medianFinder.addNum(parameters[i][0]);
                    break;
                case "findMedian":
                    System.out.println("Median: " + medianFinder.findMedian());
                    break;
            }
        }
    }

    // The data structure the problem asks for (nested here; on LeetCode it is a top-level class).
    static class MedianFinder {

        // unused
        List<Integer> allInt;

        // smallHeap: max-heap with the smaller half; largeHeap: min-heap with the larger half
        PriorityQueue<Integer> smallHeap;
        PriorityQueue<Integer> largeHeap;
        public MedianFinder() {
            smallHeap = new PriorityQueue<>(Collections.reverseOrder());
            largeHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // put the number into the half it belongs to
            if(smallHeap.isEmpty() || num <= smallHeap.peek()){
                smallHeap.add(num);
            }else{
                largeHeap.add(num);
            }
            // rebalance: the heap sizes may differ by at most one
            if(smallHeap.size() > largeHeap.size() + 1){
                largeHeap.add(smallHeap.poll());
            }else if(smallHeap.size() + 1 < largeHeap.size()){
                smallHeap.add(largeHeap.poll());
            }
        }

        public double findMedian() {
            int totalSize = smallHeap.size() + largeHeap.size();
            if (totalSize % 2 == 0) {
                // even count: the two middle values are the two tops
                return (smallHeap.peek() + largeHeap.peek()) / 2.0;
            }else {
                // odd count: the middle value is the top of the bigger heap
                if(smallHeap.size() > largeHeap.size()){
                    return smallHeap.peek();
                }else{
                    return largeHeap.peek();
                }
            }
        }
    }
}

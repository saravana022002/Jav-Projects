package SDE.Heaps;

import java.util.*;

public class FindMedianFromDataStream {

    public static void main(String[] args) {
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

    static class MedianFinder {

        List<Integer> allInt;

        PriorityQueue<Integer> smallHeap;
        PriorityQueue<Integer> largeHeap;
        public MedianFinder() {
            smallHeap = new PriorityQueue<>(Collections.reverseOrder());
            largeHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(smallHeap.isEmpty() || num <= smallHeap.peek()){
                smallHeap.add(num);
            }else{
                largeHeap.add(num);
            }
            if(smallHeap.size() > largeHeap.size() + 1){
                largeHeap.add(smallHeap.poll());
            }else if(smallHeap.size() + 1 < largeHeap.size()){
                smallHeap.add(largeHeap.poll());
            }
        }

        public double findMedian() {
            int totalSize = smallHeap.size() + largeHeap.size();
            if (totalSize % 2 == 0) {
                return (smallHeap.peek() + largeHeap.peek()) / 2.0;
            }else {
                if(smallHeap.size() > largeHeap.size()){
                    return smallHeap.peek();
                }else{
                    return largeHeap.peek();
                }
            }
        }
    }
}

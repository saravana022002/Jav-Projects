package Leetcode.FrequencyAndMultisetResoning4;

import java.util.*;

public class LC347TopKFrequent {

//    347. Top K Frequent Elements
//            Medium
//    Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,1,2,2,3], k = 2
//
//    Output: [1,2]
//
//    Example 2:
//
//    Input: nums = [1], k = 1
//
//    Output: [1]
//
//    Example 3:
//
//    Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
//
//    Output: [1,2]
//
//
//
//    Constraints:
//
//            1 <= nums.length <= 105
//            -104 <= nums[i] <= 104
//    k is in the range [1, the number of unique elements in the array].
//    It is guaranteed that the answer is unique.
//
//
//    Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
    public static void main(String[] args) {
        int[] nums = {1,2,1,2,1,2,3,1,3,2};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] buckets = new List[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()){
            if(buckets[mapEntry.getValue()] == null){
                buckets[mapEntry.getValue()] = new ArrayList<>();
            }
            buckets[mapEntry.getValue()].add(mapEntry.getKey());
        }


        List<Integer> arraylist = new ArrayList<>();
        int count = 0;
        for (int i = buckets.length - 1; i > 0; i--) {
            if(buckets[i] == null){
                continue;
            }
            for(int l : buckets[i]) {
                count++;
                if (count <= k) {
                    arraylist.add(l);
                }
            }
        }
        return arraylist.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}

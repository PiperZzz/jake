package spring.code.jake.myleetcode;

import java.util.*;

public class MyMaps {

    /* 1. Two Sum
     * Given an array of integers, return indices of the two numbers such that they
     * add up to a specific target.
     * 
     * You may assume that each input would have exactly one solution, and you may
     * not use the same element twice.
     * 
     * Example:
     * 
     * Given nums = [2, 7, 11, 15], target = 9,
     * 
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */
    public static int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        Map<Integer, Integer> mapDiffIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i]; // calculate the difference

            if (mapDiffIndex.containsKey(diff)) { // diff exists in key
                result[0] = i; // current index
                result[1] = mapDiffIndex.get(diff); // the index of the diff
                return result;
            } else {
                mapDiffIndex.put(nums[i], i); // diff doesn't exist in key, put it into the map
            }
        }

        return result;
    }

    /* 347. Top K Frequent Elements
     * Given a non-empty array of integers, return the k most frequent elements.
     * 
     * Example 1:
     * 
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * Example 2:
     * 
     * Input: nums = [1], k = 1
     * Output: [1]
     * Note:
     * 
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Your algorithm's time complexity must be better than O(n log n), where n is
     * the array's size.
     */
    public static List<Integer> findTopKFrequency(int[] nums, int k) {

        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> mapNumFrequency = new HashMap<>();

        for (int i : nums) {
            mapNumFrequency.put(i, mapNumFrequency.getOrDefault(i, 0) + 1);
        }

        // numbers with the same frequency in one list, frequency as key
        TreeMap<Integer, List<Integer>> mapFrequencyNums = new TreeMap<>();

        mapNumFrequency.forEach((num, frequency) -> {
            mapFrequencyNums.computeIfAbsent(frequency, key -> new ArrayList<>()).add(num);
        });

        // keep adding numbers until there are k or greater numbers in the list
        while (result.size() < k) {
            result.addAll(mapFrequencyNums.pollLastEntry().getValue());
        }

        return result;
    }
}

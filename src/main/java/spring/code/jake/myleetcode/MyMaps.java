package spring.code.jake.myleetcode;

import java.util.*;

public class MyMaps {

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

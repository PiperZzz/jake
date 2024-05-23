package spring.code.jake.myleetcode;

public class MySearches {
    
    public int binarySearch(int[] nums, int target) {
        // nums is a sorted array and return the index of the target number

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到目标元素，返回索引
            } else if (nums[mid] < target) {
                left = mid + 1; // 目标元素在右侧，更新左边界
            } else {
                right = mid - 1; // 目标元素在左侧，更新右边界
            }
        }

        return -1; // 没有找到目标元素，返回 -1
    }
}

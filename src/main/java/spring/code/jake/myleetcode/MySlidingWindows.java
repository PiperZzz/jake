package spring.code.jake.myleetcode;

public class MySlidingWindows {
    
    public static int maxSubArray(int[] nums) {

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            // currentSum is negative: start over
            // currentSum is positive: continue
            maxSum = Math.max(maxSum, currentSum); // tracking maxSum
        }

        return maxSum;
    }

    public static int maxStockProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) { // 遍历方向要保证最大价差是低买高卖产生的，而不是反向的。
            minPrice = Math.min(minPrice, price); // Record the all time low price
            maxProfit = Math.max(maxProfit, price - minPrice); // Max Profit 和 Current Price - Min Price
            // 需要分开记录，更新最低股价并不会立即影响最大利润
        }

        return maxProfit;
    }
}

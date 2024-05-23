package spring.code.jake.myleetcode;

import java.util.Arrays;

public class MyDP {
    public static int minCoinsChange(int[] coins, int amountTarget) {

        int[] dp = new int[amountTarget + 1]; // index is current amountTarget, value is minimum number of coins to make change for that amountTarget
    
        Arrays.fill(dp, Integer.MAX_VALUE);
    
        dp[0] = 0;
        
        for (int coin : coins) { // outter loop for each coin
            for (int i = coin; i <= amountTarget; i++) { // inner loop for each amountTarget
                if (dp[i - coin] != Integer.MAX_VALUE) { // 
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); //
                }
            }
        }
    
        return dp[amountTarget] == Integer.MAX_VALUE ? -1 : dp[amountTarget]; //
    }
}

package spring.code.jake.myleetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyDPTests {

    @Test
    public void testMinCoinsChange() {
        // Test case 1: coins = [1, 2, 5], amountTarget = 11
        int[] coins1 = {1, 2, 5};
        int amountTarget1 = 11;
        assertEquals(3, MyDP.minCoinsChange(coins1, amountTarget1)); // Explanation: 11 = 5 + 5 + 1

        // Test case 2: coins = [2], amountTarget = 3
        int[] coins2 = {2};
        int amountTarget2 = 3;
        assertEquals(-1, MyDP.minCoinsChange(coins2, amountTarget2)); // Explanation: Not possible to make 3 with only 2

        // Test case 3: coins = [1, 2, 5], amountTarget = 0
        int[] coins3 = {1, 2, 5};
        int amountTarget3 = 0;
        assertEquals(0, MyDP.minCoinsChange(coins3, amountTarget3)); // Explanation: No coins needed to make 0

        // Test case 4: coins = [1], amountTarget = 1
        int[] coins4 = {1};
        int amountTarget4 = 1;
        assertEquals(1, MyDP.minCoinsChange(coins4, amountTarget4)); // Explanation: 1 = 1

        // Test case 5: coins = [1], amountTarget = 2
        int[] coins5 = {1};
        int amountTarget5 = 2;
        assertEquals(2, MyDP.minCoinsChange(coins5, amountTarget5)); // Explanation: 2 = 1 + 1
    }
}
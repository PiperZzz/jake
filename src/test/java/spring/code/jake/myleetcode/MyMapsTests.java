package spring.code.jake.myleetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MyMapsTests {
    
    @Test
    void testTwoSum() {
        var nums = new int[] {2, 7, 11, 15};
        var target = 9;
        var expected = new int[] {0, 1};
        var actual = MyMaps.twoSum(nums, target);
        Arrays.sort(actual);
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
    }
}

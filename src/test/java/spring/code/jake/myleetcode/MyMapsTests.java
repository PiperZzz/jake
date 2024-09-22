package spring.code.jake.myleetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testTopKFrequentElements() {
        var nums = new int[] {1, 1, 1, 2, 2, 3};
        var k = 2;
        var expected = Arrays.asList(1, 2);
        var actual = MyMaps.topKFrequencyElements(nums, k);
        assertEquals(expected, actual);
    }
}

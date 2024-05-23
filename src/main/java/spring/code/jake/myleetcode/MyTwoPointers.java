package spring.code.jake.myleetcode;

public class MyTwoPointers {

    public static void moveZeroesToEnd(int[] nums) {
        int nonZeroIndex = 0; // 标记最后一个non-zero元素位置

        for (int i = 0; i < nums.length; i++) { // 本质就是non-zero指针和遍历指针同向移动
            if (nums[i] != 0) {
                nums[nonZeroIndex++] = nums[i]; // 由于non-zero指针不可能比遍历指针移动得更快，所以覆盖的元素一定是以前遍历过的。
            }
        }

        for (int i = nonZeroIndex; i < nums.length; i++) {
            nums[i] = 0; // fill the rest of the array with zeros
        }
    }

    public static int[] mergeSortedArrays(int[] array1, int[] array2) {

        int[] arrayMerged = new int[array1.length + array2.length];
        
        // Two Pointers for each merging array
        int index1 = 0;
        int index2 = 0;
        int indexMerged = 0;
        
        while (index1 < array1.length && index2 < array2.length) { // Either one array has no element to merge, loop will stop
            if (array1[index1] < array2[index2]) {
                arrayMerged[indexMerged++] = array1[index1++];
            } else {
                arrayMerged[indexMerged++] = array2[index2++];
            }
        }
        
        // Only one of the two while loops will be executed
        while (index1 < array1.length) {
            arrayMerged[indexMerged++] = array1[index1++];
        }
        while (index2 < array2.length) {
            arrayMerged[indexMerged++] = array2[index2++];
        }
        
        return arrayMerged;
    }
}

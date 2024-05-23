package spring.code.jake.myleetcode;

import java.util.Arrays;

public class MyStrings {

    public static boolean areAnagrams(String str1, String str2) {
        // If the lengths of the strings are not same, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Clean up or Make assumption
        str1 = str1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        str2 = str2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        char[] charArray1 = str1.toCharArray();
        Arrays.sort(charArray1);

        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }

    public static boolean isPalindrome(String str) {
        // Clean up or Make assumptions
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Two Pointers for iterating from start and end
        int left = 0;
        int right = str.length() - 1;

        while (left < right) { // if two pointers have not met
            if (str.charAt(left) != str.charAt(right)) { // then check difference
                return false; // return false if they are different
            }
            // move two pointers towards until they meet
            left++;
            right--;
        }

        return true;
    }
}

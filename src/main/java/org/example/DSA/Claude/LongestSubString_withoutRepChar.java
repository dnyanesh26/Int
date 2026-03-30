package org.example.DSA.Claude;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubString_withoutRepChar {

    /*
    Find longest substring without repeating characters.
    Pattern: Sliding window with set
     */

    public int lengthOfLongestSubstringNaive(String s) {
        int n = s.length();
        int maxLength = 0;

        // Try all possible substrings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i);
                }
            }
        }

        return maxLength;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

    public int lOLSOptimized(String s){

        Set<Character> set = new HashSet<>();
        int left=0, maxLen=0;

        for(int right=0;right<s.length();right++){
            while(set.contains(s.charAt(right))){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }

    public int lOLSOptimal(String s){

        Map<Character, Integer> map = new HashMap<>();
        int left=0, maxLen=0;

        for(int right=0;right<s.length();right++){

            if(map.containsKey(s.charAt(right))){
                left=Math.max(left,map.get(s.charAt(right))+1);


            }
            map.put(s.charAt(right),right);
            maxLen=Math.max(maxLen,right-left+1);
        }
        return maxLen;
    }



    public static void main(String[] args) {
        LongestSubString_withoutRepChar sol = new LongestSubString_withoutRepChar();
        System.out.println(sol.lOLSOptimal("abcabcbb")); // 3
        System.out.println(sol.lOLSOptimal("bbbbb"));    // 1
        System.out.println(sol.lOLSOptimal("pwwkew"));   // 3
    }
}

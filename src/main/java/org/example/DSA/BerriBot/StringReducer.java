package org.example.DSA.BerriBot;

import java.util.Stack;

public class StringReducer {
    public static int minRemainingLength(String seq) {
        Stack<Character> stack = new Stack<>();

        for (char c : seq.toCharArray()) {
            // Check if the current character is 'B' and the stack is not empty
            // Since we can delete "AB" and "BB", any 'B' can remove the previous character
            if (c == 'B' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

    public static void main(String[] args) {
        String input = "BABBA";
        System.out.println("Minimum Length: " + minRemainingLength(input));
    }
}

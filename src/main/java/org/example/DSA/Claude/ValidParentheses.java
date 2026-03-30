package org.example.DSA.Claude;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

/*
    Check if parentheses are valid.
    Pattern: Stack with matching pairs
 */

    public boolean isValidNaive(String s) {
        // Keep removing valid pairs until no more can be removed
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        return s.isEmpty();
    }

    public boolean isValidOptimized(String s){
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();

        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        for(Character c:s.toCharArray()){
            if(map.containsKey(c)){
                char temp = stack.isEmpty() ? '#' : stack.pop();
                if(temp != map.get(c)){
                    return false;
                }
            }else{
                if(map.containsValue(c)){
                    stack.push(c);
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses sol = new ValidParentheses();
        System.out.println(sol.isValidOptimized("()[]{}")); // true
        System.out.println(sol.isValidOptimized("(]"));     // false
        System.out.println(sol.isValidOptimized("([)]"));   // false

        System.out.println(sol.isValidOptimized("({[](a){d[(fc)]ss}})"));
    }
}

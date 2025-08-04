package org.example.Arr;

import java.util.Scanner;
import java.util.Stack;

public class Paranthesis {

    public static void main(String[] args) {

//        String eq = null;
//        Scanner sc = new Scanner(System.in);
//        eq = sc.nextLine();
        String[] arr = new String[]{"", "()", "{([])}", "a(b[c]d)e", "(1 + [2 * (3 - 4)] / 5)",
        "(", "[[[{", "]]])}", "{([])}{()"};
//        String[] arr = new String[]{"(1 + [2 * (3 - 4)] / 5)"};
        long startarr=System.nanoTime();
        for(String eq: arr) {

            checkParanthesisArr(eq);

        }
        long endarr=System.nanoTime();
        System.out.println(endarr-startarr);

        long startstack=System.nanoTime();
        for(String eq: arr) {

            checkParanthesisStack(eq);
        }
        long endstack=System.nanoTime();
        System.out.println(endstack-startstack);

    }

    private static String checker(String eq,char bracket, int i) {
        for(int x=i;x>=0;x--){
            if(eq.charAt(i)==')'){
                if(eq.charAt(x)=='{' || eq.charAt(x)=='['){
                    return "false";
                } else if (eq.charAt(x)=='(') {
                    StringBuilder eqSB = new StringBuilder(eq);
                    eqSB.setCharAt(i,'#');
                    eqSB.setCharAt(x,'#');
                    eq=eqSB.toString();
                    return eq;
                }
            }

            if(eq.charAt(i)=='}'){
                if(eq.charAt(x)=='(' || eq.charAt(x)=='['){
                    return "false";
                }
                else if (eq.charAt(x)=='{') {
                    StringBuilder eqSB = new StringBuilder(eq);
                    eqSB.setCharAt(i,'#');
                    eqSB.setCharAt(x,'#');
                    eq=eqSB.toString();
                    return eq;
                }
            }

            if(eq.charAt(i)==']'){
                if(eq.charAt(x)=='{' || eq.charAt(x)=='('){
                    return "false";
                }
                else if (eq.charAt(x)=='[') {
                    StringBuilder eqSB = new StringBuilder(eq);
                    eqSB.setCharAt(i,'#');
                    eqSB.setCharAt(x,'#');
                    eq=eqSB.toString();
                    return eq;
                }
            }
        }
        return "false";
    }

    private static boolean checkParanthesisArr(String eq) {

        int counter=0;

        for(int i=0; i<eq.length();i++){
            if(eq.charAt(i)=='(' || eq.charAt(i)=='{' || eq.charAt(i)=='[') {
                counter++;
            }

            if(eq.charAt(i)==')' || eq.charAt(i)=='}' || eq.charAt(i)==']'){
                String valid = checker(eq,eq.charAt(i),i);
                if(valid.equals("false")){
                    return false;

                }
                else{
                    eq=valid;
                    counter--;
                }
            }
        }
        if(counter==0) {
            return true;
        }
        else{
            return false;
        }

    }




    private static boolean checkParanthesisStack(String eq) {
        char[] EQ = eq.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char sym: EQ){
            if(sym=='(' || sym=='{' || sym=='['){
                stack.push(sym);
            }
            else if(sym==')' || sym=='}' || sym==']'){
                if(stack.empty())
                    return false;
                char comp = stack.pop();
                if(sym==')' && comp!='('){
                    return false;
                }
                if(sym=='}' && comp!='{'){
                    return false;
                }
                if(sym==']' && comp!='['){
                    return false;
                }

            }
        }
        if(stack.empty()){
            return true;
        }
        else{
            return false;
        }
    }
}

package org.example.DSA.Wissen;

public class ReverseString {

    public static String reverseStr(String input){

        StringBuilder output = new StringBuilder();
        for(int i=input.length()-1;i>=0;i--){

            output.append(input.charAt(i));

        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseStr("Hello_123"));
    }
}

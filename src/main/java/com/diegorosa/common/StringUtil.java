package com.diegorosa.common;

import java.util.Stack;

public class StringUtil {

    public static String reverse(String string) {
        char[] chars = string.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c: chars) {
            stack.push(c);
        }

        StringBuilder strb = new StringBuilder();
        while(!stack.isEmpty()) {
            char last = stack.pop();
            strb.append(last);
        }

        return strb.toString();
    }
}

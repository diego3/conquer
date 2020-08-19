package com.diegorosa.common;

import java.util.Stack;

public class MaskUtil {

    public String maskCnpj(String valor) {
        if (valor == null || !valor.matches("\\d*")) {
            return "";
        }
        String reversed = reverse(valor);
        char[] chars = reversed.toCharArray();
        StringBuilder str = new StringBuilder();
        for(int i=0; i < chars.length; i++) {
            if (i == 2) {
                str.append('-');
            }
            if (i == 6) {
                str.append('/');
            }
            if (i > 6 && i % 3 == 0) {
                str.append('.');
            }
            str.append(chars[i]);
        }

        return reverse(str.toString());
    }

    private String reverse(String str) {
        char[] chars = str.toCharArray();
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

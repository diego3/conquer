package com.diegorosa.common;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MaskUtil {
    private final DecimalFormat reaisFormat;

    public MaskUtil() {
        this.reaisFormat = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
    }

    public String maskCnpj(String valor) {
        if (valor == null || !valor.matches("\\d*")) {
            return "";
        }
        String reversed = StringUtil.reverse(valor);
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

        return StringUtil.reverse(str.toString());
    }

    public String maskMoedaReais(Double valor) {
        return "R$ "+reaisFormat.format(valor);
    }
}

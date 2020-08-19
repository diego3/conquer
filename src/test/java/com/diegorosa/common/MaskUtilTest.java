package com.diegorosa.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaskUtilTest {

    @Test
    void maskCnpj() {
        MaskUtil mask = new MaskUtil();

        assertEquals("123.456/0001-10", mask.maskCnpj("123456000110"));
        assertEquals("1.234.567/0001-10", mask.maskCnpj("1234567000110"));
        assertEquals("12.345.678/0001-10", mask.maskCnpj("12345678000110"));
        assertEquals("123.456.789/0001-10", mask.maskCnpj("123456789000110"));
    }

    @Test
    void maskCnpjBadInputs() {
        MaskUtil mask = new MaskUtil();

        assertEquals("", mask.maskCnpj(""));
        assertEquals("", mask.maskCnpj(null));
        assertFalse("STRING-LITERALS".matches("\\d*"));
        assertTrue("123456000110".matches("\\d*"));
        assertEquals("", mask.maskCnpj("STRINGS-SHOULD-RETURN-EMPTY"));
    }
}
package com.insoul.copartner.util;

import java.util.Random;

public class CodeUtil {

    public static String genBarcode(int length) {
        String[] chars = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2",
                "3", "4", "5", "6", "7", "8", "9", "!", "@", "#", "$",
                "%", "^", "&", "*", "(", ")", "-", "_", "[", "]", "{",
                "}", "<", ">", "~", "`", "+", "=", ",", ".", ";", ":",
                "/", "?", "|"
        };

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0, charsLen = chars.length; i < length; i++) {
            int number = random.nextInt(charsLen);
            sb.append(chars[number]);
        }

        return sb.toString();
    }

    public static String genNumcode(int length) {
        String chars = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0, charsLen = chars.length(); i < length; i++) {
            sb.append(chars.charAt(random.nextInt(charsLen)));
        }
        return sb.toString();
    }

    public static String genVerfyCode(int length, boolean needAlpha) {
        String chars = "123456789";
        if (needAlpha) {
            chars = chars.concat("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int charsLen = chars.length();
        sb.append(chars.charAt(random.nextInt(charsLen)));
        chars = chars.concat("0");
        length -= 1;
        charsLen += 1;
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(charsLen)));
        }

        return sb.toString();
    }
}

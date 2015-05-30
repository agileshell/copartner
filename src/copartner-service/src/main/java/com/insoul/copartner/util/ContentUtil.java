package com.insoul.copartner.util;

public class ContentUtil {
    public static String splitAndFilterString(String input, int length) {
        if (input == null || input.trim().equals("")) {
            return "";
        }

        // 去掉所有html元素
        String str = input.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");

        int len = str.length();

        if (len <= length) {
            return str;
        } else {
            str = str.substring(0, length);
            str += "......";
        }

        return str;
    }
}

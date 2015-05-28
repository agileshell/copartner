package com.insoul.copartner.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public final class ValidationUtil {

    private ValidationUtil() {
        // empty
    }

    public static boolean isNumerical(final String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isEmail(final String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }

        String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))"
                + "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern mailPattern = Pattern.compile(emailPattern);
        Matcher mailMatcher = mailPattern.matcher(email);
        return mailMatcher.matches();
    }

    public static boolean isMobilePhoneNumber(final String mobilePhoneNumber) {
        if (StringUtils.isEmpty(mobilePhoneNumber)) {
            return false;
        }

        String phonePatternStr = "^1[0-9]{10}$";
        Pattern phonePattern = Pattern.compile(phonePatternStr);
        Matcher phoneMatcher = phonePattern.matcher(mobilePhoneNumber);
        return phoneMatcher.matches();
    }

    public static boolean isFullURL(final String url) {
        boolean flag = false;
        if (StringUtils.isNotBlank(url)) {
            flag = url.toLowerCase().startsWith("http");
        }
        return flag;
    }

}

package com.hospital.adapt.utils;

public class Str {
    public static boolean isNull(String str) {
        return (null == str
                || str.trim().length() == 0
                || str.contentEquals("null")
                || str.contentEquals("NULL"));
    }

    public static boolean notNull(String str) {
        return !isNull(str);
    }

    public static String chkNull(String str) {
        String result = null;

        if (null != str) {
            if (str.trim().length() == 0
                    || str.equals("null")
                    || str.equals("NULL")) {
                result = null;
            } else {
                result = str.trim();
            }
        }

        return result;
    }
}

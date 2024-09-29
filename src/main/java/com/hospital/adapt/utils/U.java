package com.hospital.adapt.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class U {
    private static String CHARSET = "UTF-8";
    private static final Logger log = LoggerFactory.getLogger(U.class);

    public static String get(String key, Map<String, String[]> param) {
        return get(key, param, null);
    }

    public static String get(String key, Map<String, String[]> param, String dft) {
        String result = null;

        if (Str.notNull(key) && null != param && param.size() > 0) {
            String[] vs = param.get(key);
            if (null != vs && vs.length > 0) {
                result = vs[0];
            }
        }

        if (Str.isNull(result)) result = dft;

        return result;
    }
}

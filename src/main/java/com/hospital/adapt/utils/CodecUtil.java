package com.hospital.adapt.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Base64;

public class CodecUtil {

    static Logger log = LoggerFactory.getLogger(CodecUtil.class);

    public static String encode(String encode) {
        String result = null;

        if (Str.notNull(encode)) {
            try {
                result = new String(Hex.encodeHex(Base64.getEncoder().encode(encode.getBytes())));
            } catch (Exception e) {
                log.error("CodecUtil encode exception:", e);
            }
        }

        return result;
    }

    public static String decode(String decode) {
        String result = null;

        if (Str.notNull(decode)) {
            try {
                result = new String(Base64.getDecoder().decode(Hex.decodeHex(decode.toCharArray())));
            } catch (DecoderException e) {
                log.error("CodecUtil decode exception:", e);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }

}

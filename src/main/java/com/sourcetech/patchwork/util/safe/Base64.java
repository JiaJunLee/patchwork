package com.sourcetech.patchwork.util.safe;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by 李佳骏 on 2017/4/8.
 */

public class Base64 {

    public static String encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    public static byte[] decode(String string) {
        try {
            return new BASE64Decoder().decodeBuffer(string);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
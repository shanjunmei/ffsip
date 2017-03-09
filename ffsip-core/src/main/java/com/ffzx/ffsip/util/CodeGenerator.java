package com.ffzx.ffsip.util;

import java.util.UUID;

/**
 *
 */
public class CodeGenerator {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * base64(uuid) 编码生成
     *
     * @return
     */
    public static String code() {
        return Base58.encode(asBytes(UUID.randomUUID()));
    }


    /**
     * uuid 转换
     *
     * @param uuid
     * @return
     */
    public static byte[] asBytes(UUID uuid) {
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        byte[] buffer = new byte[16];

        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) (msb >>> 8 * (7 - i));
        }
        for (int i = 8; i < 16; i++) {
            buffer[i] = (byte) (lsb >>> 8 * (7 - i));
        }
        return buffer;

    }

    /**
     * 随机数生成
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandNum(int min, int max) {
        int randNum = min + (int) (Math.random() * ((max - min) + 1));
        return randNum;
    }

    /**
     * 六位验证码生成
     *
     * @return
     */
    public static int getRandNum() {
        return getRandNum(1, 999999);
    }

    public static String getRandStr() {
        return String.valueOf(getRandNum());
    }

    public static void main(String args[]) {
        int ret = getRandNum();
        System.out.println(ret);
        for (int i = 0; i < 1000; i++) {
            String code = code();
            System.out.println(code);
        }
    }
}

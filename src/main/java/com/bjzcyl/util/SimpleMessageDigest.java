package com.bjzcyl.util;

import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;

/**
 * Created by liumeng on 2016/5/26.
 *
 * 将指定的加密信息转换为指定的形式
 */
public enum SimpleMessageDigest {
    SHA1,MD5;

    /**
     * 转换为16进制的形式
     * @param str
     * @return
     */
    public String digestToHex(String str) {
            String[] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
            byte bs[] = digest(str.getBytes());
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < bs.length; i++) {
                int b = bs[i];
                int b1=b >>> 4&0x0f;
                int b2 = b & 0x0f;
                sb.append(hex[b1]).append(hex[b2]);
            }
            str = sb.toString();
        return str;
    }

    /**
     * 将加密后的数组转换为base64编码
     * @param string
     * @return
     */
    public String digestToBase64(String string) {
        byte[] bs = digest(string.getBytes());
        return Base64.encodeBase64String(bs);
    }

    private byte[] digest(byte[] bs) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(this.name());
            md.update(bs);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(this.getClass().getName()+": 获取加密信息失败",e);
        }
    }

}

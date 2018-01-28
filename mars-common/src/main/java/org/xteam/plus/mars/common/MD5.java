package org.xteam.plus.mars.common;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2017-12-16
 * Time: 14:28
 * 功能:  md5工具类
 */
public class MD5 {
    /**
     * Md5签名
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) throws Exception {
       return  leftFill(md5(str),32,"0");
    }

    /**
     * Md5签名
     *
     * @param str
     * @return
     */
    private  static String md5(String str) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5签名异常");
        }
    }

    /**
     * 在左边补足width位  inip
     * @param str
     * @param width
     * @param inip
     * @return
     */
    private static String leftFill(String str, int width, String inip) {
        StringBuilder  newstr = new StringBuilder();
        for (int i = str.length(); i < width; i++) {
            newstr.append(inip);
        }
        newstr.append(str);
        return newstr.toString();

    }



    public static void main(String[] args) throws Exception {
        String str = "ltzstxzsyyt123456";
        System.out.println("ba0fbe87db7f51fc7f72781fe31abd43");
        System.out.println(getMD5(str));
    }
}

package com.app.alcohol.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * md5 encription
 */
public class MD5Util {


    //generate md5
    public static String md5 (String key) {
        return DigestUtils.md5Hex(key) ;
    }


    //salt
    public static final String salt = "alcohol" ;


    /**
     *encrypt password
     * @param inputPass
     * @return
     */
    public static String encrypt(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2)+ inputPass + salt.charAt(5) + salt.charAt(4) ;
        return md5(str);
    }

//    public static void main(String[] args) {
//        System.out.println(encrypt("test"));
//        System.out.println(encrypt("123456"));
//        System.out.println(encrypt("SwFqNhqk9JkUW88"));
//
//    }



}

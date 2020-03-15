package com.yftech.edu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;


/**
 * 1.加密用md5，即 MD5(usernam+password)
 *
 * 用户输入password+用户名生成md5串，和数据库的比较，一致即通过
 *
 * 2.token机制
 * 使用：
 * String token = TokenProcessor.getInstance().makeToken(); //创建令牌
 *
 * 登陆时生成token保存在redis中 保存在kv中，x  hour失效 每次接口调用查token存储，没有或者对不上，剔除登陆状态，且接口不响应
 *
 * kv就是 md5：token
 *
 * @author: andrewzhang
 * @data: 2020/3/15
 * @description:
 **/
public class TokenProcessor {
    /*
     *单例设计模式（保证类的对象在内存中只有一个）
     *1、把类的构造函数私有
     *2、自己创建一个类的对象
     *3、对外提供一个公共的方法，返回类的对象
     */
    private void TokenProcessor() {
    }

    private static final TokenProcessor instance = new TokenProcessor();

    /**
     * 返回类的对象
     * @return
     */
    public static TokenProcessor getInstance() {
        return instance ;
    }

    /**
     * 生成Token
     * Token：Nv6RRuGEVvmGjB+jimI/ gw==
     * @return
     */
    public String makeToken() { //checkException
        //  7346734837483  834u938493493849384  43434384
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "" ;
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5 [] = md.digest(token.getBytes());
            //base64编码--任意二进制编码明文字符   adfsdfsdfsf
            //Base64.encodeBase64String encoder = new BASE64Encoder();
            //return encoder.encode(md5);
            return Base64.encodeBase64String(md5);
        } catch (NoSuchAlgorithmException e ) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){

        String token = TokenProcessor.getInstance().makeToken(); //创建令牌
        System.out.println(token);
    }
}

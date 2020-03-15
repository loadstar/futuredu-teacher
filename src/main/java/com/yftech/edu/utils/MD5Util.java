package com.yftech.edu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密的特点主要有以下几点：
 * 　　1、针对不同长度待加密的数据、字符串等等，其都可以返回一个固定长度的MD5加密字符串。（通常32位的16进制字符串）；
 * 　　2、其加密过程几乎不可逆，除非维护一个庞大的Key-Value数据库来进行碰撞破解，否则几乎无法解开。
 * 　　3、运算简便，且可实现方式多样，通过一定的处理方式也可以避免碰撞算法的破解。
 * 　　4、对于一个固定的字符串。数字等等，MD5加密后的字符串是固定的，也就是说不管MD5加密多少次，都是同样的结果。
 *
 * 注册的时候：我们把密码的值进行MD5加密后在塞入数据库
 * 登录的时候：我们把密码的值进行MD5加密然后去和数据库里面的值进行比对
 *
 * @author: andrewzhang
 * @data: 2020/3/16
 * @description:
 **/
public class MD5Util {

    /**
     *
     * @param plainText
     * @return md5 32位（小写）
     * @throws Exception
     */
    public static String MD5Encode(String plainText){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
            //System.out.println("result: " + buf.toString());// 32位的加密
            //System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String [] args){

        String usr = "admin12345";
        String pwd = "asdjaljlada";

        Long b1 = System.currentTimeMillis();

        for(int i =0;i<10;i++){
            System.out.println(MD5Util.MD5Encode(usr+pwd)); //e306ad3a9b7070676dde5489d76fbb3f
        }

        Long e1 = System.currentTimeMillis();
        System.out.println("MD5.encode耗时:"+(e1-b1));

        // verify:验证相等即可

//        public static boolean verify(String text, String key, String md5) throws Exception {
//            //根据传入的密钥进行验证
//            String md5Text = md5(text, key);
//            if(md5Text.equalsIgnoreCase(md5))
//            {
//                System.out.println("MD5验证通过");
//                return true;
//            }
//            return false;
//        }



    }
}

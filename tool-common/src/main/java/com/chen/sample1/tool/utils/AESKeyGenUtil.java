package com.chen.sample1.tool.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.util.Arrays;
import java.util.Base64;

/**
 * AES对称密钥对生成器
 * @author ChenTian
 * @date 2019/12/6
 */
public class AESKeyGenUtil {

    public static void main(String[] args) throws Exception {
        //testDes();
        System.out.println("========================");
        testAes();
        System.out.println(StrUtil.join("a","b","c"));
       // System.out.println(HashUtil.elfHash("2019121614262301000000000100000000010100"));

    }


    public static void testDes(){
        String content = "2019121614262301000000000100000000010100";

        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();

        //构建
        DES des = SecureUtil.des(key);

        //加密解密
        byte[] encrypt = des.encrypt(content);
        byte[] decrypt = des.decrypt(encrypt);

        //加密为16进制，解密为原字符串
        String encryptHex = des.encryptHex(content);
        System.out.println("加密串:"+encryptHex+" length:"+encryptHex.length());
        String decryptStr = des.decryptStr(encryptHex);
        System.out.println("解密串:"+decryptStr);

    }


    /**
     *
     * 小于16字节的原文会得到32字节长度的密文
     * 大于16字节的，就是16*n长的原文会得到32*n长度的密文
     * @date 2019/12/17
     */
    public static void testAes(){
        byte[] bytes2 = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        System.out.println(Arrays.toString(bytes2)+" length:"+bytes2.length);

        byte[] bytes = "CNICG&&MDR&&2020".getBytes();
        System.out.println("bytes:"+ Arrays.toString(bytes)+" length:"+bytes.length);
        String bystr = encryptBASE64(bytes).toUpperCase();
        System.out.println("str:"+bystr+" length:"+bystr.length());
        System.out.println(Integer.MAX_VALUE);

        String content = "0000000004"+"0101";
        System.out.println("content:"+Arrays.toString(content.getBytes())+" length:"+content.getBytes().length);

        //生成密钥
        String privateKey = encryptBASE64(SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded());

        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, decryptBASE64(privateKey));

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);

        System.out.println("privateKey:"+privateKey);
        System.out.println("加密串:"+encryptHex+" length:"+encryptHex.length());
        System.out.println("解密串:"+decryptStr);


    }

    /**
     * 解码返回byte
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) {
        return Base64.getDecoder().decode(key);
    }


    /**
     * 编码返回字符串
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key){
        return Base64.getEncoder().encodeToString(key);
    }
}

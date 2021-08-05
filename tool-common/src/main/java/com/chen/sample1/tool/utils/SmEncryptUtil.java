package com.chen.sample1.tool.utils;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

/**
 * @description：国密加密工具
 */
public final class SmEncryptUtil {

    private SmEncryptUtil() {
    }

    private static final String PRIVATE_KEY = "308193020100301306072a8648ce3d020106082a811ccf5501822d0479307702010104201e58eefe403019aa516f5e44435d89c5b9829c6b622b4fb84fd2f7f3ecdcfb5fa00a06082a811ccf5501822da14403420004f722cc9f3aefa57291ea995c1e49d0dde7ce751be736adf9334389f4b3157e318f607b92b54aa5fe942b76776dac0682b0f447f137195e196f7f1ede1151c45a";
    private static final String PUBLIC_KEY = "3059301306072a8648ce3d020106082a811ccf5501822d03420004f722cc9f3aefa57291ea995c1e49d0dde7ce751be736adf9334389f4b3157e318f607b92b54aa5fe942b76776dac0682b0f447f137195e196f7f1ede1151c45a";

    private static final SM2 SM = SmUtil.sm2();
    //private static final KeyPair PAIR = SecureUtil.generateKeyPair("SM2");

    /**
     * 非对称加密
     *
     * @param text 加密文本
     * @return 返回加密后的文本
     */
    public static String asymmetricCryptoEncrypt(String text) {

        SM2 sm2 = SmUtil.sm2(HexUtil.decodeHex(PRIVATE_KEY), HexUtil.decodeHex(PUBLIC_KEY));
        return sm2.encryptBcd(text, KeyType.PublicKey);
    }

    /**
     * 非对称解密
     *
     * @param text 解密文本
     * @return 返回解密后的文本
     */
    public static String asymmetricCryptoDecrypt(String text) {

        SM2 sm2 = SmUtil.sm2(HexUtil.decodeHex(PRIVATE_KEY), HexUtil.decodeHex(PUBLIC_KEY));
        return StrUtil.utf8Str(sm2.decryptFromBcd(text, KeyType.PrivateKey));
    }

    /**
     * 签名
     *
     * @param content 需要签名的字段
     * @return 返回签名后的结果
     */
    public static String sign(String content) {
        return SM.signHex(HexUtil.encodeHexStr(content));
    }

    /**
     * 验签
     *
     * @param content 签名的原文
     * @param sign    签名后的字段
     * @return 返回true为验签正确，否则false
     */
    public static boolean verify(String content, String sign) {
        return SM.verifyHex(HexUtil.encodeHexStr(content), sign);
    }

    public static void main(String[] args) {
        String info = "hello";
        String cryptoEncrypt = asymmetricCryptoEncrypt(info);
        Console.log(cryptoEncrypt);
        String cryptoDecrypt = asymmetricCryptoDecrypt(cryptoEncrypt);
        Console.log(cryptoDecrypt);

        info = "world";
        String sign = sign(info);
        Console.log(sign);
        boolean verify = verify(info, sign);
        Console.log(verify);
    }
}

package com.chen.sample1.tool.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA非对称密钥对生成器
 * @author ChenTian
 */
public class RSAKeyGenUtil {
    /**
     * 指定加密算法为RSA
     */
    private final String ALGORITHM = "RSA";
    /**
     * 密钥长度，用来初始化
     */
    private final int KEYSIZE = 1024;
    /**
     * 指定公钥存放文件
     */
    private String publicKeyStr = "";
    /**
     * 指定私钥存放文件
     */
    private String privateKeyStr = "";

    private final String PUBLIC_KEY = "RSAPublicKey";
    private final String PRIVATE_KEY = "RSAPrivateKey";

    public static void main(String[] args) throws Exception {
        RSAKeyGenUtil test = new RSAKeyGenUtil();
        test.genKeyPair();
    }

    private void genKeyPair() throws Exception {

        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom secureRandom = new SecureRandom();

        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        keyPairGenerator.initialize(KEYSIZE, secureRandom);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        /** 得到私钥 */
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        publicKeyStr = getPublicKeyStr(keyMap);
        privateKeyStr = getPrivateKeyStr(keyMap);

        System.out.println("公钥:" + publicKeyStr);
        System.out.println("私钥:" + privateKeyStr);


        String data = "123";

        RSA rsa = SecureUtil.rsa(privateKeyStr,publicKeyStr);
        String encrypt = rsa.encryptBase64(data.getBytes(), KeyType.PublicKey);
        System.out.println(encrypt);
        String result = rsa.decryptStr(encrypt, KeyType.PrivateKey);
        System.out.println(result);

    }

    /**
     * 获得公钥字符串
     * @param keyMap
     * @return
     * @throws Exception
     */
    public String getPublicKeyStr(Map<String, Object> keyMap) throws Exception {
        //获得map中的公钥对象 转为key对象
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        //编码返回字符串
        return encryptBASE64(key.getEncoded());
    }


    /**
     * 获得私钥字符串
     * @param keyMap
     * @return
     * @throws Exception
     */
    public String getPrivateKeyStr(Map<String, Object> keyMap) throws Exception {
        //获得map中的私钥对象 转为key对象
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        //编码返回字符串
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 获取公钥
     * @param key
     * @return
     * @throws Exception
     */
    public PublicKey getPublicKey(String key) throws Exception {
        byte[] pubKey = decryptBASE64(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(x509KeySpec);
    }

    /**
     * 获取私钥
     * @param key
     * @return
     * @throws Exception
     */
    public PrivateKey getPrivateKey(String key) throws Exception {
        byte[] b1 = decryptBASE64(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    /**
     * 解码返回byte
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] decryptBASE64(String key) throws Exception {
        return Base64.getDecoder().decode(key);
    }


    /**
     * 编码返回字符串
     * @param key
     * @return
     * @throws Exception
     */
    public String encryptBASE64(byte[] key) throws Exception {
        return Base64.getEncoder().encodeToString(key);
    }

}

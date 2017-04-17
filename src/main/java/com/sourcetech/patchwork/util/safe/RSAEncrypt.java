package com.sourcetech.patchwork.util.safe;

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * Created by 李佳骏 on 2017/4/8.
 */
public class RSAEncrypt {

    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";

    private static final String KEY_ALGORITHM = "RSA";
    private static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

    private static final int RSA_KEY_SIZE = 1024;
    private static final int RSA_MAX_SIZE = 100;

    public static PublicKey restorePublicKey(byte[] keyBytes) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createNewKeys(File file) {
        FileOutputStream outputStream = null;
        try {
            Map<String,Object> keys = genKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keys.get(PUBLIC_KEY);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keys.get(PRIVATE_KEY);
            Properties properties = new Properties();
            properties.put(PUBLIC_KEY,Base64.encode(rsaPublicKey.getEncoded()));
            properties.put(PRIVATE_KEY,Base64.encode(rsaPrivateKey.getEncoded()));
            outputStream = new FileOutputStream(file);
            properties.store(outputStream,"RSA Keys");
        } catch (Exception e) {
            System.err.println("Error, Can not to create new keys");
            e.printStackTrace();
        } finally {
            if(outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.err.println("Error, Can not to close output stream");
                    e.printStackTrace();
                }
            }
            System.out.println("RSA KEY CREATE COMPLETE");
        }
    }

    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(RSA_KEY_SIZE);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] RSAEncodeByPublicKey(PublicKey key, String plainText) throws Exception{
        int blockSize = plainText.length() / RSA_MAX_SIZE;
        blockSize = plainText.length() % RSA_MAX_SIZE == 0 ? blockSize : blockSize + 1;
        String[] textBlock = new String[blockSize];
        String[] encodes = new String[blockSize];
        for (int i = 0; i < blockSize; i++) {
            int endIndex;
            if (i == (blockSize - 1))
                endIndex = plainText.length();
            else
                endIndex = (i + 1) * RSA_MAX_SIZE;
            textBlock[i] = plainText.substring(i * RSA_MAX_SIZE, endIndex);
        }
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            for (int i = 0; i < encodes.length; i++) {
                encodes[i] = Base64.encode(cipher.doFinal(textBlock[i].getBytes()));
            }
            return encodes;


    }

    public static String RSADecodeByPrivateKey(PrivateKey key, String[] encodedTexts) throws Exception{

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < encodedTexts.length; i++) {
                sb.append(new String(cipher.doFinal(Base64.decode(encodedTexts[i]))));
            }
            return sb.toString();

    }

    public static Map<String,Object> loadKeys(File file){
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            Map<String, Object> keyMap = new HashMap<String, Object>();
            keyMap.put(PUBLIC_KEY, restorePublicKey(Base64.decode(String.valueOf(properties.get(PUBLIC_KEY)))));
            keyMap.put(PRIVATE_KEY, restorePrivateKey(Base64.decode(String.valueOf(properties.get(PRIVATE_KEY)))));
            return keyMap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}

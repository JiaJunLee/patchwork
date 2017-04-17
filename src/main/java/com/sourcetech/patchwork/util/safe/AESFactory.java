package com.sourcetech.patchwork.util.safe;


import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
@Component
public class AESFactory {

    public static final String AES_ALGORITHM = "AES";
    public static final String AES_KEY = "aes_key";
    public static final int KEY_LENGTH = 16;

    private String key;
    private SecretKey keySpec;
    private Cipher encodeCipher;
    private Cipher decodeCipher;

    public AESFactory(){
        File aesKeyFile = new File(getClass().getResource("/").getPath()+"aesKeys.properties");
        if(!aesKeyFile.exists())
            createNewKeys(aesKeyFile);
        key = loadKeys(aesKeyFile);
        System.out.println("AES KEY LOAD COMPLETE");
        initFactory();
    }

    public String encode(String input){
        try {
            return Base64.encode(encodeCipher.doFinal(input.getBytes()));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decode(String input){
        try {
            return new String(decodeCipher.doFinal(Base64.decode(input)));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initFactory() {
        keySpec = new SecretKeySpec(key.getBytes(), AES_ALGORITHM);
        try {
            encodeCipher = Cipher.getInstance(AES_ALGORITHM);
            encodeCipher.init(Cipher.ENCRYPT_MODE, keySpec);

            decodeCipher = Cipher.getInstance(AES_ALGORITHM);
            decodeCipher.init(Cipher.DECRYPT_MODE, keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public static void createNewKeys(File file) {
        FileOutputStream outputStream = null;
        try {
            Properties properties = new Properties();
            RandomSalt randomSalt = RandomSalt.getInstance();
            properties.put(AES_KEY,Base64.encode(randomSalt.getBytesSalt()).substring(0,KEY_LENGTH));
            outputStream = new FileOutputStream(file);
            properties.store(outputStream,"AES Key");
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
            System.out.println("AES KEY CREATE COMPLETE");
        }
    }

    public static String loadKeys(File file){
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            return String.valueOf(properties.getProperty(AES_KEY));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

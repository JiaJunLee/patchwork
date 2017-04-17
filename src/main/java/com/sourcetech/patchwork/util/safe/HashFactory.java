package com.sourcetech.patchwork.util.safe;

import com.sourcetech.patchwork.hibernate.HibernateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
@Component
public class HashFactory {

    public static final String MD5_ALGORITHM = "MD5";
    public static final String SHA_1_ALGORITHM = "SHA-1";
    public static final String SHA_224_ALGORITHM = "SHA-224";
    public static final String SHA_256_ALGORITHM = "SHA-256";
    public static final String SHA_384_ALGORITHM = "SHA-384";
    public static final String SHA_512_ALGORITHM = "SHA-512";

    private static HashMap<String, Integer> algorithms = new HashMap<String, Integer>();
    static{
        algorithms.put(MD5_ALGORITHM, 0);
        algorithms.put(SHA_1_ALGORITHM, 1);
        algorithms.put(SHA_224_ALGORITHM, 2);
        algorithms.put(SHA_256_ALGORITHM, 3);
        algorithms.put(SHA_384_ALGORITHM, 4);
        algorithms.put(SHA_512_ALGORITHM, 5);
    }

    private static MessageDigest[] messageDigests = new MessageDigest[6];

    private HashFactory() {
        try {
            Iterator iter = algorithms.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                messageDigests[(Integer)entry.getValue()] = MessageDigest.getInstance((String)entry.getKey());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public byte[] toHashBytes(byte[] input, String algorithm){
        int temp = algorithms.get(algorithm);
        messageDigests[temp].update(input);
        return messageDigests[temp].digest();
    }


}

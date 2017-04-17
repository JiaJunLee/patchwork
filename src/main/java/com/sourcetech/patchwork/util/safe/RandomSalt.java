package com.sourcetech.patchwork.util.safe;

/**
 * Created by 李佳骏 on 2017/4/10.
 */
import java.security.SecureRandom;

public class RandomSalt {

    private static final int SALT_SIZE = 32;

    private byte[] salt;
    private SecureRandom secureRandom;
    private static volatile RandomSalt RANDOM_SALT;

    private RandomSalt() {
        salt = new byte[SALT_SIZE];
        secureRandom = new SecureRandom();
    }

    public byte[] getBytesSalt(){
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static RandomSalt getInstance() {
        if (RANDOM_SALT == null) {
            synchronized (RandomSalt.class) {
                if (RANDOM_SALT == null) {
                    RANDOM_SALT = new RandomSalt();
                }
            }
        }
        return RANDOM_SALT;
    }

}

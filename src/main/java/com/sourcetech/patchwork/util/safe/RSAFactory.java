package com.sourcetech.patchwork.util.safe;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * Created by 李佳骏 on 2017/4/9.
 */
@Component
public class RSAFactory {

    private Map<String,Object> keys = null;

    private RSAFactory(){
        File rsaKeyFile = new File(getClass().getResource("/").getPath()+"rsaKeys.properties");
        if(!rsaKeyFile.exists())
            RSAEncrypt.createNewKeys(rsaKeyFile);
        keys = RSAEncrypt.loadKeys(rsaKeyFile);
        System.out.println("RSA KEY LOAD COMPLETE");
    }

    public Map<String,Object> getKeys(){return this.keys;}

    public String[] encode(String data) throws Exception{
        return RSAEncrypt.RSAEncodeByPublicKey((RSAPublicKey) keys.get(RSAEncrypt.PUBLIC_KEY),data);
    }

    public String decode(String[] data) throws Exception{
        return RSAEncrypt.RSADecodeByPrivateKey((RSAPrivateKey) keys.get(RSAEncrypt.PRIVATE_KEY),data);
    }

}

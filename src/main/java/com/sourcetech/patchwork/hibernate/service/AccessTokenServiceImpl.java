package com.sourcetech.patchwork.hibernate.service;

import com.sourcetech.patchwork.hibernate.dao.AccessTokenDaoImpl;
import com.sourcetech.patchwork.hibernate.dao.define.AccessTokenDao;
import com.sourcetech.patchwork.hibernate.model.PatchworkAccessTokenEntity;
import com.sourcetech.patchwork.hibernate.service.define.AccessTokenService;
import com.sourcetech.patchwork.servers.oauth.define.OAuthServiceDef;
import com.sourcetech.patchwork.util.safe.AESFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by 李佳骏 on 2017/4/16.
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService{

    @Autowired
    private AccessTokenDaoImpl accessTokenDao;

    public void saveToken(PatchworkAccessTokenEntity accessTokenEntity) {
        accessTokenDao.save(accessTokenEntity);
    }

    @Autowired
    private AESFactory aesFactory;

    public String createToken(Serializable mId) {
        String accessToken = aesFactory.encode(UUID.randomUUID().toString());
        long accessTokenTimeout = System.currentTimeMillis() + OAuthServiceDef.ACCESS_TOKEN_TIMEOUT;
        PatchworkAccessTokenEntity accessTokenEntity = new PatchworkAccessTokenEntity();
        accessTokenEntity.setPatchworkMemberMId((Integer) mId);
        accessTokenEntity.setAccessToken(accessToken);
        accessTokenEntity.setDeadline(new Timestamp(accessTokenTimeout));
        accessTokenDao.save(accessTokenEntity);
        return accessToken;
    }

}

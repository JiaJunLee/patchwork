package com.sourcetech.patchwork.hibernate.service;

import com.sourcetech.patchwork.hibernate.dao.RefreshTokenDaoImpl;
import com.sourcetech.patchwork.hibernate.model.PatchworkRefreshTokenEntity;
import com.sourcetech.patchwork.hibernate.service.define.RefreshTokenService;
import com.sourcetech.patchwork.servers.oauth.define.OAuthServiceDef;
import com.sourcetech.patchwork.util.safe.AESFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by 李佳骏 on 2017/4/16.
 */
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenDaoImpl refreshTokenDao;

    public void saveToken(PatchworkRefreshTokenEntity refreshTokenEntity) {
        refreshTokenDao.save(refreshTokenEntity);
    }

    @Autowired
    private AESFactory aesFactory;

    public String createToken(Serializable mId) {
        String refreshToken = aesFactory.encode(UUID.randomUUID().toString());
        long refreshTokenTimeout = System.currentTimeMillis() + OAuthServiceDef.REFRESH_TOKEN_TIMEOUT;
        PatchworkRefreshTokenEntity refreshTokenEntity = new PatchworkRefreshTokenEntity();
        refreshTokenEntity.setPatchworkMemberMId((Integer) mId);
        refreshTokenEntity.setRefreshToken(refreshToken);
        refreshTokenEntity.setDeadline(new Timestamp(refreshTokenTimeout));
        refreshTokenDao.save(refreshTokenEntity);
        return refreshToken;
    }
}

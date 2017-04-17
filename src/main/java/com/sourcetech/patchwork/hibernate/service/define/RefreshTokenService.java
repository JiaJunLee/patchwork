package com.sourcetech.patchwork.hibernate.service.define;


import com.sourcetech.patchwork.hibernate.model.PatchworkRefreshTokenEntity;

import java.io.Serializable;

/**
 * Created by 李佳骏 on 2017/4/16.
 */
public interface RefreshTokenService {

    public void saveToken(PatchworkRefreshTokenEntity refreshTokenEntity);
    public String createToken(Serializable mId);

}

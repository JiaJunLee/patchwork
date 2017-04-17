package com.sourcetech.patchwork.hibernate.service.define;

import com.sourcetech.patchwork.hibernate.model.PatchworkAccessTokenEntity;
import com.sourcetech.patchwork.hibernate.model.PatchworkMemberEntity;

import java.io.Serializable;

/**
 * Created by 李佳骏 on 2017/4/16.
 */
public interface AccessTokenService {

    public void saveToken(PatchworkAccessTokenEntity accessTokenEntity);
    public String createToken(Serializable mId);

}

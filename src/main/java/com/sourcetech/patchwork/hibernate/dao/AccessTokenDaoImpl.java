package com.sourcetech.patchwork.hibernate.dao;

import com.sourcetech.patchwork.hibernate.dao.define.AccessTokenDao;
import com.sourcetech.patchwork.hibernate.model.PatchworkAccessTokenEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by 李佳骏 on 2017/4/16.
 */
@Repository
public class AccessTokenDaoImpl extends BaseDaoImpl<PatchworkAccessTokenEntity,Serializable> implements AccessTokenDao {



}

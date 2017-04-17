package com.sourcetech.patchwork.hibernate.dao;

import com.sourcetech.patchwork.hibernate.dao.define.RefreshTokenDao;
import com.sourcetech.patchwork.hibernate.model.PatchworkRefreshTokenEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by 李佳骏 on 2017/4/16.
 */
@Repository
public class RefreshTokenDaoImpl extends BaseDaoImpl<PatchworkRefreshTokenEntity,Serializable> implements RefreshTokenDao {



}

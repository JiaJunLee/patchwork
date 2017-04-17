package com.sourcetech.patchwork.hibernate.dao.define;

import com.sourcetech.patchwork.hibernate.model.PatchworkMemberEntity;

/**
 * Created by 李佳骏 on 2017/4/16.
 */
public interface MemberDao {

    public PatchworkMemberEntity queryByPE(PatchworkMemberEntity memberEntity);
}

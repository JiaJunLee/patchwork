package com.sourcetech.patchwork.hibernate.service.define;

import com.sourcetech.patchwork.hibernate.model.PatchworkMemberEntity;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
public interface MemberService {

    public void saveMember(PatchworkMemberEntity memberEntity);

    public PatchworkMemberEntity queryMember(PatchworkMemberEntity memberEntity);

    public boolean checkMember(PatchworkMemberEntity memberEntity,String passwordMeta);
}

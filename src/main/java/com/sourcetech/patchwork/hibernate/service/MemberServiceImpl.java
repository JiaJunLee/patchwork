package com.sourcetech.patchwork.hibernate.service;

import com.sourcetech.patchwork.hibernate.dao.MemberDaoImpl;
import com.sourcetech.patchwork.hibernate.model.PatchworkMemberEntity;
import com.sourcetech.patchwork.hibernate.service.define.MemberService;
import com.sourcetech.patchwork.hibernate.service.define.ServiceResult;
import com.sourcetech.patchwork.util.safe.Base64;
import com.sourcetech.patchwork.util.safe.HashFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDaoImpl memberDao;
    @Autowired
    private HashFactory hashFactory;

    public void saveMember(PatchworkMemberEntity memberEntity) {
        memberDao.save(memberEntity);
    }

    public PatchworkMemberEntity queryMember(PatchworkMemberEntity memberEntity) {
        return memberDao.queryByPE(memberEntity);
    }

    public boolean checkMember(PatchworkMemberEntity memberEntity, String passwordMeta) {
        // 拼接passwordMeta+salt
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(passwordMeta);
        stringBuilder.append(memberEntity.getmPasswordSalt());
        // 接收到的密码与数据库中salt拼接生成hash串
        String hashPasswordReceive = Base64.encode(hashFactory.toHashBytes(stringBuilder.toString().getBytes(), HashFactory.SHA_256_ALGORITHM));
        // 校验密码
        if(memberEntity.getmHashPassword().equals(hashPasswordReceive)) {
            return true;
        }else{
            return false;
        }
    }

}

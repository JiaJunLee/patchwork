package com.sourcetech.patchwork.hibernate.dao;

import com.sourcetech.patchwork.hibernate.dao.define.ExecuteDao;
import com.sourcetech.patchwork.hibernate.dao.define.MemberDao;
import com.sourcetech.patchwork.hibernate.model.PatchworkMemberEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
@Repository
public class MemberDaoImpl extends BaseDaoImpl<PatchworkMemberEntity,Serializable> implements MemberDao{

    public PatchworkMemberEntity queryByPE(PatchworkMemberEntity memberEntity) {
        Object[] objects = execute(new ExecuteDao() {
            public Object[] execute(Session session,Transaction transaction, Object... objects) {
                PatchworkMemberEntity memberEntity = (PatchworkMemberEntity) objects[0];
                // 获取CriteriaBuilder对象
                CriteriaBuilder builder = session.getCriteriaBuilder();
                // 创建Criteria对象
                CriteriaQuery<PatchworkMemberEntity> criteria = builder.createQuery(PatchworkMemberEntity.class);
                // 创建Root对象
                Root<PatchworkMemberEntity> root = criteria.from(PatchworkMemberEntity.class);
                // 添加查询条件
                criteria.select(root);
                criteria.where(builder.or(
                        builder.equal(root.get("mEmail"),memberEntity.getmEmail()),
                        builder.equal(root.get("mPhoneNumber"),memberEntity.getmPhoneNumber())
                ));
                // 查询
                List<PatchworkMemberEntity> memberEntities = session.createQuery(criteria).getResultList();
                transaction.commit();
                return new Object[]{memberEntities};
            }
        },memberEntity);
        List<PatchworkMemberEntity> memberEntities = (List<PatchworkMemberEntity>) objects[0];
        if(memberEntities!=null){
            if(memberEntities.size()>0)
                return memberEntities.get(0);
            else
                return null;
        }
        return null;
    }

}

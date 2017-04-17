package com.sourcetech.patchwork.hibernate.dao.define;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
public interface ExecuteDao {

    public Object[] execute(Session session, Transaction transaction,Object... objects);

}

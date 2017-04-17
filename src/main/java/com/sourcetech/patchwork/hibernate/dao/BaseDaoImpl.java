package com.sourcetech.patchwork.hibernate.dao;

import com.sourcetech.patchwork.hibernate.HibernateFactory;
import com.sourcetech.patchwork.hibernate.dao.define.BaseDao;
import com.sourcetech.patchwork.hibernate.dao.define.ExecuteDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaDelete;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
public class BaseDaoImpl<T,ID extends Serializable> implements BaseDao<T,ID>{

    @Autowired
    private HibernateFactory hibernateFactory;
    private Class<T> entityClass;

    public BaseDaoImpl(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    public T query(ID id) {
        Session session = hibernateFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        T t = null;
        try{
            t = session.get(entityClass,id);
            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return t;
    }

    public T save(T t) {
        Session session = hibernateFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(t);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return t;
    }

    public void update(T t) {
        Session session = hibernateFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(t);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    public void delete(T t) {
        Session session = hibernateFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(t);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    public void delete(Collection<T> entities) {
        Session session = hibernateFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            for(T t:entities)
                session.delete(t);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    public void delete(CriteriaDelete<T> criteria) {
        Session session = hibernateFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.createQuery(criteria);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    public Object[] execute(ExecuteDao executeDao,Object... objects) {
        Session session = hibernateFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Object[] os = null;
        try{
            os = executeDao.execute(session,transaction,objects);
        }catch(Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return os;
    }


}

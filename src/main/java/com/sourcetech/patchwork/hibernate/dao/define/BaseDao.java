package com.sourcetech.patchwork.hibernate.dao.define;

import javax.persistence.criteria.CriteriaDelete;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by 李佳骏 on 2017/4/15.
 */
public interface BaseDao<T, ID extends Serializable> {

    public T query(ID id);
    public T save(T t);
    public void update(T t);
    public void delete(T t);
    public void delete(Collection<T> entities);
    public void delete(CriteriaDelete<T> criteria);
    public Object[] execute(ExecuteDao executeDao,Object... objects);

}

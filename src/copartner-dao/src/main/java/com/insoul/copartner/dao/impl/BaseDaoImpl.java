package com.insoul.copartner.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.insoul.copartner.dao.IBaseDao;

public class BaseDaoImpl<T, PK extends Serializable> implements IBaseDao<T, PK> {

    @PersistenceContext(unitName = "core")
    protected EntityManager entityManager;

    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEM() {
        return this.entityManager;
    }

    public T get(PK id) {
        return getEM().find(this.clazz, id);
    }

    public T load(Class<T> clazz, PK id) {
        return getEM().getReference(clazz, id);
    }

    public void refresh(T entity) {
        getEM().refresh(entity);
    }

    public void save(T entity) {
        getEM().persist(entity);
    }

    public void update(T entity) {
        getEM().merge(entity);
    }

    public void delete(T entity) {
        getEM().remove(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getEM().createQuery("FROM " + clazz.getName()).getResultList();
    }

    public List<T> query(String query) {
        return query(query, null);
    }

    public List<T> query(String query, Map<String, Object> args) {
        return createQuery(query, this.clazz, args).getResultList();
    }

    public T queryOne(String query) {
        return queryOne(query, null);
    }

    public T queryOne(String query, Map<String, Object> args) {
        List<T> result = createQuery(query, this.clazz, args).getResultList();

        return (null != result && result.size() > 0) ? result.get(0) : null;
    }

    public List<T> queryByNamedQuery(String queryName) {
        return queryByNamedQuery(queryName, null);
    }

    public List<T> queryByNamedQuery(String queryName, Map<String, Object> args) {
        return createNamedQuery(queryName, this.clazz, args).getResultList();
    }

    public T queryOneByNamedQuery(String queryName) {
        return queryOneByNamedQuery(queryName, null);
    }

    public T queryOneByNamedQuery(String queryName, Map<String, Object> args) {
        List<T> result = createNamedQuery(queryName, this.clazz, args).getResultList();

        return (null != result && result.size() > 0) ? result.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByNativeQuery(String sql, Map<String, Object> args) {
        return createNativeQuery(sql, this.clazz, args).getResultList();
    }

    @SuppressWarnings("unchecked")
    public T queryOneByNativeQuery(String sql, Map<String, Object> args) {
        List<T> result = createNativeQuery(sql, this.clazz, args).getResultList();

        return (null != result && result.size() > 0) ? result.get(0) : null;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryMapByNativeQuery(String sql, Map<String, Object> args) {
        Query query = createNativeQuery(sql, args);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();
    }

    protected final Query createQuery(String ql, Map<String, Object> args) {
        return queryMerger(getEM().createQuery(ql), args);
    }

    protected final <C> TypedQuery<C> createQuery(String ql, Class<C> clazz, Map<String, Object> args) {
        return queryMerger(getEM().createQuery(ql, clazz), args);
    }

    protected final Query createNamedQuery(String queryName, Map<String, Object> args) {
        return queryMerger(getEM().createNamedQuery(queryName), args);
    }

    protected final <C> TypedQuery<C> createNamedQuery(String queryName, Class<C> clazz, Map<String, Object> args) {
        return queryMerger(getEM().createNamedQuery(queryName, clazz), args);
    }

    protected final Query createNativeQuery(String sql, Map<String, Object> args) {
        return queryMerger(getEM().createNativeQuery(sql), args);
    }

    protected final Query createNativeQuery(String sql, Class<?> clazz, Map<String, Object> args) {
        return queryMerger(getEM().createNativeQuery(sql, clazz), args);
    }

    protected final <Q extends Query> Q queryMerger(Q query, Map<String, Object> args) {
        if (null == query || null == args || args.isEmpty()) {
            return query;
        }

        for (Map.Entry<String, Object> entry : args.entrySet()) {
            if (entry.getValue() instanceof java.util.Date) {
                query.setParameter(entry.getKey(), (java.util.Date) entry.getValue(), TemporalType.TIMESTAMP);
                continue;
            }
            if (entry.getValue() instanceof java.sql.Date) {
                query.setParameter(entry.getKey(), (java.sql.Date) entry.getValue(), TemporalType.TIMESTAMP);
                continue;
            }
            if (entry.getValue() instanceof java.util.Calendar) {
                query.setParameter(entry.getKey(), (java.util.Calendar) entry.getValue(), TemporalType.TIMESTAMP);
                continue;
            }
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query;
    }

}

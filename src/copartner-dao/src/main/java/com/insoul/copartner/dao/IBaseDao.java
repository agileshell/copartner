package com.insoul.copartner.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T, PK extends Serializable> {

    T get(PK id);

    T load(Class<T> clazz, PK id);

    void refresh(T entity);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    List<T> query(String query);

    List<T> query(String query, Map<String, Object> args);

    T queryOne(String query);

    T queryOne(String query, Map<String, Object> args);

    List<T> queryByNamedQuery(String queryName);

    List<T> queryByNamedQuery(String queryName, Map<String, Object> args);

    T queryOneByNamedQuery(String queryName);

    T queryOneByNamedQuery(String queryName, Map<String, Object> args);

    List<T> queryByNativeQuery(String sql, Map<String, Object> args);

    T queryOneByNativeQuery(String sql, Map<String, Object> args);

    List<Map<String, Object>> queryMapByNativeQuery(String sql, Map<String, Object> args);
}

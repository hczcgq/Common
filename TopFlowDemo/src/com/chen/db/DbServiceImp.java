package com.chen.db;

import java.util.List;

public interface DbServiceImp<T> {

    /**
     * insert or update entity
     * 
     * @param entity
     * @return
     */
    public long insertOrReplace(T entity);

    /**
     * insert or update entity with mass storage
     * 
     * @param list
     */
    public void insertOrReplace(List<T> list);

    /**
     * delete all entity
     */
    public void deleteAll();

    /**
     * delete entity by id
     * 
     * @param id
     */
    public void deleteBykey(long id);

    /**
     * delete entity by entity
     * 
     * @param entity
     */
    public void deleteByEntity(T entity);
    
    /**
     * query entity with id
     * @param id
     * @return
     */
    public T loadByKey(long id);
    
    /**
     * query all entity
     * @return
     */
    public List<T> loadAll();
    
    /**
     * query list with where clause ex: begin_date_time >= ? AND end_date_time
     * <= ?
     * @param where
     *            where clause, include 'where' word
     * @param params
     *            query parameters
     * @return
     */
    public List<T> queryWithParem(String where, String... params);
    
    
    /**
     * check exist entity with id
     * @param id
     * @return
     */
    public boolean isExistWithId(long id);
    
    
}

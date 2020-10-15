package com.lizhao.my.shop.commons.persistence;

import java.util.List;

/**
 * 树形通用接口方法
 */
public interface BaseTreeDao<T extends BaseEntity> {
    /**
     * 查询全部数据
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(long id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    T getById(long id);


    /**
     * 更新
     * @param entity
     */
    void update(T entity);

    List<T> selectByPid(Long pid);

}

package com.lizhao.my.shop.commons.persistence;

import com.lizhao.my.shop.commons.dto.BaseResult;

import java.util.List;

public interface BaseTreeService<T extends BaseEntity> {
    /**
     * 查询全部数据
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    BaseResult save(T entity);

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
    T getById(Long id);


    /**
     * 更新
     * @param entity
     */
    void update(T entity);

    /**
     * 根据父级类目节点，查询所有子节点
     * 给zTree返回数据
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);
}

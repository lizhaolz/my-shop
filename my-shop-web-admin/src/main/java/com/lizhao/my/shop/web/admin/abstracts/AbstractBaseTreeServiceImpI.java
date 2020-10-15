package com.lizhao.my.shop.web.admin.abstracts;

import com.lizhao.my.shop.commons.persistence.BaseDao;
import com.lizhao.my.shop.commons.persistence.BaseEntity;
import com.lizhao.my.shop.commons.persistence.BaseTreeDao;
import com.lizhao.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractBaseTreeServiceImpI<T extends BaseEntity, D extends BaseTreeDao<T>>implements BaseTreeService<T> {
    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public T getById(Long id){
        return dao.getById(id);
    }

    @Override
    public void update(T entity){
        dao.update(entity);
    }

    @Override
    public List<T> selectByPid(Long pid){
        return dao.selectByPid(pid);
    }


}

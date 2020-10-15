package com.lizhao.my.shop.web.admin.dao;

import com.lizhao.my.shop.commons.persistence.BaseDao;
import com.lizhao.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    List<TbUser> selectByUsername(String username);
    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    TbUser getByEmail(String email);
}

package com.lizhao.my.shop.web.admin.service;

import com.lizhao.my.shop.commons.dto.BaseResult;
import com.lizhao.my.shop.commons.dto.PageInfo;
import com.lizhao.my.shop.commons.persistence.BaseService;
import com.lizhao.my.shop.domain.TbUser;

import java.util.LinkedList;
import java.util.List;

public interface TbUserService extends BaseService<TbUser> {
    /*
       用户登陆
     */
    TbUser login(String email, String password);

    List<TbUser> selectByUsername(String username);
}



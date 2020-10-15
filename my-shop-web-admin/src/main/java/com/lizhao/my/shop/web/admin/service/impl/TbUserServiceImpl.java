package com.lizhao.my.shop.web.admin.service.impl;

import com.lizhao.my.shop.commons.dto.BaseResult;
import com.lizhao.my.shop.commons.dto.PageInfo;
import com.lizhao.my.shop.commons.utils.RegexpUtils;
import com.lizhao.my.shop.commons.validator.BeanValidator;
import com.lizhao.my.shop.domain.TbUser;
import com.lizhao.my.shop.web.admin.abstracts.AbstractBaseServiceImpI;
import com.lizhao.my.shop.web.admin.dao.TbUserDao;
import com.lizhao.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpI<TbUser, TbUserDao> implements TbUserService {


    @Override
    public BaseResult save(TbUser tbUser) {
        String beanValidator = BeanValidator.validator(tbUser);
        // 验证未通过
        if(beanValidator != null)
        {
            return BaseResult.fail(beanValidator);
        }
        // 验证通过
        else{
            tbUser.setUpdated(new Date());
            // 新增用户
            if(tbUser.getId()==null) {
                // 密码要加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                dao.insert(tbUser);
            }

            // 编辑用户
            else {
                update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }

    }




    @Override
    public List<TbUser> selectByUsername(String username) {
        return dao.selectByUsername(username);
    }





    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
        if (tbUser != null) {
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (md5Password.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }







}

package com.lizhao.my.shop.web.admin.abstracts;

import com.lizhao.my.shop.commons.dto.BaseResult;
import com.lizhao.my.shop.commons.dto.PageInfo;
import com.lizhao.my.shop.commons.persistence.BaseEntity;
import com.lizhao.my.shop.commons.persistence.BaseService;
import com.lizhao.my.shop.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public abstract class AbstractBaseController<T extends BaseEntity, S extends BaseService<T>> {
    @Autowired
    protected S service;

    /**
     * 跳转到列表页
     * @return
     */
    public abstract String list();

    /**
     * 跳转到表单页
     * @return
     */
    public abstract String form();

    /**
     * 保存信息
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 删除
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

    /**
     * 分页查询
     * @param httpServletRequest
     * @param entity
     * @return
     * @throws Exception
     */
    public abstract PageInfo<T> page(HttpServletRequest httpServletRequest, T entity) throws Exception;

    /**
     * 跳转详情页
     * @return
     */
    public abstract String detail();

    }

package com.lizhao.my.shop.web.admin.abstracts;

import com.lizhao.my.shop.commons.dto.PageInfo;
import com.lizhao.my.shop.commons.persistence.BaseEntity;
import com.lizhao.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractBaseTreeController<T extends BaseEntity, S extends BaseTreeService> {
    @Autowired
    protected S service;

    /**
     * 表单页
     * @param entity
     * @return
     */
    public abstract String form(T entity);

    /**
     * 列表页
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 保存
     * @param model
     * @param entity
     * @param redirectAttributes
     * @return
     */
    public abstract String save(Model model, T entity, RedirectAttributes redirectAttributes);

    /**
     * 树形结构
     * @param id
     * @return
     */
    public abstract List<T> treeDate(Long id);
}

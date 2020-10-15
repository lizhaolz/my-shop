package com.lizhao.my.shop.web.admin.web.controller;

import com.lizhao.my.shop.commons.dto.BaseResult;
import com.lizhao.my.shop.commons.persistence.BaseEntity;
import com.lizhao.my.shop.domain.TbContentCategory;
import com.lizhao.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.lizhao.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory, TbContentCategoryService> {

    @Override
    public String list() {
        return null;
    }

    @Override
    public String form() {
        return null;
    }

    @Override
    public String save(BaseEntity entity, Model model, RedirectAttributes redirectAttributes) {
        return null;
    }

    @Override
    public BaseResult delete(String ids) {
        return null;
    }

    @Override
    public String detail() {
        return null;
    }
}

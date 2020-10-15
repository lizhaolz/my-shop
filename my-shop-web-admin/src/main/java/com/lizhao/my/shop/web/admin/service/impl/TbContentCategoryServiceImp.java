package com.lizhao.my.shop.web.admin.service.impl;

import com.lizhao.my.shop.commons.dto.BaseResult;
import com.lizhao.my.shop.commons.validator.BeanValidator;
import com.lizhao.my.shop.domain.TbContentCategory;
import com.lizhao.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpI;
import com.lizhao.my.shop.web.admin.dao.TbContentCategoryDao;
import com.lizhao.my.shop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbContentCategoryServiceImp extends AbstractBaseTreeServiceImpI<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {




    @Override
    public BaseResult save(TbContentCategory entity) {
        String validate = BeanValidator.validator(entity);
        if (validate != null){
            return BaseResult.fail(validate);
        }
        else{
            TbContentCategory parent = entity.getParent();
            // 如果没有选择根节点，则默认设置为根目录
            if(parent == null || parent.getId() == null){
                // 0代表根目录
                parent.setId(0L);
                entity.setIsParent(true);
            }
            entity.setUpdated(new Date());
            // 新增
            if(entity.getId()==null){
                entity.setCreated(new Date());
                entity.setIsParent(false);
                // 判断当前新增的节点有没有父节点
                if(parent.getId()!=0L){
                    TbContentCategory currentCateoryParent = getById(parent.getId());
                    if(currentCateoryParent != null){
                        currentCateoryParent.setIsParent(true);
                        update(currentCateoryParent);
                    }
                }else{
                    // 等于0的话增加的一定是父级节点
                    entity.setIsParent(true);
                }
                dao.insert(entity);
            }else{
                update(entity);
            }


            return BaseResult.success("保存分类信息成功！！！");
        }
    }









}


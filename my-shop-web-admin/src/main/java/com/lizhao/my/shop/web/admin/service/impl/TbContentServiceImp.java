package com.lizhao.my.shop.web.admin.service.impl;

import com.lizhao.my.shop.commons.dto.BaseResult;
import com.lizhao.my.shop.commons.dto.PageInfo;
import com.lizhao.my.shop.commons.utils.RegexpUtils;
import com.lizhao.my.shop.domain.TbContent;
import com.lizhao.my.shop.domain.TbUser;
import com.lizhao.my.shop.web.admin.abstracts.AbstractBaseServiceImpI;
import com.lizhao.my.shop.web.admin.dao.TbContentDao;
import com.lizhao.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.w3c.dom.views.AbstractView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImp extends AbstractBaseServiceImpI<TbContent, TbContentDao> implements TbContentService {


    @Override
    public BaseResult save(TbContent tbContent) {
        BaseResult baseResult = checkTbContent(tbContent);
        if (baseResult.getStatus()==BaseResult.STATUS_SUCCESS) {
            tbContent.setUpdated(new Date());

            // 新增内容
            if(tbContent.getId()==null) {
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }

            // 编辑内容
            else {
                update(tbContent);
            }
            baseResult.setMessage("保存内容信息成功");
        }
        return baseResult;

    }


    /**
     * 信息的有效性验证
     * @param tbContent
     */
    private BaseResult checkTbContent(TbContent tbContent){
        BaseResult baseResult = BaseResult.success();

        if(StringUtils.isBlank(tbContent.getTitle())) {
            baseResult = BaseResult.fail(500,"内容标题不能为空，请重新输入");
        }

        else if (StringUtils.isBlank(tbContent.getSubTitle())) {
            baseResult = BaseResult.fail(500,"内容子标题不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(tbContent.getTitleDesc())){
            baseResult = BaseResult.fail(500, "标题描述不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(tbContent.getUrl())){
            baseResult = BaseResult.fail(500, "链接不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbContent.getPic())) {
            baseResult = BaseResult.fail(500,"图片绝对路径不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(tbContent.getPic2())) {
            baseResult = BaseResult.fail(500,"图片不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(tbContent.getContent())) {
            baseResult = BaseResult.fail(500,"内容不能为空，请重新输入");
        }
        return baseResult;
    }
}

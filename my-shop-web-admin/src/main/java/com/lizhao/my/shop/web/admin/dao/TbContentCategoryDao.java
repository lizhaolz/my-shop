package com.lizhao.my.shop.web.admin.dao;

import com.lizhao.my.shop.commons.persistence.BaseDao;
import com.lizhao.my.shop.commons.persistence.BaseTreeDao;
import com.lizhao.my.shop.domain.TbContent;
import com.lizhao.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao extends BaseTreeDao<TbContentCategory> {

}

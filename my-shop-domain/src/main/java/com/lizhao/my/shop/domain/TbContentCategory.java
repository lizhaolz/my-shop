package com.lizhao.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.lizhao.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 分类管理
 */

@Data
public class TbContentCategory extends BaseEntity {
    @Length(min = 1, max = 20, message = "分类名称必须介于1-20位之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    private Boolean isParent;
    private TbContentCategory parent; // 自关联，数据库查询

}

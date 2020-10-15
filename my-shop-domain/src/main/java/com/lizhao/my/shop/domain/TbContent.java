package com.lizhao.my.shop.domain;

import com.lizhao.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TbContent extends BaseEntity {
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;

    @NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;  // 外键


}

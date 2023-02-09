package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 
 * @TableName pms_category
 */
@TableName(value ="pms_category")
@Data
public class Category implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long catId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Long parentCid;

    /**
     * 
     */
    private Integer catLevel;

    /**
     * 
     */
    @TableLogic(value = "1",delval = "0")
    private Integer showStatus;

    /**
     * 
     */
    private Integer sort;

    /**
     * ͼ
     */
    private String icon;

    /**
     * 
     */
    private String productUnit;

    /**
     * 
     */
    private Integer productCount;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<Category> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
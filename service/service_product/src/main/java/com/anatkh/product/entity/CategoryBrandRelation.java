package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * Ʒ
 * @TableName pms_category_brand_relation
 */
@TableName(value ="pms_category_brand_relation")
@Data
public class CategoryBrandRelation implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Ʒ
     */
    private Long brandId;

    /**
     * 
     */
    private Long catelogId;

    /**
     * 
     */
    private String brandName;

    /**
     * 
     */
    private String catelogName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
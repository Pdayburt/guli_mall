package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * spu
 * @TableName pms_product_attr_value
 */
@TableName(value ="pms_product_attr_value")
@Data
public class ProductAttrValue implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long spuId;

    /**
     * 
     */
    private Long attrId;

    /**
     * 
     */
    private String attrName;

    /**
     * 
     */
    private String attrValue;

    /**
     * ˳
     */
    private Integer attrSort;

    /**
     * 
     */
    private Integer quickShow;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
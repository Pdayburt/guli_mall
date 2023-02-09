package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * skuͼƬ
 * @TableName pms_sku_images
 */
@TableName(value ="pms_sku_images")
@Data
public class SkuImages implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * ͼƬ
     */
    private String imgUrl;

    /**
     * 
     */
    private Integer imgSort;

    /**
     * Ĭ
     */
    private Integer defaultImg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
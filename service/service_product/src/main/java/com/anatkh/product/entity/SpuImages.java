package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * spuͼƬ
 * @TableName pms_spu_images
 */
@TableName(value ="pms_spu_images")
@Data
public class SpuImages implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * spu_id
     */
    private Long spuId;

    /**
     * ͼƬ
     */
    private String imgName;

    /**
     * ͼƬ
     */
    private String imgUrl;

    /**
     * ˳
     */
    private Integer imgSort;

    /**
     * 
     */
    private Integer defaultImg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName pms_spu_comment
 */
@TableName(value ="pms_spu_comment")
@Data
public class SpuComment implements Serializable {
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
     * spu_id
     */
    private Long spuId;

    /**
     * 
     */
    private String spuName;

    /**
     * 
     */
    private String memberNickName;

    /**
     * 
     */
    private Integer star;

    /**
     * 
     */
    private String memberIp;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer showStatus;

    /**
     * 
     */
    private String spuAttributes;

    /**
     * 
     */
    private Integer likesCount;

    /**
     * 
     */
    private Integer replyCount;

    /**
     * 
     */
    private String resources;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String memberIcon;

    /**
     * 
     */
    private Integer commentType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
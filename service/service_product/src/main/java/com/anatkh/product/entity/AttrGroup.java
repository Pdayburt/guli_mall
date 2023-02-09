package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pms_attr_group
 */
@TableName(value ="pms_attr_group")
@Data
public class AttrGroup implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long attrGroupId;

    /**
     * 
     */
    private String attrGroupName;

    /**
     * 
     */
    private Integer sort;

    /**
     * 
     */
    private String descript;

    /**
     * 
     */
    private String icon;

    /**
     * 
     */
    private Long catelogId;

    @TableField(exist = false)
    private Long[] catalogPath;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
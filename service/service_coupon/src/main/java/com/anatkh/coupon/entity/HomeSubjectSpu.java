package com.anatkh.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ר
 * @TableName sms_home_subject_spu
 */
@TableName(value ="sms_home_subject_spu")
@Data
@ToString
@EqualsAndHashCode
public class HomeSubjectSpu implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * ר
     */
    private String name;

    /**
     * ר
     */
    private Long subjectId;

    /**
     * spu_id
     */
    private Long spuId;

    /**
     * 
     */
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
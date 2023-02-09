package com.anatkh.product.entity;

import com.anatkh.commonUtil.valid.AddGroup;
import com.anatkh.commonUtil.valid.ListValue;
import com.anatkh.commonUtil.valid.UpdateGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * Ʒ
 * @TableName pms_brand
 */
@TableName(value ="pms_brand")
@Data
public class Brand implements Serializable {
    /**
     * Ʒ
     */
    @Null(message = "新增时必须为空",groups = AddGroup.class)
    @NotNull(message = "修改时不能为空",groups = UpdateGroup.class)
    @TableId(type = IdType.AUTO)
    private Long brandId;

    /**
     * Ʒ
     */

    @NotBlank(message = "品牌名不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * Ʒ
     */
    @NotEmpty
    @URL(message = "logo必须是一个合法的url地址")
    private String logo;

    /**
     * 
     */
    private String descript;

    /**
     * 
     */
    @ListValue(vals = {1,0},groups = {AddGroup.class},message = "必须提交指定的值")
    private Integer showStatus;

    /**
     * 
     */
    @NotEmpty
    @Pattern(regexp="^[a-zA-Z]$",message = "检索首字母必须为一个字母")
    private String firstLetter;

    /**
     * 
     */
    @NotNull
    @Min(value = 0,message = "排序必须大于等于0")
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
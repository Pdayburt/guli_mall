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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Brand other = (Brand) that;
        return (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getDescript() == null ? other.getDescript() == null : this.getDescript().equals(other.getDescript()))
            && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()))
            && (this.getFirstLetter() == null ? other.getFirstLetter() == null : this.getFirstLetter().equals(other.getFirstLetter()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getDescript() == null) ? 0 : getDescript().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        result = prime * result + ((getFirstLetter() == null) ? 0 : getFirstLetter().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", brandId=").append(brandId);
        sb.append(", name=").append(name);
        sb.append(", logo=").append(logo);
        sb.append(", descript=").append(descript);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", firstLetter=").append(firstLetter);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
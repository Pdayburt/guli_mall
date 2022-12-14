package com.anatkh.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sms_coupon
 */
@TableName(value ="sms_coupon")
@Data
public class Coupon implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Integer couponType;

    /**
     * 
     */
    private String couponImg;

    /**
     * 
     */
    private String couponName;

    /**
     * 
     */
    private Integer num;

    /**
     * 
     */
    private BigDecimal amount;

    /**
     * ÿ
     */
    private Integer perLimit;

    /**
     * ʹ
     */
    private BigDecimal minPoint;

    /**
     * 
     */
    private Date startTime;

    /**
     * 
     */
    private Date endTime;

    /**
     * ʹ
     */
    private Integer useType;

    /**
     * 
     */
    private String note;

    /**
     * 
     */
    private Integer publishCount;

    /**
     * 
     */
    private Integer useCount;

    /**
     * 
     */
    private Integer receiveCount;

    /**
     * 
     */
    private Date enableStartTime;

    /**
     * 
     */
    private Date enableEndTime;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private Integer memberLevel;

    /**
     * 
     */
    private Integer publish;

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
        Coupon other = (Coupon) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCouponType() == null ? other.getCouponType() == null : this.getCouponType().equals(other.getCouponType()))
            && (this.getCouponImg() == null ? other.getCouponImg() == null : this.getCouponImg().equals(other.getCouponImg()))
            && (this.getCouponName() == null ? other.getCouponName() == null : this.getCouponName().equals(other.getCouponName()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getPerLimit() == null ? other.getPerLimit() == null : this.getPerLimit().equals(other.getPerLimit()))
            && (this.getMinPoint() == null ? other.getMinPoint() == null : this.getMinPoint().equals(other.getMinPoint()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getUseType() == null ? other.getUseType() == null : this.getUseType().equals(other.getUseType()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getPublishCount() == null ? other.getPublishCount() == null : this.getPublishCount().equals(other.getPublishCount()))
            && (this.getUseCount() == null ? other.getUseCount() == null : this.getUseCount().equals(other.getUseCount()))
            && (this.getReceiveCount() == null ? other.getReceiveCount() == null : this.getReceiveCount().equals(other.getReceiveCount()))
            && (this.getEnableStartTime() == null ? other.getEnableStartTime() == null : this.getEnableStartTime().equals(other.getEnableStartTime()))
            && (this.getEnableEndTime() == null ? other.getEnableEndTime() == null : this.getEnableEndTime().equals(other.getEnableEndTime()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getMemberLevel() == null ? other.getMemberLevel() == null : this.getMemberLevel().equals(other.getMemberLevel()))
            && (this.getPublish() == null ? other.getPublish() == null : this.getPublish().equals(other.getPublish()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCouponType() == null) ? 0 : getCouponType().hashCode());
        result = prime * result + ((getCouponImg() == null) ? 0 : getCouponImg().hashCode());
        result = prime * result + ((getCouponName() == null) ? 0 : getCouponName().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getPerLimit() == null) ? 0 : getPerLimit().hashCode());
        result = prime * result + ((getMinPoint() == null) ? 0 : getMinPoint().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getUseType() == null) ? 0 : getUseType().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getPublishCount() == null) ? 0 : getPublishCount().hashCode());
        result = prime * result + ((getUseCount() == null) ? 0 : getUseCount().hashCode());
        result = prime * result + ((getReceiveCount() == null) ? 0 : getReceiveCount().hashCode());
        result = prime * result + ((getEnableStartTime() == null) ? 0 : getEnableStartTime().hashCode());
        result = prime * result + ((getEnableEndTime() == null) ? 0 : getEnableEndTime().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getMemberLevel() == null) ? 0 : getMemberLevel().hashCode());
        result = prime * result + ((getPublish() == null) ? 0 : getPublish().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponType=").append(couponType);
        sb.append(", couponImg=").append(couponImg);
        sb.append(", couponName=").append(couponName);
        sb.append(", num=").append(num);
        sb.append(", amount=").append(amount);
        sb.append(", perLimit=").append(perLimit);
        sb.append(", minPoint=").append(minPoint);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", useType=").append(useType);
        sb.append(", note=").append(note);
        sb.append(", publishCount=").append(publishCount);
        sb.append(", useCount=").append(useCount);
        sb.append(", receiveCount=").append(receiveCount);
        sb.append(", enableStartTime=").append(enableStartTime);
        sb.append(", enableEndTime=").append(enableEndTime);
        sb.append(", code=").append(code);
        sb.append(", memberLevel=").append(memberLevel);
        sb.append(", publish=").append(publish);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
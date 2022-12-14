package com.anatkh.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName ums_member_statistics_info
 */
@TableName(value ="ums_member_statistics_info")
@Data
public class MemberStatisticsInfo implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long memberId;

    /**
     * 
     */
    private BigDecimal consumeAmount;

    /**
     * 
     */
    private BigDecimal couponAmount;

    /**
     * 
     */
    private Integer orderCount;

    /**
     * 
     */
    private Integer couponCount;

    /**
     * 
     */
    private Integer commentCount;

    /**
     * 
     */
    private Integer returnOrderCount;

    /**
     * 
     */
    private Integer loginCount;

    /**
     * 
     */
    private Integer attendCount;

    /**
     * 
     */
    private Integer fansCount;

    /**
     * 
     */
    private Integer collectProductCount;

    /**
     * 
     */
    private Integer collectSubjectCount;

    /**
     * 
     */
    private Integer collectCommentCount;

    /**
     * 
     */
    private Integer inviteFriendCount;

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
        MemberStatisticsInfo other = (MemberStatisticsInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getConsumeAmount() == null ? other.getConsumeAmount() == null : this.getConsumeAmount().equals(other.getConsumeAmount()))
            && (this.getCouponAmount() == null ? other.getCouponAmount() == null : this.getCouponAmount().equals(other.getCouponAmount()))
            && (this.getOrderCount() == null ? other.getOrderCount() == null : this.getOrderCount().equals(other.getOrderCount()))
            && (this.getCouponCount() == null ? other.getCouponCount() == null : this.getCouponCount().equals(other.getCouponCount()))
            && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
            && (this.getReturnOrderCount() == null ? other.getReturnOrderCount() == null : this.getReturnOrderCount().equals(other.getReturnOrderCount()))
            && (this.getLoginCount() == null ? other.getLoginCount() == null : this.getLoginCount().equals(other.getLoginCount()))
            && (this.getAttendCount() == null ? other.getAttendCount() == null : this.getAttendCount().equals(other.getAttendCount()))
            && (this.getFansCount() == null ? other.getFansCount() == null : this.getFansCount().equals(other.getFansCount()))
            && (this.getCollectProductCount() == null ? other.getCollectProductCount() == null : this.getCollectProductCount().equals(other.getCollectProductCount()))
            && (this.getCollectSubjectCount() == null ? other.getCollectSubjectCount() == null : this.getCollectSubjectCount().equals(other.getCollectSubjectCount()))
            && (this.getCollectCommentCount() == null ? other.getCollectCommentCount() == null : this.getCollectCommentCount().equals(other.getCollectCommentCount()))
            && (this.getInviteFriendCount() == null ? other.getInviteFriendCount() == null : this.getInviteFriendCount().equals(other.getInviteFriendCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getConsumeAmount() == null) ? 0 : getConsumeAmount().hashCode());
        result = prime * result + ((getCouponAmount() == null) ? 0 : getCouponAmount().hashCode());
        result = prime * result + ((getOrderCount() == null) ? 0 : getOrderCount().hashCode());
        result = prime * result + ((getCouponCount() == null) ? 0 : getCouponCount().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getReturnOrderCount() == null) ? 0 : getReturnOrderCount().hashCode());
        result = prime * result + ((getLoginCount() == null) ? 0 : getLoginCount().hashCode());
        result = prime * result + ((getAttendCount() == null) ? 0 : getAttendCount().hashCode());
        result = prime * result + ((getFansCount() == null) ? 0 : getFansCount().hashCode());
        result = prime * result + ((getCollectProductCount() == null) ? 0 : getCollectProductCount().hashCode());
        result = prime * result + ((getCollectSubjectCount() == null) ? 0 : getCollectSubjectCount().hashCode());
        result = prime * result + ((getCollectCommentCount() == null) ? 0 : getCollectCommentCount().hashCode());
        result = prime * result + ((getInviteFriendCount() == null) ? 0 : getInviteFriendCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", consumeAmount=").append(consumeAmount);
        sb.append(", couponAmount=").append(couponAmount);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", couponCount=").append(couponCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", returnOrderCount=").append(returnOrderCount);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", attendCount=").append(attendCount);
        sb.append(", fansCount=").append(fansCount);
        sb.append(", collectProductCount=").append(collectProductCount);
        sb.append(", collectSubjectCount=").append(collectSubjectCount);
        sb.append(", collectCommentCount=").append(collectCommentCount);
        sb.append(", inviteFriendCount=").append(inviteFriendCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.anatkh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pms_comment_replay
 */
@TableName(value ="pms_comment_replay")
@Data
public class CommentReplay implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long commentId;

    /**
     * 
     */
    private Long replyId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
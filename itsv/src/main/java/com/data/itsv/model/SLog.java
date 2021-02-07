package com.data.itsv.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author ysc
 * @since 2021-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 日志类型 关联字典id
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 操作类型 关联字典id
     */
    private Integer optType;

    /**
     * 用户id
     */
    private String userId;

    /**
     * ip
     */
    private String ip;

    /**
     * 内容
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 时间
     */
    @TableField("CREATETIME")
    private Date createtime;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 参数
     */
    private String parameter;


}

package com.data.itsv.model;

import java.io.Serializable;

public class SLogType implements Serializable {
    /**
     * 日志类型 编号
     */
    private Integer id;

    /**
     * 日志类型名称
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_log_type
     *
     * @mbg.generated Mon Dec 28 10:48:16 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
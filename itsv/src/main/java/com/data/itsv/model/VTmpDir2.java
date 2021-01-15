package com.data.itsv.model;

import java.io.Serializable;

public class VTmpDir2 implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer dirLevel;

    /**
     * 
     */
    private Integer parentId;

    /**
     * 目录编号
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_tmp_dir2
     *
     * @mbg.generated Mon Dec 28 11:02:10 CST 2020
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

    public Integer getDirLevel() {
        return dirLevel;
    }

    public void setDirLevel(Integer dirLevel) {
        this.dirLevel = dirLevel;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", dirLevel=").append(dirLevel);
        sb.append(", parentId=").append(parentId);
        sb.append(", code=").append(code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
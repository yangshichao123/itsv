package com.data.itsv.model;

import java.io.Serializable;

public class SMapInfo implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 配置项名称
     */
    private String name;

    /**
     * 服务地址
     */
    private String url;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 所属地
     */
    private String location;

    /**
     * 所属地拼音
     */
    private String location2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_map_info
     *
     * @mbg.generated Mon Dec 28 10:48:45 CST 2020
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2 == null ? null : location2.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", description=").append(description);
        sb.append(", location=").append(location);
        sb.append(", location2=").append(location2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
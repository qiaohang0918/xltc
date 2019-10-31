package com.qigan.qiganshop.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XltcLocations implements Serializable {
    private Integer id;

    private String name;

    private Integer parentId;

    private Byte level;


    //子区域集合
    private List<XltcLocations> childDepartments=new ArrayList<>();

    @Override
    public String toString() {
        return "XltcLocations{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                ", childDepartments=" + childDepartments +
                '}';
    }

    public List<XltcLocations> getChildDepartments() {
        return childDepartments;
    }

    public void setChildDepartments(List<XltcLocations> childDepartments) {
        this.childDepartments = childDepartments;
    }

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

}
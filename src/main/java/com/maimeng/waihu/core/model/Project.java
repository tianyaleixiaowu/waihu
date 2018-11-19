package com.maimeng.waihu.core.model;

import com.maimeng.waihu.core.model.base.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Entity
@Table(indexes = {@Index(name = "prjid", columnList = "prjid", unique = true)})
public class Project extends BaseIdEntity {
    private String prjid;
    /**
     * 项目类型（外呼、呼入、手工项目）
     */
    private String type;
    private String name;

    @Override
    public String toString() {
        return "Project{" +
                "prjid='" + prjid + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getPrjid() {
        return prjid;
    }

    public void setPrjid(String prjid) {
        this.prjid = prjid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

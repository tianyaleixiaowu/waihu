package com.maimeng.waihu.core.model;

import com.maimeng.waihu.core.model.base.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Entity
@Table(indexes = {@Index(name = "subid", columnList = "subid", unique = true)})
public class Sub extends BaseIdEntity {
    private String subid;
    /**
     * 预约名称
     */
    private String name;
    private String status;

    private String prjid;

    @Override
    public String toString() {
        return "Sub{" +
                "subid='" + subid + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", prjid='" + prjid + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrjid() {
        return prjid;
    }

    public void setPrjid(String prjid) {
        this.prjid = prjid;
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

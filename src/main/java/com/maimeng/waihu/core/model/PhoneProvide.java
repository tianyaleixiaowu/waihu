package com.maimeng.waihu.core.model;

import com.maimeng.waihu.core.model.base.BaseIdEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Entity
public class PhoneProvide extends BaseIdEntity {
    private String subid;
    private String prjid;
    /**
     * 孩子名称
     */
    private String name;
    private Integer age;
    private String mobile;

    private String remark1;
    private String remark2;
    private Date createTime;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

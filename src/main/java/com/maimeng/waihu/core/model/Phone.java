package com.maimeng.waihu.core.model;

import com.maimeng.waihu.core.model.base.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Entity
@Table(indexes = {@Index(name = "phone", columnList = "phone", unique = true)})
public class Phone extends BaseIdEntity {
    private String phone;
    private String value1;
    private String value2;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}

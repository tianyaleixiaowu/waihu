package com.maimeng.waihu.core.bean;

import com.maimeng.waihu.core.model.PhoneRecord;

import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public class PhoneRecordData extends BaseData {
    private String prjid;
    private String subid;
    private List<PhoneRecord> data;

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

    public List<PhoneRecord> getData() {
        return data;
    }

    public void setData(List<PhoneRecord> data) {
        this.data = data;
    }
}

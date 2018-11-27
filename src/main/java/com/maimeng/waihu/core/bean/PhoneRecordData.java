package com.maimeng.waihu.core.bean;

import com.maimeng.waihu.core.model.PhoneRecord;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.lang.Base64;

import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public class PhoneRecordData extends BaseData {
    private String prjid;
    private String subid;
    private String data;
    private List<PhoneRecord> records;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<PhoneRecord> getRecords() {
        String recordStr = Base64.decodeStr(data);
        try {
            JSONArray jsonArray = JSONUtil.parseArray(recordStr);
            return jsonArray.toList(PhoneRecord.class);
        } catch (Exception e) {
            return null;
        }
    }

    public void setRecords(List<PhoneRecord> records) {
        this.records = records;
    }
}

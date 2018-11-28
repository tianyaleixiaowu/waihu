package com.maimeng.waihu.core.bean;

import com.maimeng.waihu.core.model.Sub;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public class SubData extends BaseData {
    private String prjid;

    private List<Sub> data = new ArrayList<>();

    @Override
    public String toString() {
        return "SubData{" +
                "prjid='" + prjid + '\'' +
                ", data=" + data +
                '}';
    }

    public String getPrjid() {
        return prjid;
    }

    public void setPrjid(String prjid) {
        this.prjid = prjid;
    }

    public List<Sub> getData() {
        return data;
    }

    public void setData(List<Sub> data) {
        this.data = data;
    }
}

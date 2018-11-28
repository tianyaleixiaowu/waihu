package com.maimeng.waihu.core.bean;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public class SubCreateData extends BaseData {
    private String subid;
    /**
     * 预约名称
     */
    private String name;

    private String prjid;

    @Override
    public String toString() {
        return "SubCreateData{" +
                "subid='" + subid + '\'' +
                ", name='" + name + '\'' +
                ", prjid='" + prjid + '\'' +
                '}';
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

    public String getPrjid() {
        return prjid;
    }

    public void setPrjid(String prjid) {
        this.prjid = prjid;
    }
}

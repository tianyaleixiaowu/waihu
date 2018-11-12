package com.maimeng.waihu.core.bean;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public class PhoneImportData extends BaseData {
    private String subid;
    /**
     * 预约名称
     */
    private String name;

    private String prjid;

    private String total;

    private String successnum;

    //重复的数量
    private String repeatnum;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSuccessnum() {
        return successnum;
    }

    public void setSuccessnum(String successnum) {
        this.successnum = successnum;
    }

    public String getRepeatnum() {
        return repeatnum;
    }

    public void setRepeatnum(String repeatnum) {
        this.repeatnum = repeatnum;
    }
}

package com.maimeng.waihu.core.model;

import com.maimeng.waihu.core.model.base.BaseIdEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Entity
public class PhoneRecord extends BaseIdEntity {
    private String cname;//公司名称,
    private String prjname;//项目名称,
    private String deptname;//部门名称,
    private String uname;//用户名称,
    private String sphone;//主叫号码,
    private String dphone;//被叫号码,
    private String begintime;//发起时间,
    private String waittime;//等待时长（秒）,
    private String duration;//通话时长,
    private String bill;//费用(单位;//元),
    private String reason;//通话状态
    private String creason;//呼叫状态,
    private String status;//号码状态,
    private String content;//用户信息 此属性为数组，按客户模板属性对应顺序存放，例：["客户模板属性1的值","客户模板属性2的值","客户模板属性3的值"]

    private String subid;
    private String prjid;

    private Date begintimeTemp;

    private Date createTime = new Date();

    public Date getBegintimeTemp() {
        return begintimeTemp;
    }

    public void setBegintimeTemp(Date begintimeTemp) {
        this.begintimeTemp = begintimeTemp;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public String getPrjid() {
        return prjid;
    }

    public void setPrjid(String prjid) {
        this.prjid = prjid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPrjname() {
        return prjname;
    }

    public void setPrjname(String prjname) {
        this.prjname = prjname;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getWaittime() {
        return waittime;
    }

    public void setWaittime(String waittime) {
        this.waittime = waittime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreason() {
        return creason;
    }

    public void setCreason(String creason) {
        this.creason = creason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

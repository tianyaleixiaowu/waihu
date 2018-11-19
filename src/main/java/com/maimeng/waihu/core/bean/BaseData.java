package com.maimeng.waihu.core.bean;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public class BaseData {
    private String result;
    private String tokenid;

    @Override
    public String toString() {
        return "BaseData{" +
                "result='" + result + '\'' +
                ", tokenid='" + tokenid + '\'' +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }
}

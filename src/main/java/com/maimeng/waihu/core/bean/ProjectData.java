package com.maimeng.waihu.core.bean;

import com.maimeng.waihu.core.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public class ProjectData extends BaseData {
      private List<Project> data = new ArrayList<>();

    public List<Project> getData() {
        return data;
    }

    public void setData(List<Project> data) {
        this.data = data;
    }
}

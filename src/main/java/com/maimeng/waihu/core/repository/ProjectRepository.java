package com.maimeng.waihu.core.repository;

import com.maimeng.waihu.core.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findFirstByPrjid(String prjid);
}

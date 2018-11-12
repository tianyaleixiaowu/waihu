package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.ProjectData;
import com.maimeng.waihu.core.model.Project;
import com.maimeng.waihu.core.repository.ProjectRepository;
import com.maimeng.waihu.core.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
public class ProjectManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ProjectRepository projectRepository;

    @Scheduled(cron = "0 0/30 1 * * ?")
    public void fetchProject() {
        Map<String, String> map = new HashMap<>();
        map.put("func", "getproject");
        map.put("tokenid", getToken());

        try {
            ProjectData loginData = restTemplate.postForEntity(baseUrl, map,
                    ProjectData.class)
                    .getBody();
            logger.info("返回值：" + loginData);
            if (Constant.SUCCESS.equals(loginData.getResult())) {
                saveProject(loginData.getData());

            } else {
                logger.info("获取项目信息失败");
            }
        } catch (Exception e) {
            logger.info("获取项目信息失败");
            e.printStackTrace();
        }
    }

    private void save(Project project) {
        Project old = projectRepository.findFirstByPrjid(project.getPrjid());
        if (old == null) {
            projectRepository.save(project);
        }
    }

    private void saveProject(List<Project> projectList) {
        for (Project project : projectList) {
            save(project);
        }
    }
}
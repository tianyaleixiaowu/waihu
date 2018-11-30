package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.ProjectData;
import com.maimeng.waihu.core.model.Project;
import com.maimeng.waihu.core.repository.ProjectRepository;
import com.maimeng.waihu.core.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
@Order(2)
public class ProjectManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ProjectRepository projectRepository;

    @Scheduled(cron = "0 */30 * * * ?")
    public void fetchProject() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("func", "getproject");
        map.add("tokenid", getToken());

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

    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}

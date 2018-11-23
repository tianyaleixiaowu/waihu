package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.SubCreateData;
import com.maimeng.waihu.core.bean.SubData;
import com.maimeng.waihu.core.model.Project;
import com.maimeng.waihu.core.model.Sub;
import com.maimeng.waihu.core.repository.SubRepository;
import com.maimeng.waihu.core.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
public class SubManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SubRepository subRepository;
    @Resource
    private ProjectManager projectManager;

    @Scheduled(cron = "0 0/40 1 * * ?")
    public void pp() {
        List<Project> projectList = projectManager.findAll();
        for (Project project : projectList) {
            fetchsub(project.getPrjid());
        }
    }

    public void fetchsub(String prjid) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("func", "getsubscribe");
        map.add("tokenid", getToken());
        map.add("prjid", prjid);

        try {
            SubData subData = restTemplate.postForEntity(baseUrl, map,
                    SubData.class)
                    .getBody();
            logger.info("返回值：" + subData);
            if (Constant.SUCCESS.equals(subData.getResult())) {
                saveSub(subData);

            } else {
                logger.info("获取sub信息失败");
            }
        } catch (Exception e) {
            logger.info("获取sub信息失败");
            e.printStackTrace();
        }
    }

    public void createSub(String prjid, String memo) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("func", "createsubscribe");
        map.add("tokenid", getToken());
        map.add("prjid", prjid);
        map.add("memo", memo);

        try {
            SubCreateData subData = restTemplate.postForEntity(baseUrl, map,
                    SubCreateData.class)
                    .getBody();
            logger.info("返回值：" + subData);
            if (Constant.SUCCESS.equals(subData.getResult())) {
                saveSub(subData);

            } else {
                logger.info("获取sub信息失败");
            }
        } catch (Exception e) {
            logger.info("获取sub信息失败");
            e.printStackTrace();
        }
    }


    private void save(Sub sub) {
        Sub old = subRepository.findFirstBySubid(sub.getSubid());
        if (old == null) {
            subRepository.save(sub);
        }
    }

    private void saveSub(SubCreateData subData) {
        Sub sub = new Sub();
        BeanUtils.copyProperties(subData, sub);
        save(sub);
    }

    private void saveSub(SubData subData) {
        String prjid = subData.getPrjid();
        for (Sub sub : subData.getData()) {
            sub.setPrjid(prjid);
            save(sub);
        }
    }
}

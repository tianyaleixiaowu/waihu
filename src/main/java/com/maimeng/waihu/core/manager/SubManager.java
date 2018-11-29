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
@Order(3)
public class SubManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SubRepository subRepository;
    @Resource
    private ProjectManager projectManager;

    @Scheduled(cron = "0 0/45 * * * ?")
    public void fetch() {
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

    public Sub createSub(String prjid, String memo) {
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
                return saveSub(subData, memo);
            } else {
                logger.info("获取sub信息失败");
                return null;
            }
        } catch (Exception e) {
            logger.info("获取sub信息失败");
            e.printStackTrace();
            return null;
        }
    }


    private Sub save(Sub sub) {
        Sub old = subRepository.findFirstBySubid(sub.getSubid());
        if (old == null) {
            return subRepository.save(sub);
        }
        return old;
    }

    public Sub findByName(String name) {
        return subRepository.findFirstByName(name);
    }

    public List<Sub> findAll() {
        return subRepository.findAll();
    }

    private Sub saveSub(SubCreateData subData, String memo) {
        Sub sub = new Sub();
        BeanUtils.copyProperties(subData, sub);
        sub.setName(memo);
        return save(sub);
    }

    private void saveSub(SubData subData) {
        String prjid = subData.getPrjid();
        if (subData.getData() == null) {
            return;
        }
        for (Sub sub : subData.getData()) {
            sub.setPrjid(prjid);
            save(sub);
        }
    }
}

package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.SubCreateData;
import com.maimeng.waihu.core.bean.SubData;
import com.maimeng.waihu.core.model.Sub;
import com.maimeng.waihu.core.repository.SubRepository;
import com.maimeng.waihu.core.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
public class SubManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SubRepository subRepository;

    public void fetchProject(String prjid) {
        Map<String, String> map = new HashMap<>();
        map.put("func", "getsubscribe");
        map.put("tokenid", getToken());
        map.put("prjid", prjid);

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
        Map<String, String> map = new HashMap<>();
        map.put("func", "createsubscribe");
        map.put("tokenid", getToken());
        map.put("prjid", prjid);
        map.put("memo", memo);

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

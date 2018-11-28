package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.model.PhoneProvide;
import com.maimeng.waihu.core.model.Sub;
import com.maimeng.waihu.core.repository.PhoneProvideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/20.
 */
@Service
public class PhoneProvideManager {
    @Resource
    private PhoneProvideRepository phoneProvideRepository;

    @Resource
    private SubManager subManager;

    @Resource
    private PhoneImportManager phoneImportManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0/30 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void importData() throws InterruptedException {
        PhoneProvide phoneProvide = phoneProvideRepository.findFirstByOrderById();
        if (phoneProvide == null) {
            return;
        }
        //取第一个的预约名字
        String subname = phoneProvide.getSubname();
        logger.info("预约的名字是：" + subname);

        Sub sub = subManager.findByName(subname);
        if (sub == null) {
            sub = subManager.createSub(phoneProvide.getPrjid(), subname);
            logger.info("创建预约：" + sub);
        }

        //如果创建失败
        if (sub == null) {
            return;
        }
        //给subid赋值
        phoneProvideRepository.updateSubid(sub.getSubid(), subname);
        logger.info("给数据库subid赋值");

        List<PhoneProvide> phoneProvides = phoneProvideRepository.findBySubid(sub.getSubid());
        logger.info("本预约的数量：" + phoneProvides.size());

        List<List<PhoneProvide>> lists = groupList(phoneProvides);
        for (List<PhoneProvide> list : lists) {
            logger.info("导入开始----");
            phoneImportManager.importPhone(list);
            Thread.sleep(5000);
            delete(list);
            logger.info("已删除----");
        }

    }

    private void delete(List<PhoneProvide> list) {
        phoneProvideRepository.deleteAll(list);
    }

    public static List<List<PhoneProvide>> groupList(List<PhoneProvide> list) {
        List<List<PhoneProvide>> listGroup = new ArrayList<>();
        int listSize = list.size();
        //子集合的长度
        int toIndex = 500;
        for (int i = 0; i < list.size(); i += 100) {
            if (i + 100 > listSize) {
                toIndex = listSize - i;
            }
            List<PhoneProvide> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }

    public List<PhoneProvide> findByIdBetween(Integer beginId, Integer endId) {
        return phoneProvideRepository.findByIdBetween(beginId, endId);
    }
    
}

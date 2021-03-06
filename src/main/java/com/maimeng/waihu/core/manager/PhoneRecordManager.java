package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.PhoneRecordData;
import com.maimeng.waihu.core.model.PhoneRecord;
import com.maimeng.waihu.core.model.Sub;
import com.maimeng.waihu.core.repository.PhoneRecordRepository;
import com.maimeng.waihu.core.util.Constant;
import com.xiaoleilu.hutool.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
@Order(4)
public class PhoneRecordManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PhoneRecordRepository phoneRecordRepository;
    @Resource
    private SubManager subManager;

    @Scheduled(cron = "0 */15 * * * ?")
    public void fetchRecord() throws InterruptedException {
        List<Sub> projectList = subManager.findAll();
        for (Sub sub : projectList) {
            logger.info("拉取subId为" + sub.getSubid() + ",subName=" + sub.getName() + "的通话记录");
            record(sub.getPrjid(), sub.getSubid());
            Thread.sleep(50000);
        }
    }

    public void record(String prjid, String subid) {
        //找到最新的一条
        PhoneRecord phoneRecord = phoneRecordRepository.findFirstBySubidOrderByBegintimeTempDesc(subid);
        Long begin;
        if (phoneRecord == null) {
            begin = DateUtil.beginOfDay(new Date()).getTime();
        } else {
            begin = phoneRecord.getBegintimeTemp();
        }

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("func", "exportcallrecord");
        map.add("prjid", prjid);
        //非必填
        map.add("subid", subid);
        map.add("starttime", begin / 1000 + "");
        map.add("endtime", System.currentTimeMillis() / 1000 + "");
        map.add("tokenid", getToken());

        try {
            PhoneRecordData phoneRecordData = restTemplate.postForEntity(baseUrl, map,
                    PhoneRecordData.class)
                    .getBody();
            logger.info("返回值：" + phoneRecordData);
            if (Constant.SUCCESS.equals(phoneRecordData.getResult())) {
                save(phoneRecordData);
            } else {
                logger.info("获取通话记录失败");
            }
        } catch (Exception e) {
            logger.info("获取通话记录失败");
            e.printStackTrace();
        }
    }
    
    private void save(PhoneRecordData phoneRecordData) {
          String prjid = phoneRecordData.getPrjid();
          String subid = phoneRecordData.getSubid();
          for (PhoneRecord phoneRecord : phoneRecordData.getRecords()) {
              phoneRecord.setPrjid(prjid);
              phoneRecord.setSubid(subid);
              phoneRecord.setBegintimeTemp(DateUtil.parse(phoneRecord.getBegintime()).getTime());
              phoneRecordRepository.save(phoneRecord);
          }
    }
}

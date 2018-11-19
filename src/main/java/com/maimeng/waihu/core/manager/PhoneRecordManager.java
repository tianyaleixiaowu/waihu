package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.PhoneRecordData;
import com.maimeng.waihu.core.model.PhoneRecord;
import com.maimeng.waihu.core.repository.PhoneRecordRepository;
import com.maimeng.waihu.core.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
public class PhoneRecordManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PhoneRecordRepository phoneRecordRepository;

    public void record() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("func", "exportcallrecord");
        map.add("prjid", "");
        //非必填
        map.add("subid", "");
        map.add("starttime", "");
        map.add("endtime", "");
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
          for (PhoneRecord phoneRecord : phoneRecordData.getData()) {
              phoneRecord.setPrjid(prjid);
              phoneRecord.setSubid(subid);
              phoneRecordRepository.save(phoneRecord);
          }
    }
}

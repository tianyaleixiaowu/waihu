package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.PhoneImportData;
import com.maimeng.waihu.core.model.PhoneImport;
import com.maimeng.waihu.core.repository.PhoneImportRepository;
import com.maimeng.waihu.core.util.Constant;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.lang.Base64;
import com.xiaoleilu.hutool.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
public class PhoneImportManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private PhoneImportRepository phoneImportRepository;

    public void phoneImport() {
        //key是phoneNum，value是备注的集合。
        // "13412345678（客户号码）":["客户模板属性1对应的值","客户模板属性2对应的值","客户模板属性3对应的值"]
        Map<String, List<String>> phoneMap = new HashMap<>();
        phoneMap.put("123", Arrays.asList("1", "2", "3"));
        String jsonStr = JSONUtil.toJsonStr(phoneMap);
        String phone = Base64.encode(jsonStr);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("func", "importnumber");
        map.add("tokenid", getToken());
        map.add("prjid", getToken());
        map.add("subid", getToken());
        map.add("data", phone);
        //去重方式，1 表示不去重，2 表示本列表去重，3 本预约去重，4 本项目去重
        map.add("duplicates", phone);

        try {
            PhoneImportData phoneImportData = restTemplate.postForEntity(baseUrl, map,
                    PhoneImportData.class)
                    .getBody();
            logger.info("返回值：" + phoneImportData);
            if (Constant.SUCCESS.equals(phoneImportData.getResult())) {
                //成功
                PhoneImport phoneImport = new PhoneImport();
                BeanUtil.copyProperties(phoneImportData, phoneImport);

                save(phoneImport);
            } else {
                logger.info("获取sub信息失败");
            }
        } catch (Exception e) {
            logger.info("获取sub信息失败");
            e.printStackTrace();
        }
        
    }

    private void save(PhoneImport phoneImport) {
        phoneImportRepository.save(phoneImport);
    }

}

package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.PhoneImportData;
import com.maimeng.waihu.core.model.PhoneImport;
import com.maimeng.waihu.core.model.PhoneProvide;
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
import java.util.*;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
public class PhoneImportManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private PhoneImportRepository phoneImportRepository;
    @Resource
    private PhoneProvideManager phoneProvideManager;

    public String phoneImport(String subid, String prjid, Map<String, List<String>> phoneMap) {
        //key是phoneNum，value是备注的集合。
        // "13412345678（客户号码）":["客户模板属性1对应的值","客户模板属性2对应的值","客户模板属性3对应的值"]
        //phoneMap.put("123", Arrays.asList("1", "2", "3"));
        String jsonStr = JSONUtil.toJsonStr(phoneMap);
        String phone = Base64.encode(jsonStr);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("func", "importnumber");
        map.add("tokenid", getToken());
        map.add("prjid", prjid);
        map.add("subid", subid);
        map.add("data", phone);
        //去重方式，1 表示不去重，2 表示本列表去重，3 本预约去重，4 本项目去重
        map.add("duplicates", "1");

        System.out.println(map);
        try {
            String s = restTemplate.postForEntity(baseUrl, map,
                    String.class)
                    .getBody();

            logger.info("返回值：" + s);
            PhoneImportData phoneImportData = JSONUtil.toBean(JSONUtil.parseObj(s), PhoneImportData.class);
            if (Constant.SUCCESS.equals(phoneImportData.getResult())) {
                //成功
                PhoneImport phoneImport = new PhoneImport();
                BeanUtil.copyProperties(phoneImportData, phoneImport);

                save(phoneImport);
                return "成功";
            } else {
                logger.info("导入失败");
                return "导入失败";
            }
        } catch (Exception e) {
            logger.info("导入失败");
            e.printStackTrace();
            return "导入失败";
        }

    }


    public String importPhone(List<PhoneProvide> phoneProvides) {
        //按subid进行分类
        MultiValueMap<String, PhoneProvide> multiValueMap = new LinkedMultiValueMap<>();
        for (PhoneProvide phoneProvide : phoneProvides) {
            multiValueMap.add(phoneProvide.getSubid(), phoneProvide);
        }
        Set<String> keySet = multiValueMap.keySet();
        List<String> result = new ArrayList<>();
        for (String subId : keySet) {
            List<PhoneProvide> list = ((LinkedMultiValueMap<String, PhoneProvide>) multiValueMap).get(subId);

            Map<String, List<String>> phoneMap = new HashMap<>();
            //phoneImport(String subid, String prjid, Map < String, List < String >> phoneMap)
            for (PhoneProvide phoneProvide : list) {
                //phoneMap.put(phoneProvide.getMobile(), Arrays.asList(phoneProvide.getName(), phoneProvide.getAge() + "",
                //        phoneProvide.getRemark1()));
                phoneMap.put(phoneProvide.getMobile(), Arrays.asList(
                                phoneProvide.getRemark1(), phoneProvide.getRemark2()));
            }

            result.add(phoneImport(subId, list.get(0).getPrjid(), phoneMap));
        }

        return result.toString();
    }

    public String importPhone(Integer begin, Integer end) {
        List<PhoneProvide> phoneProvides = phoneProvideManager.findByIdBetween(begin, end);
        return importPhone(phoneProvides);
    }


    private void save(PhoneImport phoneImport) {
        phoneImportRepository.save(phoneImport);
    }

}

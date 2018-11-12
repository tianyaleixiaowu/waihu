package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.LoginData;
import com.maimeng.waihu.core.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
public class LoginManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 半小时登录一次
     */
    @Scheduled(cron = "0 0/30 0 * * ?")
    public void login() {
        Map<String, String> map = new HashMap<>();
        map.put("func", "login");
        map.put("corp", "");
        map.put("uname", "");
        map.put("passwd", "");

        try {
            LoginData loginData = restTemplate.postForEntity(baseUrl, map,
                    LoginData.class)
                    .getBody();
            logger.info("返回值：" + loginData);
            if (Constant.SUCCESS.equals(loginData.getResult())) {
                //存入缓存
                userCache.saveToken(loginData.getTokenid());
            } else {
                logger.info("登录失败");
            }
        } catch (Exception e) {
            logger.info("-------登录失败------返回默认用户");
            e.printStackTrace();
        }
    }


}

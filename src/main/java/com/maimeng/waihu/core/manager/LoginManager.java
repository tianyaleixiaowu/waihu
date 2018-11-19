package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.core.bean.LoginData;
import com.maimeng.waihu.core.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
public class LoginManager extends BaseManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${base.username}")
    protected String username;
    @Value("${base.corpname}")
    protected String corpname;
    @Value("${base.password}")
    protected String password;
    /**
     * 半小时登录一次
     */
    @Scheduled(cron = "0 0/30 0 * * ?")
    public void login() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("func", "login");
        map.add("corpname", "mmkj");
        map.add("username", "1000");
        map.add("password", "010888");

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

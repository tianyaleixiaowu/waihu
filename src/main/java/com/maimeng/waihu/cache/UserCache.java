package com.maimeng.waihu.cache;

import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/4/17.
 */
@Component
public class UserCache {
    @Resource
    private Cache cache;

    public void saveToken(String token) {
        cache.put("token", token);
    }

    public String getToken() {
        Object token = cache.get("token");
        if (token == null) {
            return null;
        }
        return token.toString();
    }
}

package com.maimeng.waihu.core.manager;

import com.maimeng.waihu.cache.UserCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@Service
public class BaseManager {
    @Value("${base.uri}")
    protected String baseUrl;

    @Resource
    protected RestTemplate restTemplate;
    @Resource
    protected UserCache userCache;

    protected String getToken() {
        return userCache.getToken();
    }
}

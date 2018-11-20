package com.maimeng.waihu.core.controller;

import com.maimeng.waihu.core.manager.PhoneImportManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * http://dev.weygo.cn
 * 公司编号 2002  管理员1000 密码 010888
 * 这个开发帐号可以用来做接口调试
 *
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("/phoneImport")
public class PhoneImportController {
    @Resource
    private PhoneImportManager phoneImportManager;


    @RequestMapping
    public Object importData(Integer beginId, Integer endId) {
        return phoneImportManager.importPhone(beginId, endId);
    }


}

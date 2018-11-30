package com.maimeng.waihu.core.controller;

import com.maimeng.waihu.core.manager.ProjectManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/30.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectManager projectManager;

    @RequestMapping
    public Object fetch() {
        projectManager.fetchProject();

        return "ok";
    }
}

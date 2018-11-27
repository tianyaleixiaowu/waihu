package com.maimeng.waihu;

import com.maimeng.waihu.core.manager.LoginManager;
import com.maimeng.waihu.core.manager.PhoneRecordManager;
import com.maimeng.waihu.core.manager.ProjectManager;
import com.maimeng.waihu.core.manager.SubManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class WaihuApplication {
    @Resource
    private LoginManager loginManager;
    @Resource
    private ProjectManager projectManager;
    @Resource
    private SubManager subManager;
    @Resource
    private PhoneRecordManager phoneRecordManager;

    @PostConstruct
    public void exe() throws InterruptedException {
        loginManager.login();
        Thread.sleep(1000);
        projectManager.fetchProject();
        Thread.sleep(2000);
        subManager.fetch();
        Thread.sleep(3000);
        phoneRecordManager.fetchRecord();
    }

    public static void main(String[] args) {
        SpringApplication.run(WaihuApplication.class, args);
    }
}

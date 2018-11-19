package com.maimeng.waihu;

import com.maimeng.waihu.core.manager.LoginManager;
import com.maimeng.waihu.core.manager.ProjectManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaihuApplicationTests {
    @Resource
    private LoginManager loginManager;
    @Resource
    private ProjectManager projectManager;



	@Test
	public void contextLoads() {
        loginManager.login();
        projectManager.fetchProject();
	}

}

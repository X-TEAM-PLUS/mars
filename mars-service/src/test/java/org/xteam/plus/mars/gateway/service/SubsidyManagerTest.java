package org.xteam.plus.mars.gateway.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xteam.plus.mars.manager.UserRelationManager;
import org.xteam.plus.mars.service.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Service.class)
public class SubsidyManagerTest {
    @Resource
    private UserRelationManager userRelationManager;

    @Test
    public void queryMyTeamCountAndNewUserLevelCount() {
        try {
            Date beginDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-01");
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-31");
            HashMap map = userRelationManager.queryMyTeamCountAndNewUserLevelCount(new BigDecimal(2000000), beginDate, endDate);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

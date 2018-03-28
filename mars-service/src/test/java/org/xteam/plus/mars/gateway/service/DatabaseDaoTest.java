package org.xteam.plus.mars.gateway.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xteam.plus.mars.dao.UserInfoDao;
import org.xteam.plus.mars.dao.UserRelationDao;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.domain.UserRelation;
import org.xteam.plus.mars.service.Service;
import org.xteam.plus.mars.type.UserLevelEnum;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Service.class)
public class DatabaseDaoTest {

    @Resource
    private UserRelationDao userRelationDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Test
    public void createUserRelationData() throws Exception {
        for (int i = 0; i <= 1500; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setRegisterTime(new Date());
            userInfo.setUserLevel(UserLevelEnum.MEMBER.getCode());
            userInfo.setStatus(0);
            userInfo.setCreated(new Date());
            userInfoDao.insert(userInfo);
            UserRelation userRelation = new UserRelation();
            userRelation.setRefereeUserId(new BigDecimal(2000000));
            userRelation.setUserId(userInfo.getUserId());
            userRelation.setCreated(new Date());
            userRelationDao.insert(userRelation);
        }
    }
}

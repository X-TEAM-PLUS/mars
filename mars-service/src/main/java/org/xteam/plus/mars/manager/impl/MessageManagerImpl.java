package org.xteam.plus.mars.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xteam.plus.mars.dao.MessageDao;
import org.xteam.plus.mars.domain.Message;
import org.xteam.plus.mars.manager.MessageManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-02-02
 * Time: 19:34
 * 功能:T_MARS_MESSAGE表Manager接口实现类
 */

@Service
public class MessageManagerImpl implements MessageManager {
    private static final Log log = LogFactory.getLog(MessageManagerImpl.class);
    @javax.annotation.Resource
    private MessageDao messageDao;


    @Override
    public Message get(Message message) throws Exception {
        return messageDao.get(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Message message) throws Exception {
        return messageDao.insert(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsert(List<Message> list) throws Exception {
        return messageDao.batchInsert(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Message message) throws Exception {
        return messageDao.delete(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Message message) throws Exception {
        return messageDao.update(message);
    }

    @Override
    public List<Message> query(Message message) throws Exception {
        return messageDao.query(message);
    }

    @Override
    public Integer queryCount(Message message) throws Exception {
        return messageDao.queryCount(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sendMessage(BigDecimal userId, String title, String message) throws Exception {
        return messageDao.insert(new Message()
                .setUserId(userId)
                .setMessageTitle(title)
                .setMessageContent(message)
                .setStatus(0)
                .setCreated(new Date())
        );
    }

}

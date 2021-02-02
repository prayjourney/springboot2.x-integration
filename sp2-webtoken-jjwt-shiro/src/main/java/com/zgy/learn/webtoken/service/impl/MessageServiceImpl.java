package com.zgy.learn.webtoken.service.impl;

import com.zgy.learn.webtoken.dao.MessageDao;
import com.zgy.learn.webtoken.entity.Message;
import com.zgy.learn.webtoken.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2021-02-02 15:36:44
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Message queryById(Integer id) {
        return this.messageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Message> queryAllByLimit(int offset, int limit) {
        return this.messageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message insert(Message message) {
        this.messageDao.insert(message);
        return message;
    }

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message update(Message message) {
        this.messageDao.update(message);
        return this.queryById(message.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.messageDao.deleteById(id) > 0;
    }
}
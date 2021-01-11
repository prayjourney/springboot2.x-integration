package com.zgy.bootintegration.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zgy.bootintegration.pojo.Kid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    // 查询所有的Kid
    public List<Kid> queryAll() {
        return mongoTemplate.findAll(Kid.class);
    }

    // 按照ID查询
    public Kid queryById(Integer id) {
        if (id <= 0 || null == id) {
            throw new RuntimeException("stId有异常！");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Kid.class);
    }

    // 插入Kid
    public Kid insert(Kid kid) {
        if (kid.getId() <= 0) {
            throw new RuntimeException("kidId有异常！");
        }
        return mongoTemplate.insert(kid, "kid");
    }

    // 按照ID删除
    public Long delete(Integer kidId) {
        if (kidId <= 0 || null == kidId) {
            throw new RuntimeException("kidId有异常！");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(kidId));
        DeleteResult result = mongoTemplate.remove(query, Kid.class);
        if (result.getDeletedCount() >= 1) {
            return result.getDeletedCount();
        } else {
            return -1L;
        }
    }

    // 更新一个Kid
    public Long update(Kid kid) {
        if (kid.getId() <= 0) {
            throw new RuntimeException("kidId有异常！");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(kid.getId()));
        Update update = new Update();
        // 主键不是id，而是_id
        // 每一个字段，都要去按照key来更新
        update.set("id", kid.getId()).set("username", kid.getUsername()).set("password", kid.getPassword());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Kid.class, "kid");
        long modifiedCount = updateResult.getModifiedCount();
        if (modifiedCount == 1) {
            return 1L;
        } else {
            return -1L;
        }
    }
}

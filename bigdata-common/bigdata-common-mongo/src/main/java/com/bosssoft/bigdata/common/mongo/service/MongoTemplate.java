package com.bosssoft.bigdata.common.mongo.service;

import com.bosssoft.bigdata.common.mongo.enums.MongoOperation;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: study-bigdata-mongo
 * @description: mongo 交互类
 * @author: Mr.Lucky
 * @create: 2019-03-08 16:48
 **/
@RequiredArgsConstructor
@Slf4j
public class MongoTemplate implements InitializingBean {
    private final String endpoint;
    private final int port;
    private final String dbName;
    private final String accessKey;
    private final String secretKey;
    private MongoClient mongoClient;

    //######################################

    /**
     * @param dbName   数据库名称
     * @param collName 表名称
     * @return {@link }
     * @Title getCollection
     * @Description 获取指定数据库的指定表
     * @since 2019-03-08
     */
    public MongoCollection<Document> getCollection(String dbName, String collName) {
        if (StringUtils.isEmpty(collName)) {
            return null;
        }
        if (StringUtils.isEmpty(dbName)) {
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;
    }

    /**
     * @param collName 表名称
     * @return {@link }
     * @Title getDefaultCollection
     * @Description 获取默认数据库表
     * @since 2019-03-08
     */
    public MongoCollection<Document> getDefaultCollection(String collName) {
        if (StringUtils.isEmpty(collName)) {
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;
    }

    /**
     * @param coll 表
     * @param id   主键  mongodb默认主键名称为‘_id’ 无法修改
     * @return {@link }
     * @Title findById
     * @Description 通过id查询
     * @since 2019-03-08
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coll.find(Filters.eq("_id", _idobj)).first();
    }

    /**
     * @param coll   表
     * @param filter 条件
     * @return {@link }
     * @Title find
     * @Description 条件查询
     * @since 2019-03-08
     */
    public MongoCursor<Document> findByFilter(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /**
     * @param coll     表
     * @param filter   查询条件
     * @param sort     排序条件  使用 1 和 -1 来指定排序的方式，其中 1 为升序排列，而-1是用于降序排列。
     * @param pageNo   需要跳转页
     * @param pageSize 每页大小
     * @return {@link }
     * @Title findByPage
     * @Description 分页查询
     * @since 2019-03-08
     */
    public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, Bson sort, int pageNo, int pageSize) {
        if (sort == null) {
            sort = new BasicDBObject("_id", 1);
        }
        return coll.find(filter).sort(sort).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }

    /**
     * @param coll 表
     * @return {@link }
     * @Title getCount
     * @Description 查询表中记录条数
     * @since 2019-03-08
     */
    public int getCount(MongoCollection<Document> coll) {
        int count = (int) coll.count();
        return count;
    }

    /**
     * @param coll 表
     * @param id   mongodb 默认主键 ‘_id’
     * @return 被删除的条数
     * {@link }
     * @Title deleteById
     * @Description 通过id删除
     * @since 2019-03-08
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _id);
        DeleteResult deleteResult = coll.deleteOne(filter);
        return (int) deleteResult.getDeletedCount();
    }

    /**
     * @param coll   表
     * @param filter 条件
     * @return {@link }
     * @Title deleteByFilter
     * @Description 按条件删除
     * @since 2019-03-08
     */
    public int deleteByFilter(MongoCollection<Document> coll, Bson filter) {
        DeleteResult deleteResult = coll.deleteMany(filter);
        return (int) deleteResult.getDeletedCount();
    }

    /**
     * @param coll   表名称
     * @param id     mongodb默认主键 ‘_id’
     * @param newdoc 被修改后的对象
     * @return 返回被修改的条数
     * {@link }
     * @Title updateById
     * @Description 通过Id修改
     * @since 2019-03-08
     */
    public int updateById(MongoCollection<Document> coll, String id, Document newdoc) {
        ObjectId _idobj = null;
        try {
            _idobj = new ObjectId(id);
        } catch (Exception e) {
            return 0;
        }
        Bson filter = Filters.eq("_id", _idobj);
        UpdateResult updateResult = coll.updateOne(filter, new Document(MongoOperation.SET.getOperStr(), newdoc));
        return (int) updateResult.getModifiedCount();
    }

    /**
     * @param coll   表
     * @param filter 条件
     * @param newdoc 被修改后的对象
     * @return 被修改的条数
     * {@link }
     * @Title updateByFilter
     * @Description 按条件修改
     * @since 2019-03-08
     */
    public int updateByFilter(MongoCollection<Document> coll, Bson filter, Document newdoc) {
        UpdateResult updateResult = coll.updateMany(filter, new Document(MongoOperation.SET.getOperStr(), newdoc));
        return (int) updateResult.getModifiedCount();
    }

    /**
     * @param coll     表
     * @param document 需新增对象  若没有设置'_id' 则mongodb自动生成
     *                 {@link }
     * @Title insertOne
     * @Description 新增一个
     * @since 2019-03-08
     */
    public void insertOne(MongoCollection<Document> coll, Document document) {
        coll.insertOne(document);
    }

    /**
     * @param coll      表
     * @param documents 需新增对象
     *                  {@link }
     * @Title insertMany
     * @Description 新增多个  若没有设置'_id' 则mongodb自动生成
     * @since 2019-03-08
     */
    public void insertMany(MongoCollection<Document> coll, List<Document> documents) {
        coll.insertMany(documents);
    }

    //######################################

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(endpoint, "Mongo url 为空");
        Assert.hasText(String.valueOf(port), "Mongo port 为空");
        Assert.hasText(dbName, "Mongo dbName 为空");
        Assert.hasText(accessKey, "Mongo accessKey 为空");
        Assert.hasText(secretKey, "Mongo secretKey 为空");
        log.info("MongoDb初始化＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝Begin");
        try {
//            //实现不需要写入返回信息的场景，快速到达写操作
//            MongoClientOptions options = MongoClientOptions.builder()
//                    .writeConcern(WriteConcern.UNACKNOWLEDGED)
//                    .build();
            //连接到 mongodb 服务
            this.mongoClient = new MongoClient(endpoint, port);
        } catch (Exception e) {
            throw new RuntimeException("load resource fail, uri: errorMsg:" + e.getMessage(), e);
        } finally {

        }
        log.info("MongoDb初始化＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝end");
    }

}

package com.bosssoft.bigdata.common.mongo.util;

/**
 * @Author: Lucky
 * @Description:
 * @Date: Created in 18:30 2019/3/9
 * @Modified By:
 */
import java.util.List;

import com.bosssoft.bigdata.common.mongo.dao.MongoDbDao;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
 * @Title MongoDbClient.java
 * @Description MongoDb组件对外服务类
 * @author night
 * @date 2019/3/9
 * @version V1.0
 */
public class MongoDbUtils {
    /**
     *
     * @Title getCollection
     * @Description 获取指定数据库的指定表
     * @param dbName
     *            数据库名称
     * @param collName
     *            表名称
     * @return {@link }
     * @since 2019/3/9
     */
    public static MongoCollection<Document> getCollection(String dbName,String collName) {
        return MongoDbDao.INSTANCE.getCollection(dbName, collName);
    }
    /**
     *
     * @Title getDefaultCollection
     * @Description 获取默认数据库表
     * @param collName	表名称
     * @return
     * {@link }
     * @since 2019/3/9
     */
    public static MongoCollection<Document> getDefaultCollection(String collName) {
        return MongoDbDao.INSTANCE.getDefaultCollection(collName);
    }
    /**
     *
     * @Title findById
     * @Description 通过id查询
     * @param collName	表名
     * @param id	主键  mongodb默认主键名称为‘_id’ 无法修改
     * @return
     * {@link }
     * @since 2019/3/9
     */
    public static Document findById(String collName, String id) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return MongoDbDao.INSTANCE.findById(coll, id);
    }
    /**
     *
     * @Title find
     * @Description 条件查询
     * @param collName	表名
     * @param filter	条件
     * @return
     * {@link }
     * @since 2019/3/9
     */
    public static MongoCursor<Document> findByFilter(String collName, Bson filter) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return MongoDbDao.INSTANCE.findByFilter(coll, filter);
    }

    /**
     *
     * @Title findByPage
     * @Description 分页查询
     * @param collName	表名
     * @param filter	查询条件
     * @param sort		排序条件  使用 1 和 -1 来指定排序的方式，其中 1 为升序排列，而-1是用于降序排列。
     * @param pageNo	需要跳转页
     * @param pageSize	每页大小
     * @return
     * {@link }
     * @since 2019/3/9
     */
    public static MongoCursor<Document> findByPage(String collName, Bson filter,Bson sort , int pageNo, int pageSize) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return MongoDbDao.INSTANCE.findByPage(coll, filter, sort, pageNo, pageSize);
    }

    /**
     *
     * @Title getCount
     * @Description 查询表中记录条数
     * @param collName	表名
     * @return
     * {@link }
     * @since 2019/3/9
     */
    public static int getCount(String collName) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return MongoDbDao.INSTANCE.getCount(coll);
    }

    /**
     *
     * @Title deleteById
     * @Description 通过id删除
     * @param collName	表名
     * @param id	mongodb 默认主键 ‘_id’
     * @return 被删除的条数
     * {@link }
     * @since 2019/3/9
     */
    public static int deleteById(String collName, String id) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return MongoDbDao.INSTANCE.deleteById(coll, id);
    }
    /**
     *
     * @Title deleteByFilter
     * @Description 按条件删除
     * @param collName	表名
     * @param filter	条件
     * @return
     * {@link }
     * @since 2019/3/9
     */
    public static int deleteByFilter(String collName, Bson filter){
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return MongoDbDao.INSTANCE.deleteByFilter(coll, filter);
    }
    /**
     *
     * @Title updateById
     * @Description 通过Id修改
     * @param collName	表名称
     * @param id	mongodb默认主键 ‘_id’
     * @param newdoc	被修改后的对象
     * @return 返回被修改的条数
     * {@link }
     * @since 2019/3/9
     */
    public static int updateById(String collName, String id, Document newdoc) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return MongoDbDao.INSTANCE.updateById(coll, id, newdoc);
    }
    /**
     *
     * @Title updateByFilter
     * @Description 按条件修改
     * @param collName	表名称
     * @param filter	条件
     * @param newdoc	被修改后的对象
     * @return 被修改的条数
     * {@link }
     * @since 2019/3/9
     */
    public static int updateByFilter(String collName,  Bson filter, Document newdoc){
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return MongoDbDao.INSTANCE.updateByFilter(coll, filter, newdoc);
    }

    /**
     *
     * @Title insertOne
     * @Description 新增一个
     * @param collName	表名称
     * @param document 需新增对象  若没有设置'_id' 则mongodb自动生成
     * {@link }
     * @since 2019/3/9
     */
    public static void insertOne(String collName, Document document){
        MongoCollection<Document> coll = getDefaultCollection(collName);
        MongoDbDao.INSTANCE.insertOne(coll, document);;
    }

    /**
     *
     * @Title insertMany
     * @Description 新增多个  若没有设置'_id' 则mongodb自动生成
     * @param collName	表名
     * @param documents	需新增对象
     * {@link }
     * @since 2019/3/9
     */
    public static void insertMany(String collName, List<Document> documents){
        MongoCollection<Document> coll = getDefaultCollection(collName);
        MongoDbDao.INSTANCE.insertMany(coll, documents);
    }
}

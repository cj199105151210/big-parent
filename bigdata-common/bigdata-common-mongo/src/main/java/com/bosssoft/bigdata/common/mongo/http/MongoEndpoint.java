package com.bosssoft.bigdata.common.mongo.http;

import java.util.List;
import com.bosssoft.bigdata.common.mongo.service.MongoTemplate;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: study-bigdata-mongo
 * @description: MongoDb组件对外服务类端点
 * @author: Mr.Lucky
 * @create: 2019-03-08 16:48
 **/
@ConditionalOnProperty(name = "mongo.endpoint.enable", havingValue = "true")
@RestController
@AllArgsConstructor
@RequestMapping("${mongo.endpoint.name:/mongo}")
public class MongoEndpoint {
    private final MongoTemplate template;

    @SneakyThrows
    @GetMapping("/mongo/{mongoName}")
    public Document findById(@PathVariable String collName) {
        MongoCollection<Document> coll = template.getDefaultCollection(collName);
        Document document = template.findById(coll, "5c8245745787f52eecb209c3");
        System.out.println("★★★Lucky★★★ mongo值=" + document.toJson());
        return document;
    }

    /**
     *
     * @Title getCollection
     * @Description 获取指定数据库的指定表
     * @param dbName
     *            数据库名称
     * @param collName
     *            表名称
     * @return {@link }
     * @since 2019-03-08
     */
    public MongoCollection<Document> getCollection(String dbName,String collName) {
        return template.getCollection(dbName, collName);
    }
    /**
     *
     * @Title getDefaultCollection
     * @Description 获取默认数据库表
     * @param collName	表名称
     * @return
     * {@link }
     * @since 2019-03-08
     */
    public MongoCollection<Document> getDefaultCollection(String collName) {
        return template.getDefaultCollection(collName);
    }
    /**
     *
     * @Title findById
     * @Description 通过id查询
     * @param collName	表名
     * @param id	主键  mongodb默认主键名称为‘_id’ 无法修改
     * @return
     * {@link }
     * @since 2019-03-08
     */
    public Document findById(String collName, String id) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return template.findById(coll, id);
    }
    /**
     *
     * @Title find
     * @Description 条件查询
     * @param collName	表名
     * @param filter	条件
     * @return
     * {@link }
     * @since 2019-03-08
     */
    public MongoCursor<Document> findByFilter(String collName, Bson filter) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return template.findByFilter(coll, filter);
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
     * @since 2019-03-08
     */
    public MongoCursor<Document> findByPage(String collName, Bson filter,Bson sort , int pageNo, int pageSize) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return template.findByPage(coll, filter, sort, pageNo, pageSize);
    }

    /**
     *
     * @Title getCount
     * @Description 查询表中记录条数
     * @param collName	表名
     * @return
     * {@link }
     * @since 2019-03-08
     */
    public int getCount(String collName) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return template.getCount(coll);
    }

    /**
     *
     * @Title deleteById
     * @Description 通过id删除
     * @param collName	表名
     * @param id	mongodb 默认主键 ‘_id’
     * @return 被删除的条数
     * {@link }
     * @since 2019-03-08
     */
    public int deleteById(String collName, String id) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return template.deleteById(coll, id);
    }
    /**
     *
     * @Title deleteByFilter
     * @Description 按条件删除
     * @param collName	表名
     * @param filter	条件
     * @return
     * {@link }
     * @since 2019-03-08
     */
    public int deleteByFilter(String collName, Bson filter){
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return template.deleteByFilter(coll, filter);
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
     * @since 2019-03-08
     */
    public int updateById(String collName, String id, Document newdoc) {
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return template.updateById(coll, id, newdoc);
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
     * @since 2019-03-08
     */
    public int updateByFilter(String collName,  Bson filter, Document newdoc){
        MongoCollection<Document> coll = getDefaultCollection(collName);
        return template.updateByFilter(coll, filter, newdoc);
    }

    /**
     *
     * @Title insertOne
     * @Description 新增一个
     * @param collName	表名称
     * @param document 需新增对象  若没有设置'_id' 则mongodb自动生成
     * {@link }
     * @since 2019-03-08
     */
    public void insertOne(String collName, Document document){
        MongoCollection<Document> coll = getDefaultCollection(collName);
        template.insertOne(coll, document);
    }

    /**
     *
     * @Title insertMany
     * @Description 新增多个  若没有设置'_id' 则mongodb自动生成
     * @param collName	表名
     * @param documents	需新增对象
     * {@link }
     * @since 2019-03-08
     */
    public void insertMany(String collName, List<Document> documents){
        MongoCollection<Document> coll = getDefaultCollection(collName);
        template.insertMany(coll, documents);
    }
}

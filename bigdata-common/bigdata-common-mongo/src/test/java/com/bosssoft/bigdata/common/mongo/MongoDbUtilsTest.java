package com.bosssoft.bigdata.common.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * @Author: Lucky
 * @Description: 数据库连接
 * @Date: Created in 23:43 2019/3/6
 * @Modified By:
 */
public class MongoDbUtilsTest {

//    /**
//     * java链接用户名密码mongodb
//     */
//    @Test
//    public void createCredential() {
//        MongoCredential credential = MongoCredential.createCredential("username", "sgccoa", "123456".toCharArray());
//        MongoClient mongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017), Arrays.asList(credential));
//        System.out.println("Connect to database successfully");
//        DB database = mongoClient.getDB("sgccoa");//获取数据库
//        DBCollection collection = database.getCollection("archiveLog");//集合名
//        BasicDBObject docss = new BasicDBObject();
//    }
//
//    @Test
//    public void mongoClient() {
//        //实现不需要写入返回信息的场景，快速到达写操作
//        MongoClientOptions options = MongoClientOptions.builder()
//                .writeConcern(WriteConcern.UNACKNOWLEDGED)
//                .build();
//        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
//        BasicDBObject docss = new BasicDBObject();
//        DB db = mongoClient.getDB("sgccoa");//获取数据库
//        MongoCollection<Document> collection = (MongoCollection<Document>) db.getCollection("archiveLog");//获取collection（表）
//    }
//
//    @Test
//    public void connectToMongoNoPasswd() throws Exception {
//        //无密码连接mongodb服务
//        connectToMongoNoPasswd("127.0.0.1", 27017, "sgccoa");
//    }
//
//    /**
//     * 无密码连接mongodb服务
//     *
//     * @param endpoint
//     * @param prot
//     * @param dbName
//     * @throws Exception
//     */
//    public void connectToMongoNoPasswd(String endpoint, int prot, String dbName) throws Exception {
//        //连接到mongodb服务
//        MongoClient mongoClient = new MongoClient(endpoint, prot);
//        //连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
//        System.out.println("Connect to database successfully");
//    }
//
//    /**
//     * 设置密码连接mongodb服务
//     *
//     * @param endpoint
//     * @param prot
//     * @param dbName
//     * @param username
//     * @param password
//     * @throws Exception
//     */
//    public void connectToMongoByPasswd(String endpoint, int prot, String dbName, String username, String password) throws Exception {
//        //ServerAddress()两个参数分别为 服务器地址 和 端口
//        ServerAddress serverAddress = new ServerAddress(endpoint, prot);
//        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
//        addrs.add(serverAddress);
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码 　　　　
//        MongoCredential credential = MongoCredential.createScramSha1Credential("username", dbName, "password".toCharArray());
//        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
//        credentials.add(credential);
//        //通过连接认证获取MongoDB连接
//        MongoClient mongoClient = new MongoClient(addrs, credentials);
//        //连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
//        System.out.println("Connect to database successfully");
//    }
}

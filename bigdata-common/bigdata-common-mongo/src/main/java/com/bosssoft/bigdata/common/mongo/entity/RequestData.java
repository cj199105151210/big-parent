package com.bosssoft.bigdata.common.mongo.entity;

import java.util.Date;

import lombok.Data;
import org.bson.Document;
import org.bson.types.ObjectId;


/**
 * @program: bigdata-parent
 * @description:
 * @author: Mr.Lucky
 * @create: 2019-03-08 21:29
 **/
@Data
public class RequestData {
    /**
     * Mongodb主键  id类型不可改
     */
    private ObjectId id;

    private String cardNo;

    private String cardPwd;

    private Date firstDate;

    /**
     * 转换为toDocument 在新增和修改时都需要转换  新增和修改的对象都是Document
     * @param requestData
     * @return
     */
    public Document toDocument(RequestData requestData){
        Document document =  new Document();
        document.put("cardNo", requestData.getCardNo());
        document.put("cardPwd", requestData.getCardPwd());
        document.put("firstDate", requestData.getFirstDate());
        return document;
    }

    /**
     * 转换为实体bean 查询结果为Document  若想用该bean则需要转换
     * @param document
     */
    public void toRequestData(Document document){
        this.setId(document.getObjectId("_id"));
        this.setCardNo(document.getString("cardNo"));
        this.setCardPwd(document.getString("cardPwd"));
        this.setFirstDate(document.getDate("firstDate"));
    }

}

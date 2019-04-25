package com.bosssoft.bigdata.common.mongo.enums;


/**
 * @author Mr.Lucky
 * @version V1.0
 * @Title Seach.java
 * @Description 查询操作符  可参考https://docs.mongodb.org/v3.0/reference/operator/query/
 * @date 2019-03-08 21:25
 */
public enum Seach {
    LT("$lt", "小于-->{ a : { $gt: value } }"),
    LTE("$lte", "小于等于"),
    GT("$gt", "大于"),
    GTE("$gte", "大于等于"),
    ALL("$all", "数组中的元素是否完全匹配-->{ a: { $all: [ 2, 3 ] } }"),
    EXISTS("$exists", "可选:true,false-->{ a : { $exists : true } }"),
    MOD("$mod", "取模:a % 10 == 1 -->{ a : { $mod : [ 10 , 1 ] } }"),
    NE("$ne", "取反:即not equals -->{ x : { $ne : 3 } }"),
    EQ("$eq", "类似equals-->db.inventory.find( { qty: { $eq: 20 } } )"),
    IN("$in", "类似于SQL的IN操作-->{j:{$in: [2,4,6]}}"),
    NIN("$nin", "$in的反操作,即SQL的 NOT IN-->{j:{$nin: [2,4,6]}}"),
    NOR("$nor", "$or的反操作,即不匹配(a或b)-->{ name : 'bob' , $nor : [ { a : 1 } , { b : 2 } ] }"),
    OR("$or", "Or子句,注意$or不能嵌套使用-->{ name : 'bob' , $or : [ { a : 1 } , { b : 2 } ] }"),
    SIZE("$size", "匹配数组长度-->{ a : { $size: 1 } }"),
    TYPE("$type", "匹配子键的数据类型-->{ a : { $type : 2 } }"),
    NOT("$not", "$not执行逻辑NOT运算，选择出不能匹配表达式的文档 ，包括没有指定键的文档-->{ amount: { $not: { $gt: 50 } } }");

    private String operStr;
    private String mean;

    private Seach(String operStr, String mean) {
        this.operStr = operStr;
        this.mean = mean;
    }

    public String getOperStr() {
        return operStr;
    }

    public void setOperStr(String operStr) {
        this.operStr = operStr;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

}

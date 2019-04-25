package com.bosssoft.bigdata.common.mongo.enums;

/**
 * @program: bigdata-parent
 * @description: MongoDb操作符
 * @author: Mr.Lucky
 * @create: 2019-03-08 21:25
 **/
public enum MongoOperation {

    INC("$inc", "增加或插入"),
    SET("$set", "设置或新建"),
    UNSET("$unset", "$set的反操作"),
    PUSH("$push", "元素追加数组末尾"),
    PUSHALL("$pushAll", "$push的批量操作版"),
    ADDTOSET("$addToSet", "同$push,但会过滤重复元素"),
    POP("$pop", "{$pop : {key : 1}}——从数组末尾移除元素 {$pop : {key : -1}}——从数组开头移除元素"),
    PULL("$pull", "从数组中移除所有匹配的元素"),
    PULLALL("$pullALL", "$pull的批量操作版本"),
    RENAME("$rename", "修改指定键的键名"),
    BIT("$bit", "对整形的键值执行位操作‘与’，‘或’等");


    private String operStr;
    private String mean;

    private MongoOperation(String operStr, String mean) {
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

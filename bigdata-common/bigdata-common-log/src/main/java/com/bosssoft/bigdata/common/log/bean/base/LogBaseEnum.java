package com.bosssoft.bigdata.common.log.bean.base;

import java.io.Serializable;

/**
 * @Author: Lucky
 * @Description:日志枚举
 * @Date: Created in 0:52 2019/3/7
 * @Modified By:
 */
public class LogBaseEnum implements Serializable {
    /**
     * 业务对象：基础、文档、意见、附上、流动、可信、配置
     */
    public static enum BusinessObj
    {
        base,//基础
        doc,//文档
        opinion,//意见
        attach,//附上
        flow,//流动
        auth,//可信
        config;//配置
    }

    /**
     * 模块:
     */
    public static enum Modules
    {
        bam,//
        conversion,//转版
        engine,//引擎
        fs,//
        message,
        mobile,//移动
        msgcnt,//消息
        portal,//门户
        schedule,//日程安排
        ums,//
        unview;//未查看
    }

    /**
     * 登录方法：密码，单点
     */
    public static enum LoginMethod
    {
        password,//密码
        sso;//单点
    }

    /**
     * 登录类型：登录，退出
     */
    public static enum LoginType
    {
        login,//登录
        logout;//退出
    }

    /**
     * 操作类型
     */
    public static enum OpeType
    {
        add,//增加
        update,//更新
        delete,//删除
        select,//选择
        upload,//上传
        download,//下载
        publish,//发布
        draft,//草案
        print,//打印
        saveas,//另存为
        open,//打开
        export,//导出
        imports,//导入
        notify,//通知
        handurger,//
        switchs,//开关
        specialsend,//特殊发送
        endope,//取消
        canceldo,//
        done,//
        send,//发送
        sign,//签名
        report,//
        reback,//
        isuue,//
        tackback;//
    }

    /**
     * 操作状态：
     * success-成功、
     * fail-失败
     */
    public static enum OpeStatus
    {
        success,//成功
        fail;//失败
    }

    /**
     * 日志类型：login-登录日志、
     * business-业务日志、
     * manangement-管理日志、
     * exceedpower-越权日志、
     * performanace-性能日志、
     * exception-异常日志
     */
    public static enum LogType
    {
        login,//登录日志
        business,//业务日志
        manangement,//管理日志
        exceedpower,//越权日志
        performanace,//性能日志
        exception;//异常日志
    }

}
package com.bosssoft.bigdata.aggregation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author bosx code aggregation
 * @date 2019-04-04 10:38:01
 */
@Data
@TableName("aggr_lightapp")
@EqualsAndHashCode(callSuper = true)
public class AggrLightapp extends Model<AggrLightapp> {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId
    private Integer lightappId;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片地址
     */
    private String photoUrl;
    /**
     * 链接
     */
    private String url;
    /**
     * 服务类别_数据字典：105
     */
    private String serviceCategory;
    /**
     * 办理地点
     */
    private String processingPlace;
    /**
     * 服务部门
     */
    private String serviceDpt;
    /**
     * 服务方式
     */
    private String serviceMode;
    /**
     * 服务专题
     */
    private String serviceTopice;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 描述
     */
    private String describeMsg;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 用户组
     */
    @TableField(exist = false)
    private List<String> groups;
    /**
     * 用户组
     */
    @TableField(exist = false)
    private List<String> serviceTime;
    /**
     * 图标名
     */
    @TableField(exist = false)
    private String photoName;
}

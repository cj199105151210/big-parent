package com.bosssoft.bigdata.admin.api.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bosssoft.bigdata.admin.api.enums.MonitorFrequency;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Http实例表
 *
 * @author Lucky
 * @date 2019/2/18
 */
@Data
@TableName("http_sequence")
@KeySequence("Http实例表")//类注解
@EqualsAndHashCode(callSuper = true)
public class Sequence extends Model<Sequence> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
   * 主键
   */
    private String guid;
    /**
   * 所属系统
   */
    @TableField("`group`")
    private String group;
    /**
   * 类型（SINGLE, SEQUENCE）
   */
    @EnumValue
    private MonitorType type;//类型：SINGLE-单个接口；SEQUENCE-多个接口
    /**
   * 名称
   */
    @TableField("`name`")
    private String name;
    /**
   * 备注
   */
    private String remark;
    /**
   * job名称
   */
    private String jobname;
    /**
   * 是否启动监控（0-未启动，1-启动）
   */
    private Integer enabled;
    /**
   * 监控频率，默认THIRTY30秒
   */
    @EnumValue
    private MonitorFrequency frequency = MonitorFrequency.THIRTY;//监控频率
    /**
   * 创建时间
   */
    private LocalDateTime createtime;
    /**
   * 存档（0-有效，1-删除）
   */
    private Integer archived;

    /**
     * @Author: Lucky
     * @Description:
     * @Date: Created in 22:52 2019/2/23
     * @Modified By:
     */
    public enum MonitorType {
        //单一 序列
        SINGLE, SEQUENCE
    }
}

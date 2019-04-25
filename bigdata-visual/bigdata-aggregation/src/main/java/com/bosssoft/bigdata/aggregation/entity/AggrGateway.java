package com.bosssoft.bigdata.aggregation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-03-18 10:51:11
 */
@Data
@TableName("aggr_gateway")
@EqualsAndHashCode(callSuper = true)
public class AggrGateway extends Model<AggrGateway> {
private static final long serialVersionUID = 1L;

    /**
   * 自增主键
   */
    @TableId
    private Integer gatewayId;
    /**
     * 应用名称
     */
    private String name;
    /**
     * 应用状态
     */
    private String status;
    /**
   * 编码
   */
    private String code;
    /**
   * 类型
   */
    private String type;
    /**
   * 身份标识_数据字典：106
   */
    private String identity;
    /**
   * 创建者
   */
    private String createUser;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
  
}

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
 * @date 2019-04-02 10:38:58
 */
@Data
@TableName("aggr_subject")
@EqualsAndHashCode(callSuper = true)
public class AggrSubject extends Model<AggrSubject> {
private static final long serialVersionUID = 1L;

    /**
   * 自增主键
   */
    @TableId
    private Integer subjectId;
    /**
   * 编码
   */
    private String subjectCode;
    /**
   * 门户信息表主键
   */
    private Integer gatewayId;
    /**
   * 对应轻应用信息表主键
   */
    private Integer lightappId;
    /**
   * 类型标识_数据字典：100
   */
    private String typeFlag;
    /**
   * 名称
   */
    private String name;
    /**
   * 排序
   */
    private Integer sort;
    /**
   * 状态
   */
    private String status;
    /**
   * 显示性质_数据字典：101
   */
    private String showType;
    /**
   * 操作类型_数据字典：102
   */
    private String operationType;
    /**
   * 对应父结点在当前表的主键
   */
    private Integer parentSubjectId;
    /**
   * 创建者
   */
    private String createUser;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
  
}

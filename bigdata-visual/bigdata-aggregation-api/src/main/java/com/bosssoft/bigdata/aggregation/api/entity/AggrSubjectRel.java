package com.bosssoft.bigdata.aggregation.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-03-11 17:38:24
 */
@Data
@TableName("aggr_subject_rel")
@EqualsAndHashCode(callSuper = true)
public class AggrSubjectRel extends Model<AggrSubjectRel> {
private static final long serialVersionUID = 1L;

    /**
   * 自增主键
   */
    @TableId
    private Integer subjectRelId;
    /**
   * 对应栏目表主键
   */
    private Integer subjectId;
    /**
   * 用户ID
   */
    private String userId;
    /**
   * 显示状态_数据字典：103
   */
    private String showStatus;
    /**
   * 排序
   */
    private Integer sort;
    /**
   * 创建者
   */
    private String createUser;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
  
}

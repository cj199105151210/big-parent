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
 * @date 2019-03-15 16:30:52
 */
@Data
@TableName("aggr_hyperlink")
@EqualsAndHashCode(callSuper = true)
public class AggrHyperlink extends Model<AggrHyperlink> {
private static final long serialVersionUID = 1L;

    /**
   * 自增主键
   */
    @TableId
    private Integer hyperlinkId;
    /**
   * 对应栏目表主键
   */
    private Integer subjectId;
    /**
   * 链接
   */
    private String url;
    /**
   * 描述
   */
    private String describeMsg;
    /**
   * 打开方式_数据字典：104
   */
    private String openMode;
    /**
   * 创建者
   */
    private String createUser;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
  
}

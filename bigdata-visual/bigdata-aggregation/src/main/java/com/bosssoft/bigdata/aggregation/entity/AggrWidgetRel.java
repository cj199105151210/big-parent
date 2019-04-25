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
 * @date 2019-04-04 10:37:54
 */
@Data
@TableName("aggr_widget_rel")
@EqualsAndHashCode(callSuper = true)
public class AggrWidgetRel extends Model<AggrWidgetRel> {
private static final long serialVersionUID = 1L;

    /**
   * 自增主键
   */
    @TableId
    private Integer widgetRelId;
    /**
   * 对应栏目表主键
   */
    private Integer subjectId;
    /**
   * 控件类型_0：非轻应用控件，1：轻应用控件
   */
    private String type;
    /**
   * 组名
   */
    private String groupName;
    /**
   * 用户组ID
   */
    private String groupId;
    /**
   * 创建者
   */
    private String createUser;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
  
}

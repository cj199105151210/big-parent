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
 * @date 2019-03-14 14:45:47
 */
@Data
@TableName("aggr_slideshow")
@EqualsAndHashCode(callSuper = true)
public class AggrSlideshow extends Model<AggrSlideshow> {
private static final long serialVersionUID = 1L;

    /**
   * 自增主键
   */
    @TableId
    private Integer slideshowId;
    /**
   * 对应栏目表主键
   */
    private Integer subjectId;
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
   * 排序
   */
    private Integer sort;
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

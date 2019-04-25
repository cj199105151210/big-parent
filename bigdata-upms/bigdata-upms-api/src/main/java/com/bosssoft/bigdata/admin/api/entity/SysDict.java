package com.bosssoft.bigdata.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author lucky
 * @since 2017-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDict extends Model<SysDict> {
private static final long serialVersionUID = 1L;

    /**
   * 编号
   */
    @TableId
    private Integer id;
    /**
   * 类型
   */
    private String type;
    /**
   * 描述
   */
    private String description;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;
    /**
   * 备注信息
   */
    private String remarks;
    /**
   * 删除标记
   */
	@TableLogic
    private String delFlag;
    /**
   * 所属租户
   */
    private Integer tenantId;
  
}

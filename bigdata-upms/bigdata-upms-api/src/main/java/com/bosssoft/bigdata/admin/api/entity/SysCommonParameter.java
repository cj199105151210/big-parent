package com.bosssoft.bigdata.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Author: Lucky
 * @Description: 公共参数配置表
 * @Date: 2019-03-29 16:55:25
 * @Modified By:
 */
@Data
@TableName("sys_common_parameter")
@EqualsAndHashCode(callSuper = true)
public class SysCommonParameter extends Model<SysCommonParameter> {
private static final long serialVersionUID = 1L;

    /**
   * 系统id
   */
    @TableId
    private Long publicId;
    /**
   * 公共参数名称
   */
    private String publicName;
    /**
   * 公共参数地址值
   */
    private String publicKey;
    /**
   * 备注信息
   */
    private String publicValue;
    /**
   * 状态（1有效；2无效；）
   */
    private String status;
    /**
   * 删除状态（0：正常 1：已删除）
   */
	@TableLogic
    private String deleteFlag;
    /**
   * 公共参数编码
   */
    private String validateCode;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
    /**
   * 模块类型
   */
    private String publicType;
  
}

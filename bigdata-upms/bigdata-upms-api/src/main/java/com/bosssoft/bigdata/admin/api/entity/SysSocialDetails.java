package com.bosssoft.bigdata.admin.api.entity;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统社交登录账号表
 *
 * @author lucky
 * @date 2018-08-16 21:30:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysSocialDetails extends Model<SysSocialDetails> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主鍵
	 */
	@TableId
	private Integer id;
	/**
	 * 类型
	 */
	@NotBlank(message = "类型不能为空")
	private String type;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * appid
	 */
	@NotBlank(message = "账号不能为空")
	private String appId;
	/**
	 * app_secret
	 */
	@NotBlank(message = "密钥不能为空")
	private String appSecret;
	/**
	 * 回调地址
	 */
	private String redirectUrl;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;

	/**
	 * 租户ID
	 */
	private Integer tenantId;

}

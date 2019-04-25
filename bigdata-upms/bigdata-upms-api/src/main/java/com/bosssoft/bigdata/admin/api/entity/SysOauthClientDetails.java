package com.bosssoft.bigdata.admin.api.entity;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 客户端信息
 * </p>
 *
 * @author lucky
 * @since 2018-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOauthClientDetails extends Model<SysOauthClientDetails> {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端ID
	 */
	@NotBlank(message = "client_id 不能为空")
	@TableId(value = "client_id", type = IdType.INPUT)
	private String clientId;

	/**
	 * 门户名称
	 */
	private String portalName;

	/**
	 * 客户端密钥
	 */
	@NotBlank(message = "client_secret 不能为空")
	private String clientSecret;

	/**
	 * 资源ID,资源编号：
	 */
	private String resourceIds;

	/**
	 * 作用域,授权范围：read,server,all
	 */
	@NotBlank(message = "scope 不能为空")
	private String scope;

	/**
	 * 授权方式（A,B,C），例如：password,refresh_token,authorization_code
	 */
	private String authorizedGrantTypes;

	/**
	 * 回调地址，例如：http://example.com
	 */
	private String webServerRedirectUri;

	/**
	 * 权限，例如：
	 */
	private String authorities;

	/**
	 * 请求令牌有效时间(秒数)
	 */
	private Integer accessTokenValidity;

	/**
	 * 刷新令牌有效时间(秒数)
	 */
	private Integer refreshTokenValidity;

	/**
	 * 扩展信息
	 */
	private String additionalInformation;

	/**
	 * 是否自动放行
	 */
	private String autoapprove;

	@Override
	protected Serializable pkVal() {
		return this.clientId;
	}

}

package com.bosssoft.bigdata.common.security.model;

import lombok.Data;

/**
 * @author: Lucky
 * @date: 2019/03/27
 * <p>
 */
@Data
public class OauthTokenRequestBody {
	private String username;
	private String password;
	private String scope;
	private String grant_type;


}

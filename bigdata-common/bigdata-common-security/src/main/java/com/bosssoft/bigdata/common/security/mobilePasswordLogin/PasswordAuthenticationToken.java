package com.bosssoft.bigdata.common.security.mobilePasswordLogin;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-18 18:23
 */
public class PasswordAuthenticationToken extends AbstractAuthenticationToken {

    // 用户信息全部放在这里面，如用户名，手机号，密码等
    private final Object principal;
    //这里保存的证书信息，如密码，验证码等
    private Object credentials;

    //构造未认证之前用户信息

    PasswordAuthenticationToken(Object principal, Object credentials){
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }

    //构造已认证用户信息
    PasswordAuthenticationToken(Object principal,
                               Object credentials,
                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }


    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }
}

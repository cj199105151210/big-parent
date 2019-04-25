package com.bosssoft.bigdata.common.security.mobilePasswordLogin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fanghuaizheng
 * @Description: 密码验证拦截器
 * @date 2019-03-18 18:16
 */
public class PasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private boolean postOnly = true;

    // 手机号参数变量
    private String usernameParam = "username";
    private String passwordParam = "password";

    PasswordAuthenticationFilter(){
        super(new AntPathRequestMatcher("/mobile/password", "POST"));
    }


    /**
     * 添加未认证用户认证信息，然后在provider里面进行正式认证
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        if (postOnly && !httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String username = obtainUsername(httpServletRequest);

        String password = obtainPassword(httpServletRequest);

        PasswordAuthenticationToken authRequest = new PasswordAuthenticationToken(username, password);

        setDetails(httpServletRequest, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);




    }


    public void setUsernameParam(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParam = usernameParameter;
    }

    public final String getUsernameParam() {
        return usernameParam;
    }


    private void setDetails(HttpServletRequest request, PasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    /**
     * 获取手机号
     */
    private String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParam);
    }

    private String obtainPassword(HttpServletRequest request){
        return request.getParameter(passwordParam);
    }
}

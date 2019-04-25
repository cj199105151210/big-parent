package com.bosssoft.bigdata.common.security.mobilePasswordLogin;

import com.bosssoft.bigdata.common.security.component.ResourceAuthExceptionEntryPoint;
import com.bosssoft.bigdata.common.security.service.BosxUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-18 18:44
 */
@Getter
@Setter
@Component
public class PasswordAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private AuthenticationFailureHandler authenticationFailureHandler;

//    private AuthenticationSuccessHandler mobileLoginSuccessHandler;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AuthenticationEventPublisher defaultAuthenticationEventPublisher;


    @Autowired
    BosxUserDetailsService bosxUserDetailsService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void configure(HttpSecurity http) throws Exception {
       PasswordAuthenticationFilter filter = new PasswordAuthenticationFilter();
       filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
       filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
       filter.setAuthenticationFailureHandler(authenticationFailureHandler);

//       filter.setAuthenticationFailureHandler(authenticationFailureHandler);

       PasswordAuthenticationProvider provider = new PasswordAuthenticationProvider();

       provider.setUserDetailsService(bosxUserDetailsService);

       provider.setRedisTemplate(redisTemplate);


       http.authenticationProvider(provider)
               .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);


    }
}

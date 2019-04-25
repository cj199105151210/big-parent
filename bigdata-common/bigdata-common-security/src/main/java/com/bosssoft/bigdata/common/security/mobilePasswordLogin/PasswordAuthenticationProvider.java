package com.bosssoft.bigdata.common.security.mobilePasswordLogin;

import com.bosssoft.bigdata.common.core.constant.enums.LoginErrorEnum;
import com.bosssoft.bigdata.common.security.component.BosxPreAuthenticationChecks;
import com.bosssoft.bigdata.common.security.service.BosxUserDetailsService;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author fanghuaizheng
 * @Description:用户认证类
 * @date 2019-03-18 18:31
 */
@Data
public class PasswordAuthenticationProvider implements AuthenticationProvider {


    private BosxUserDetailsService userDetailsService;


    private RedisTemplate redisTemplate;

    private UserDetailsChecker detailsChecker = new BosxPreAuthenticationChecks();

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();


    /**
     * 在这里认证用户信息
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PasswordAuthenticationToken authenticationToken = (PasswordAuthenticationToken) authentication;

        String username = (String) authenticationToken.getPrincipal();

        String password = (String) authenticationToken.getCredentials();

        UserDetails user = userDetailsService.loadUserBySocial(username);

        if (user == null) {
            throw new InternalAuthenticationServiceException(LoginErrorEnum.NO_USER.getDesc());
        }
        if (!passwordEncoder.matches(password,user.getPassword())){
                throw  new AuthenticationServiceException(LoginErrorEnum.PASSWORD_ERROR.getDesc());
        }




        detailsChecker.check(user);

        PasswordAuthenticationToken authenticationResult = new PasswordAuthenticationToken(user, null, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        //存入缓存
        redisTemplate.opsForValue().set("user_details-1::"+username,user);

        return authenticationResult;


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}

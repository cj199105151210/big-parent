package com.bosssoft.bigdata.auth.handler;

import com.bosssoft.bigdata.common.core.constant.enums.LoginErrorEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-20 17:46
 */
@Component
public class BosxSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            String errStr = LoginErrorEnum.USER_OR_PASSWORD.getDesc();
            if(StringUtils.equalsIgnoreCase(exception.getMessage(),LoginErrorEnum.USER_LOCKED.getDesc())){
                errStr = LoginErrorEnum.USER_LOCKED.getDesc();
            }
            response.getWriter().write(objectMapper.writeValueAsString(
                    new ResponseEntity<>(errStr,HttpStatus.BAD_REQUEST)));

    }
}

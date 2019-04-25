package com.bosssoft.bigdata.common.data.dictionary;

import com.bosssoft.bigdata.common.data.dictionary.CodeToDataComponent;
import com.bosssoft.bigdata.common.data.annotation.CodeToData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fanghuaizheng
 * @Description:自定义拦截器
 * @date 2019-04-10 11:21
 */
@Aspect
@Component
@Slf4j
public class MyInterceptor {

    @Autowired
    CodeToDataComponent codeToDataComponent;

    @Around("@annotation(codeToData)")
    public Object around( ProceedingJoinPoint point,CodeToData codeToData)throws Throwable{


        Object obj = point.proceed();
        codeToDataComponent.transData(obj);

        return obj;

    }

}

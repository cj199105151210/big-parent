package com.bosssoft.bigdata.common.swagger.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.bosssoft.bigdata.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author lucky
 * @date 2018/7/21
 * 开启bosx swagger
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableBosxSwagger2 {
}

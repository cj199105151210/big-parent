package com.bosssoft.bigdata.common.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.bosssoft.bigdata.common.security.component.BosxResourceServerAutoConfiguration;
import com.bosssoft.bigdata.common.security.component.BosxSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author lucky
 * @date 2019/2/23
 * <p>
 * 资源服务注解
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({BosxResourceServerAutoConfiguration.class, BosxSecurityBeanDefinitionRegistrar.class})
public @interface EnableBosxResourceServer {

}

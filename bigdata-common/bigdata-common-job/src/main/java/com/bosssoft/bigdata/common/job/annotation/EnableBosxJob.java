package com.bosssoft.bigdata.common.job.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.bosssoft.bigdata.common.job.ElasticJobAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author lucky
 * @date 2019/2/19
 * 开启bosx job
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ElasticJobAutoConfiguration.class})
public @interface EnableBosxJob {
}

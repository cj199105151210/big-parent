package com.bosssoft.bigdata.admin.config;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.bosssoft.bigdata.admin.service.SysRouteConfService;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.gateway.support.DynamicRouteInitEvent;
import com.bosssoft.bigdata.common.gateway.vo.RouteDefinitionVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Async;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lucky
 * @date 2019/4/7
 * <p>
 * 容器启动后保存配置文件里面的路由信息到Redis
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicRouteInitRunner {
    private final RedisTemplate redisTemplate;
    private final SysRouteConfService routeConfService;

    @Async
    @Order
    @EventListener({WebServerInitializedEvent.class, DynamicRouteInitEvent.class})
    public R initRoute() {
        Boolean result = redisTemplate.delete(CommonConstants.ROUTE_KEY);
        log.info("初始化网关路由 {} ", result);
        //列信息
        List<RouteDefinitionVo> routeDefinitionVoArrayList = new ArrayList<>();
        routeConfService.routes().forEach(route -> {
            RouteDefinitionVo routeDefinitionVo = new RouteDefinitionVo();
            routeDefinitionVo.setRouteName(route.getRouteName());
            routeDefinitionVo.setId(route.getRouteId());
            routeDefinitionVo.setUri(URI.create(route.getUri()));
            routeDefinitionVo.setOrder(route.getOrder());

            JSONArray filterObj = JSONUtil.parseArray(route.getFilters());
            routeDefinitionVo.setFilters(filterObj.toList(FilterDefinition.class));
            JSONArray predicateObj = JSONUtil.parseArray(route.getPredicates());
            routeDefinitionVo.setPredicates(predicateObj.toList(PredicateDefinition.class));

            log.info("加载路由ID：{},{}", route.getRouteId(), routeDefinitionVo);
            routeDefinitionVoArrayList.add(routeDefinitionVo);
            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
            redisTemplate.opsForHash().put(CommonConstants.ROUTE_KEY, route.getRouteId(), routeDefinitionVo);
        });
        log.debug("初始化网关路由结束 ");

        return new R<>(routeDefinitionVoArrayList);
    }

    /**
     * 配置文件设置为空redis 加载的为准
     *
     * @return
     */
    @Bean
    public PropertiesRouteDefinitionLocator propertiesRouteDefinitionLocator() {
        return new PropertiesRouteDefinitionLocator(new GatewayProperties());
    }
}

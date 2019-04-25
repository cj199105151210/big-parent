package com.bosssoft.bigdata.admin.controller;

import com.bosssoft.bigdata.admin.config.DynamicRouteInitRunner;
import com.bosssoft.bigdata.admin.service.SysRouteConfService;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucky
 * @date 2019-04-24
 * <p>
 * 说明：由于生产环境特殊，路由添加需要初始化缓存，此添加
 * 缓存管理 针对缓存清理和初始化路由缓存
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/redis")
@Api(value = "redis", tags = "缓存管理模块")
public class RedisController {

    private final RedisTemplate redisTemplate;
    private final SysRouteConfService routeConfService;

    /**
     * 初始化路由缓存
     */
    //TODO 允许接口开放访问,生产改成认证authorization令牌
    @Inner(value = false)
    @PostMapping("/initRoute")
	@ApiOperation("初始化路由缓存")
    public R initRoute() {
        return new DynamicRouteInitRunner(redisTemplate, routeConfService).initRoute();
    }
}

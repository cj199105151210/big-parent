package com.bosssoft.bigdata.aggregation.controller;

import com.bosssoft.bigdata.admin.api.feign.RemoteUserService;
import com.bosssoft.bigdata.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: GuanYZ
 * @Description: 获取保存在运维平台的菜单数据
 * @Date: Created in 2019/3/7.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "获取保存在运维平台的菜单数据")
public class RemoteMenuController {

    private final RemoteUserService remoteUserService;

    /**
     * 获取当前用户保存在运维平台的菜单数据
     * @return
     */
    @GetMapping()
    public R getUserMenu() {
        return new R<>(remoteUserService.getUserMenu());
    }
}

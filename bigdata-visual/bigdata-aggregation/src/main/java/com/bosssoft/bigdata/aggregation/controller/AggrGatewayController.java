package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.aggregation.constant.CodeDetailConstant;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.aggregation.entity.AggrGateway;
import com.bosssoft.bigdata.aggregation.service.AggrGatewayService;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author bosx code aggregation
 * @date 2019-03-18 10:51:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aggrgateway")
@Api(value = "aggrgateway", tags = "Aggr门户信息表")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, SQLException.class})
public class AggrGatewayController {

    private final AggrGatewayService aggrGatewayService;


    /**
     * 分页查询
     *
     * @param page        分页对象
     * @param aggrGateway
     * @return
     */
    @GetMapping("/page")
    public R getAggrGatewayPage(Page page, AggrGateway aggrGateway) {
        QueryWrapper<AggrGateway> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(aggrGateway.getName())) {
            queryWrapper.like("name", aggrGateway.getName());
        }
        if (StringUtils.isNotBlank(aggrGateway.getCode())) {
            queryWrapper.like("code", aggrGateway.getCode());
        }
        if (StringUtils.isNotBlank(aggrGateway.getStatus())) {
            queryWrapper.like("status", aggrGateway.getStatus());
        }
        if (StringUtils.isNotBlank(aggrGateway.getType())) {
            queryWrapper.like("type", aggrGateway.getType());
        }
        return new R<>(aggrGatewayService.page(page, queryWrapper));
    }

    /**
     * 新增
     *
     * @param aggrGateway
     * @return R
     */
    @SysLog("新增")
    @PostMapping
    public R save(@RequestBody AggrGateway aggrGateway) {
        String username = SecurityUtils.getUser().getUsername();
        LocalDateTime nowDate = LocalDateTime.now();
        aggrGateway.setCreateUser(username);
        aggrGateway.setCreateTime(nowDate);
        return new R<>(aggrGatewayService.save(aggrGateway));
    }

    /**
     * 修改
     *
     * @param aggrGateway
     * @return R
     */
    @SysLog("修改")
    @PutMapping
    public R updateById(@RequestBody AggrGateway aggrGateway) {
        return new R<>(aggrGatewayService.updateById(aggrGateway));
    }

    /**
     * 通过id删除
     *
     * @param gatewayId id
     * @return R
     */
    @SysLog("删除")
    @DeleteMapping("/{gatewayId}")
    public R removeById(@PathVariable Integer gatewayId) {
        return new R<>(aggrGatewayService.removeById(gatewayId));
    }


    /**
     * 删除（批量）
     *
     * @param gatewayIds
     * @return
     */
    @PostMapping("/batchDelObj")
    public R batchDelObj(@RequestBody List<String> gatewayIds) {
        aggrGatewayService.removeByIds(gatewayIds);
        return new R<>("msg", "批量删除成功");
    }

    /**
     * 启用
     *
     * @param gatewayIds
     * @return
     */
    @PostMapping("/handleBatchEnable")
    public R handleBatchEnable(@RequestBody List<String> gatewayIds) {
        List<AggrGateway> aggrGateways = new ArrayList<>();
        AggrGateway aggrGateway;
        for (String gatewayId : gatewayIds) {
            aggrGateway = new AggrGateway();
            aggrGateway.setStatus(CodeDetailConstant.STATUS_ENABLE);
            aggrGateway.setGatewayId(Integer.valueOf(gatewayId));
            aggrGateways.add(aggrGateway);
        }
        aggrGatewayService.updateBatchById(aggrGateways);
        return new R<>("msg", "批量启用成功");
    }

    /**
     * 禁用
     *
     * @param gatewayIds
     * @return
     */
    @PostMapping("/handleBatchDisable")
    public R handleBatchDisable(@RequestBody List<String> gatewayIds) {
        List<AggrGateway> aggrGateways = new ArrayList<>();
        AggrGateway aggrGateway;
        for (String gatewayId : gatewayIds) {
            aggrGateway = new AggrGateway();
            aggrGateway.setStatus(CodeDetailConstant.STATUS_DISABLED);
            aggrGateway.setGatewayId(Integer.valueOf(gatewayId));
            aggrGateways.add(aggrGateway);
        }
        aggrGatewayService.updateBatchById(aggrGateways);
        return new R<>("msg", "批量禁用成功");
    }

    /**
     * 校验门户名称是否重复
     *
     * @param name
     * @return
     */
    @GetMapping("/checkName")
    public Boolean checkName(@RequestParam String name, Integer gatewayId) {
        return aggrGatewayService.checkName(name, gatewayId);
    }

    /**
     * 查询门户编码是否重复
     *
     * @param code
     * @return
     */
    @GetMapping("/checkCode")
    public Boolean checkCode(@RequestParam String code, Integer gatewayId) {
        return aggrGatewayService.checkCode(code, gatewayId);
    }
}

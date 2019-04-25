package com.bosssoft.bigdata.aggregation.controller;


import java.util.List;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.security.annotation.Inner;
import com.bosssoft.bigdata.aggregation.api.entity.MobileLog;
import com.bosssoft.bigdata.aggregation.api.vo.PreMobileLogVo;
import com.bosssoft.bigdata.aggregation.service.MobileLogService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 移动端日志表前端控制器(Demo)
 * </p>
 *
 * @author LleytonJ
 * @since 2019-03-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
@Api(value = "mobile", tags = "移动端日志管理模块")
@Slf4j
public class MobileLogController {
    private final MobileLogService mobileLogService;

    //输入地址：http://localhost:2999/aggregation/mobile/lucky
    @GetMapping("/{id}")
    public String demo(@PathVariable("id") String id) {
        if (id.equals("lucky")) {
            log.info("移动端服务提供方 :" + id + "此人移动工牌正常，有效证件！");
            return "移动端服务提供方 :" + id + "此人移动工牌正常，有效证件！";
        } else {
            log.info("移动端服务提供方 :" + id + "此人移动工牌审核，暂不允许！");
            return "移动端服务提供方 :" + id + "此人移动工牌审核，暂不允许！";
        }

    }

    /**
     * 简单分页查询
     *
     * @param page      分页对象
     * @param mobileLog 系统日志
     * @return
     */
    @GetMapping("/page")
    public R getMobileLogPage(Page page, MobileLog mobileLog) {
        return new R<>(mobileLogService.page(page, Wrappers.query(mobileLog)));
    }

    /**
     * 删除日志
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('mobile_log_del')")
    public R removeById(@PathVariable Long id) {
        return new R<>(mobileLogService.removeById(id));
    }

    /**
     * 插入日志
     *
     * @param sysLog 日志实体
     * @return success/false
     */
    @Inner
    @PostMapping("/save")
    public R save(@Valid @RequestBody MobileLog mobileLog) {
        return new R<>(mobileLogService.save(mobileLog));
    }

    /**
     * 批量插入前端异常日志
     *
     * @param preMobileLogVoList 日志实体
     * @return success/false
     */
    @PostMapping("/logs")
    public R saveBatchMobileLogs(@RequestBody List<PreMobileLogVo> preMobileLogVoList) {
        return new R<>(mobileLogService.saveBatchMobileLogs(preMobileLogVoList));
    }
}

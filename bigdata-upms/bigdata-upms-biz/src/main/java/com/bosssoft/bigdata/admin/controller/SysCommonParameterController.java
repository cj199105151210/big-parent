package com.bosssoft.bigdata.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.admin.api.entity.SysCommonParameter;
import com.bosssoft.bigdata.admin.service.SysCommonParameterService;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;



/**
 * 公共参数配置表
 *
 * @author Lucky
 * @date 2019-04-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/parameter")
@Api(value = "parameter", tags = "公共参数配置模块")
public class SysCommonParameterController {

  private final  SysCommonParameterService sysCommonParameterService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param sysCommonParameter 公共参数配置表
   * @return
   */
  @GetMapping("/page")
  public R getSysCommonParameterPage(Page page, SysCommonParameter sysCommonParameter) {
    return  new R<>(sysCommonParameterService.page(page,Wrappers.query(sysCommonParameter)));
  }


  /**
   * 通过id查询公共参数配置表
   * @param publicId id
   * @return R
   */
  @GetMapping("/{publicId}")
  public R getById(@PathVariable("publicId") Long publicId){
    return new R<>(sysCommonParameterService.getById(publicId));
  }

  /**
   * 新增公共参数配置表
   * @param sysCommonParameter 公共参数配置表
   * @return R
   */
  @SysLog("新增公共参数配置表")
  @PostMapping
  public R save(@RequestBody SysCommonParameter sysCommonParameter){
    return new R<>(sysCommonParameterService.save(sysCommonParameter));
  }

  /**
   * 修改公共参数配置表
   * @param sysCommonParameter 公共参数配置表
   * @return R
   */
  @SysLog("修改公共参数配置表")
  @PutMapping
  public R updateById(@RequestBody SysCommonParameter sysCommonParameter){
    return new R<>(sysCommonParameterService.updateById(sysCommonParameter));
  }

  /**
   * 通过id删除公共参数配置表
   * @param publicId id
   * @return R
   */
  @SysLog("删除公共参数配置表")
  @DeleteMapping("/{publicId}")
  public R removeById(@PathVariable Long publicId){
    return new R<>(sysCommonParameterService.removeById(publicId));
  }

}

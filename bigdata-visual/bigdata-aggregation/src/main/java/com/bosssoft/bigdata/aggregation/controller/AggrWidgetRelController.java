package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.aggregation.entity.AggrWidgetRel;
import com.bosssoft.bigdata.aggregation.service.AggrWidgetRelService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-04-04 10:37:54
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aggrwidgetrel")
@Api(value = "aggrsubjectrel", tags = "Aggr控件用户组关系表")
public class AggrWidgetRelController {

  private final  AggrWidgetRelService aggrWidgetRelService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param aggrWidgetRel 
   * @return
   */
  @GetMapping("/page")
  public R getAggrWidgetRelPage(Page page, AggrWidgetRel aggrWidgetRel) {
    return  new R<>(aggrWidgetRelService.page(page,Wrappers.query(aggrWidgetRel)));
  }


  /**
   * 通过id查询
   * @param widgetRelId id
   * @return R
   */
  @GetMapping("/{widgetRelId}")
  public R getById(@PathVariable("widgetRelId") Integer widgetRelId){
    return new R<>(aggrWidgetRelService.getById(widgetRelId));
  }

  /**
   * 新增
   * @param aggrWidgetRel 
   * @return R
   */
  @SysLog("新增")
  @PostMapping
  public R save(@RequestBody AggrWidgetRel aggrWidgetRel){
    return new R<>(aggrWidgetRelService.save(aggrWidgetRel));
  }

  /**
   * 修改
   * @param aggrWidgetRel 
   * @return R
   */
  @SysLog("修改")
  @PutMapping
  public R updateById(@RequestBody AggrWidgetRel aggrWidgetRel){
    return new R<>(aggrWidgetRelService.updateById(aggrWidgetRel));
  }

  /**
   * 通过id删除
   * @param widgetRelId id
   * @return R
   */
  @SysLog("删除")
  @DeleteMapping("/{widgetRelId}")
  public R removeById(@PathVariable Integer widgetRelId){
    return new R<>(aggrWidgetRelService.removeById(widgetRelId));
  }

}

package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.aggregation.entity.AggrData;
import com.bosssoft.bigdata.aggregation.service.AggrDataService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-03-15 16:30:44
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aggrdata")
@Api(value = "aggrdata", tags = "Aggr数据信息表")
public class AggrDataController {

  private final  AggrDataService aggrDataService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param aggrData 
   * @return
   */
  @GetMapping("/page")
  public R getAggrDataPage(Page page, AggrData aggrData) {
    return  new R<>(aggrDataService.page(page,Wrappers.query(aggrData)));
  }


  /**
   * 通过id查询
   * @param dataId id
   * @return R
   */
  @GetMapping("/{dataId}")
  public R getById(@PathVariable("dataId") Integer dataId){
    return new R<>(aggrDataService.getById(dataId));
  }

  /**
   * 新增
   * @param aggrData 
   * @return R
   */
  @SysLog("新增")
  @PostMapping
  public R save(@RequestBody AggrData aggrData){
    return new R<>(aggrDataService.save(aggrData));
  }

  /**
   * 修改
   * @param aggrData 
   * @return R
   */
  @SysLog("修改")
  @PutMapping
  public R updateById(@RequestBody AggrData aggrData){
    return new R<>(aggrDataService.updateById(aggrData));
  }

  /**
   * 通过id删除
   * @param dataId id
   * @return R
   */
  @SysLog("删除")
  @DeleteMapping("/{dataId}")
  public R removeById(@PathVariable Integer dataId){
    return new R<>(aggrDataService.removeById(dataId));
  }

}

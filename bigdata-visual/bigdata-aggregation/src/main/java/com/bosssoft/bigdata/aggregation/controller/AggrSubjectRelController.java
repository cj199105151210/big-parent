package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.aggregation.entity.AggrSubjectRel;
import com.bosssoft.bigdata.aggregation.service.AggrSubjectRelService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-03-29 16:23:19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aggrsubjectrel")
@Api(value = "aggrsubjectrel", tags = "Aggr用户自定义栏目表")
public class AggrSubjectRelController {

  private final  AggrSubjectRelService aggrSubjectRelService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param aggrSubjectRel 
   * @return
   */
  @GetMapping("/page")
  public R getAggrSubjectRelPage(Page page, AggrSubjectRel aggrSubjectRel) {
    return  new R<>(aggrSubjectRelService.page(page,Wrappers.query(aggrSubjectRel)));
  }


  /**
   * 通过id查询
   * @param subjectRelId id
   * @return R
   */
  @GetMapping("/{subjectRelId}")
  public R getById(@PathVariable("subjectRelId") Integer subjectRelId){
    return new R<>(aggrSubjectRelService.getById(subjectRelId));
  }

  /**
   * 新增
   * @param aggrSubjectRel 
   * @return R
   */
  @SysLog("新增")
  @PostMapping
  public R save(@RequestBody AggrSubjectRel aggrSubjectRel){
    return new R<>(aggrSubjectRelService.save(aggrSubjectRel));
  }

  /**
   * 修改
   * @param aggrSubjectRel 
   * @return R
   */
  @SysLog("修改")
  @PutMapping
  public R updateById(@RequestBody AggrSubjectRel aggrSubjectRel){
    return new R<>(aggrSubjectRelService.updateById(aggrSubjectRel));
  }

  /**
   * 通过id删除
   * @param subjectRelId id
   * @return R
   */
  @SysLog("删除")
  @DeleteMapping("/{subjectRelId}")
  public R removeById(@PathVariable Integer subjectRelId){
    return new R<>(aggrSubjectRelService.removeById(subjectRelId));
  }

}

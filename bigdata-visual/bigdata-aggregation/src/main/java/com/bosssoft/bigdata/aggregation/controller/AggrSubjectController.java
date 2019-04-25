package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.aggregation.entity.AggrSubject;
import com.bosssoft.bigdata.aggregation.service.AggrSubjectService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-04-02 10:38:58
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aggrsubject")
@Api(value = "aggrsubject", tags = "Aggr栏目表")
public class AggrSubjectController {

  private final  AggrSubjectService aggrSubjectService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param aggrSubject 
   * @return
   */
  @GetMapping("/page")
  public R getAggrSubjectPage(Page page, AggrSubject aggrSubject) {
    return  new R<>(aggrSubjectService.page(page,Wrappers.query(aggrSubject)));
  }


  /**
   * 通过id查询
   * @param subjectId id
   * @return R
   */
  @GetMapping("/{subjectId}")
  public R getById(@PathVariable("subjectId") Integer subjectId){
    return new R<>(aggrSubjectService.getById(subjectId));
  }

  /**
   * 新增
   * @param aggrSubject 
   * @return R
   */
  @SysLog("新增")
  @PostMapping
  public R save(@RequestBody AggrSubject aggrSubject){
    return new R<>(aggrSubjectService.save(aggrSubject));
  }

  /**
   * 修改
   * @param aggrSubject 
   * @return R
   */
  @SysLog("修改")
  @PutMapping
  public R updateById(@RequestBody AggrSubject aggrSubject){
    return new R<>(aggrSubjectService.updateById(aggrSubject));
  }

  /**
   * 通过id删除
   * @param subjectId id
   * @return R
   */
  @SysLog("删除")
  @DeleteMapping("/{subjectId}")
  public R removeById(@PathVariable Integer subjectId){
    return new R<>(aggrSubjectService.removeById(subjectId));
  }

}

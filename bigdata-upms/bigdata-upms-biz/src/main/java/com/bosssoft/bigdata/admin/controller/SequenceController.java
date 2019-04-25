package com.bosssoft.bigdata.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.admin.api.entity.Sequence;
import com.bosssoft.bigdata.admin.service.SequenceService;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 代码生成
 *
 * @author Lucky
 * @date 2019-04-24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sequence")
@Api(value = "sequence", tags = "代码生成模块")
public class SequenceController {

  private final  SequenceService sequenceService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param sequence 代码生成
   * @return
   */
  @GetMapping("/page")
  public R getSequencePage(Page page, Sequence sequence) {
    return  new R<>(sequenceService.page(page,Wrappers.query(sequence)));
  }


  /**
   * 通过id查询代码生成
   * @param id id
   * @return R
   */
  @GetMapping("/{id}")
  public R getById(@PathVariable("id") Integer id){
    return new R<>(sequenceService.getById(id));
  }

  /**
   * 新增代码生成
   * @param sequence 代码生成
   * @return R
   */
  @SysLog("新增代码生成")
  @PostMapping
  public R save(@RequestBody Sequence sequence){
    return new R<>(sequenceService.save(sequence));
  }

  /**
   * 修改代码生成
   * @param sequence 代码生成
   * @return R
   */
  @SysLog("修改代码生成")
  @PutMapping
  public R updateById(@RequestBody Sequence sequence){
    return new R<>(sequenceService.updateById(sequence));
  }

  /**
   * 通过id删除代码生成
   * @param id id
   * @return R
   */
  @SysLog("删除代码生成")
  @DeleteMapping("/{id}")
  public R removeById(@PathVariable Integer id){
    return new R<>(sequenceService.removeById(id));
  }

}

package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.aggregation.entity.AggrHyperlink;
import com.bosssoft.bigdata.aggregation.service.AggrHyperlinkService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-03-15 16:30:52
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aggrhyperlink")
@Api(value = "aggrhyperlink", tags = "Aggr超链接信息表")
public class AggrHyperlinkController {

  private final  AggrHyperlinkService aggrHyperlinkService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param aggrHyperlink 
   * @return
   */
  @GetMapping("/page")
  public R getAggrHyperlinkPage(Page page, AggrHyperlink aggrHyperlink) {
    return  new R<>(aggrHyperlinkService.page(page,Wrappers.query(aggrHyperlink)));
  }


  /**
   * 通过id查询
   * @param hyperlinkId id
   * @return R
   */
  @GetMapping("/{hyperlinkId}")
  public R getById(@PathVariable("hyperlinkId") Integer hyperlinkId){
    return new R<>(aggrHyperlinkService.getById(hyperlinkId));
  }

  /**
   * 新增
   * @param aggrHyperlink 
   * @return R
   */
  @SysLog("新增")
  @PostMapping
  public R save(@RequestBody AggrHyperlink aggrHyperlink){
    return new R<>(aggrHyperlinkService.save(aggrHyperlink));
  }

  /**
   * 修改
   * @param aggrHyperlink 
   * @return R
   */
  @SysLog("修改")
  @PutMapping
  public R updateById(@RequestBody AggrHyperlink aggrHyperlink){
    return new R<>(aggrHyperlinkService.updateById(aggrHyperlink));
  }

  /**
   * 通过id删除
   * @param hyperlinkId id
   * @return R
   */
  @SysLog("删除")
  @DeleteMapping("/{hyperlinkId}")
  public R removeById(@PathVariable Integer hyperlinkId){
    return new R<>(aggrHyperlinkService.removeById(hyperlinkId));
  }

}

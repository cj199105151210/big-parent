package com.bosssoft.bigdata.aggregation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.aggregation.entity.AggrSlideshow;
import com.bosssoft.bigdata.aggregation.service.AggrSlideshowService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-03-14 14:45:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aggrslideshow")
@Api(value = "aggrslideshow", tags = "Aggr轮播图信息表")
public class AggrSlideshowController {

  private final  AggrSlideshowService aggrSlideshowService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param aggrSlideshow 
   * @return
   */
  @GetMapping("/page")
  public R getAggrSlideshowPage(Page page, AggrSlideshow aggrSlideshow) {
    return  new R<>(aggrSlideshowService.page(page,Wrappers.query(aggrSlideshow)));
  }


  /**
   * 通过id查询
   * @param slideshowId id
   * @return R
   */
  @GetMapping("/{slideshowId}")
  public R getById(@PathVariable("slideshowId") Integer slideshowId){
    return new R<>(aggrSlideshowService.getById(slideshowId));
  }

  /**
   * 新增
   * @param aggrSlideshow 
   * @return R
   */
  @SysLog("新增")
  @PostMapping
  public R save(@RequestBody AggrSlideshow aggrSlideshow){
    return new R<>(aggrSlideshowService.save(aggrSlideshow));
  }

  /**
   * 修改
   * @param aggrSlideshow 
   * @return R
   */
  @SysLog("修改")
  @PutMapping
  public R updateById(@RequestBody AggrSlideshow aggrSlideshow){
    return new R<>(aggrSlideshowService.updateById(aggrSlideshow));
  }

  /**
   * 通过id删除
   * @param slideshowId id
   * @return R
   */
  @SysLog("删除")
  @DeleteMapping("/{slideshowId}")
  public R removeById(@PathVariable Integer slideshowId){
    return new R<>(aggrSlideshowService.removeById(slideshowId));
  }

}

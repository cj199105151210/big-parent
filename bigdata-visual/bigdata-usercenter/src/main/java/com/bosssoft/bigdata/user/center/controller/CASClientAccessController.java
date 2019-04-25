package com.bosssoft.bigdata.user.center.controller;

import com.bosssoft.bigdata.common.core.util.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.user.center.service.CASClientAccessService;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredService;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredServiceWithBLOBs;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("CasClient")
public class CASClientAccessController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    CASClientAccessService casClientAccessService;

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/PageSelect")
    public R PageSelect(@Param("page") int page,@Param("rows") int rows){
        PageHelper.startPage(page,rows);
        List<RegexRegisteredService> list = casClientAccessService.selectAll();

        PageInfo<RegexRegisteredService> pageInfo = new PageInfo<>(list);
        return new R(pageInfo);
    }

    /**
     * 编辑修改
     * @param regexRegisteredService
     * @return
     */
    @PostMapping("/update")
    public R updata(RegexRegisteredServiceWithBLOBs regexRegisteredService){
        int code = casClientAccessService.update(regexRegisteredService);
        if(code == 1){
            return new R("修改成功");
        }else{
            return new R(new Exception("修改失败"));
        }
    }

    /**
     * 物理删除
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R delete(int id){
        int code  = casClientAccessService.delete(id);
        if(code == 1){
            return new R("删除成功");
        }else{
            return new R(new Exception("删除失败"));
        }
    }

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @GetMapping("/SelectById")
    public R selectById(int id){
         RegexRegisteredService regexRegisteredService = casClientAccessService.selectById(id);
        return  new R(regexRegisteredService);

    }


    /**
     * 插入
     * @param regexRegisteredService
     * @return
     */
    @PostMapping("/insertRecord")
    public R insertRecord(@RequestBody RegexRegisteredServiceWithBLOBs regexRegisteredService){
        regexRegisteredService.setEvaluationOrder(10);
        regexRegisteredService.setExpressionType("regex");
        int code = casClientAccessService.insertRecord(regexRegisteredService);
        if (code == 1){
            return new R("保存成功!");
        }else{
            return new R(new Exception("保存失败!"));
        }
    }
}

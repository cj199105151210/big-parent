package com.bosssoft.bigdata.user.center.controller;

import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.user.center.service.OauthClientService;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredService;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredServiceWithBLOBs;
import com.bosssoft.bigdata.usercenter.api.entity.SysOauthClientDetails;
import com.github.pagehelper.Page;
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
@RequestMapping("OauthClient")
public class OauthClientAccessController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OauthClientService oauthClientService;

    @GetMapping("/PageSelect")
    public R PageSelect(@Param("page") int page, @Param("rows") int rows){
        PageHelper.startPage(page,rows);
        List<SysOauthClientDetails> list = oauthClientService.selectAll();

        PageInfo<SysOauthClientDetails> pageInfo = new PageInfo<>(list);
        return new R(pageInfo);

    }

    /**
     * 编辑修改
     * @param sysOauthClientDetails
     * @return
     */
    @PostMapping("/update")
    public R updata(SysOauthClientDetails sysOauthClientDetails,String oldClientId){
        int code = oauthClientService.update(sysOauthClientDetails,oldClientId);
        if(code == 1){
            return new R("修改成功");
        }else{
            return new R(new Exception("修改失败"));
        }
    }



    /**
     * 物理删除
     * @param clientId
     * @return
     */
    @GetMapping("/delete")
    public R delete(String clientId){
        int code  = oauthClientService.delete(clientId);
        if(code == 1){
            return new R("删除成功");
        }else{
            return new R(new Exception("删除失败"));
        }
    }

    /**
     * 根据id查询信息
     * @param clientId
     * @return
     */
    @GetMapping("/SelectById")
    public R selectById(String clientId){
     SysOauthClientDetails sysOauthClientDetails = oauthClientService.selectById(clientId);
     return  new R(sysOauthClientDetails);
    }

    /**
     * 新增
     * @param sysOauthClientDetails
     * @return
     */
    @PostMapping("/insertRecord")
    public R insertRecord(@RequestBody SysOauthClientDetails sysOauthClientDetails){
        sysOauthClientDetails.setTenantId(1);
        int code = oauthClientService.insertRecord(sysOauthClientDetails);
        if (code == 1){
            return new R("保存成功!");
        }else{
            return new R(new Exception("保存失败!"));
        }
    }
}

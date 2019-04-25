package com.bosssoft.bigdata.user.center.controller;

import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.user.center.service.UserLogService;
import com.bosssoft.bigdata.usercenter.api.entity.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-14 10:44
 */
@RestController
@RequestMapping("userLog")
public class UserLogController {

   @Autowired
    UserLogService userLogService;


    @RequestMapping("insert")
    public R insert(@RequestBody UserLog userLog){
        UserLog log = userLogService.insertUserLog(userLog);
        return new R<>(log);
    }

   //无条件分页查询
    private R queryAllByPage(int page , int rows){
        PageRequest pageRequest = new PageRequest(page-1,rows);
       Page<UserLog> page1 = userLogService.findAll(pageRequest);
       return new R(page1);
    }
    //用户名分页查询
    private R queryByUserName(int page,int rows , String userName){
        Pageable pageable = new PageRequest(page-1,rows);
        Page<UserLog> page3 = userLogService.findByUserName(userName,pageable);
        return new R(page3);
    }
    //登录方式分页查询
    private R queryByLoginType(int page,int rows ,int type){
        Pageable pageable = new PageRequest(page-1,rows);
        Page<UserLog> page4 = userLogService.findByLoginType(type,pageable);
        return new R(page4);
    }

    /**
     * 关系查询
     * @param page
     * @param rows
     * @param userName
     * @param loginType
     * @return
     */
    @GetMapping("/relationQuery")
    public R relationQuery(int page , int rows , String userName ,String loginType){
        if((userName==null||userName.equals(""))&&(loginType==null||loginType.equals(""))){
            return queryAllByPage(page,rows);
        }else if(userName==null||userName.equals("")){
            int type = Integer.parseInt(loginType);
            return queryByLoginType(page,rows,type);
        }else if((loginType==null||loginType.equals(""))){
            return queryByUserName(page,rows,userName);
        }else {
            int type = Integer.parseInt(loginType);
            Pageable pageable = new PageRequest(page - 1, rows);
            Page<UserLog> page2 = userLogService.findByUserNameAndLoginType(userName, type, pageable);
            return new R(page2);
        }
    }

}

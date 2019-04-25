package com.bosssoft.bigdata.usercenter.api.feign;

import com.bosssoft.bigdata.common.core.constant.ServiceNameConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.usercenter.api.entity.UserLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fanghuaizheng
 * @Description:用户日志API
 * @date 2019-03-14 10:53
 */

@FeignClient(name = ServiceNameConstants.USERCENTER_SERVICE)
public interface RemoteUserLogService {

    /**
     * 用户日志功能添加
     * @param userLog
     * @return
     */
    @PostMapping("userLog/insert")
    R<UserLog> insertUserLog(@RequestBody UserLog userLog);

    /**
     * 根据用户名修改用户首次登陆信息（1是首次登陆，0不是首次登陆）
     * @param userName 用户登录名
     * @return
     */
    @GetMapping("user/updUserFirstLogin")
    R<UserLog> updUserFirstLogin(@RequestParam(value = "userName") String userName);
}

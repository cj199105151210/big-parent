package com.bosssoft.bigdata.auth.handler;

import com.bosssoft.bigdata.usercenter.api.entity.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//import com.bosssoft.bigdata.usercenter.api.feign.RemoteUserLogService;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-27 09:37
 */
@Component
@Slf4j
public class SaveUserLogThread {
    private static final BlockingQueue<UserLog> quene = new LinkedBlockingQueue<>();

//    @Autowired
//    RemoteUserLogService remoteUserLogService;


    public void addData(UserLog userLog) {
        try {
            quene.put(userLog);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*    private void insertUserLog(UserLog record) {
        remoteUserLogService.insertUserLog(record);
    }

    private void updUserFirstLogin(String userName) {
        remoteUserLogService.updUserFirstLogin(userName);
    }*/

    @PostConstruct
    public void initMethod() {
        Thread init = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; !Thread.interrupted(); ) {
                    try {
                        UserLog userLog = quene.take();
                        //insertUserLog(userLog);
                    } catch (Exception e) {
                        log.error("用户登录日志记录发生错误\t{}", e.getCause());
                    }
                }
            }
        });

        init.setDaemon(true);
        init.start();
    }

    @PostConstruct
    public void threadFirstLogin() {
        Thread tread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; !Thread.interrupted(); ) {
                    try {
                        UserLog userLog = quene.take();
                        //updUserFirstLogin(userLog.getUserName());
                    } catch (Exception e) {
                        log.error("修改用户首次登陆信息发生错误\t{}", e.getCause());
                    }
                }
            }
        });
        tread.setDaemon(true);
        tread.start();
    }

}


package com.bosssoft.bigdata.common.data.mybatis.interceptor;

import com.bosssoft.bigdata.common.data.dictionary.TransnationUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author fanghuaizheng
 * @Description:数据查询拦截器
 * @date 2019-03-28 14:34
 */
@Intercepts({
         @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class })
})
@Slf4j
public class DictIntercept implements Interceptor {


//    private static List<SysDict> dicts = null;

    @Setter
    private CacheManager cacheManager;

    /**
     * 缓存的前置
     */
    @Setter
    private String CACHE_PREFIX;

    @Autowired
    TransnationUtils transnationUtils;



    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object proceed = invocation.proceed();

        if (proceed instanceof ArrayList){
            List data = (List) proceed;
            for (Object item: data
                 ) {
                transnationUtils.startTrans(item);
            }
        }else transnationUtils.startTrans(proceed);



        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);

    }

    @Override
    public void setProperties(Properties properties) {

    }

  }

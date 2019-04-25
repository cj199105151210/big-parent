package com.bosssoft.bigdata.common.data.dictionary;

import com.bosssoft.bigdata.admin.api.entity.SysDictItem;
import com.bosssoft.bigdata.common.core.constant.BosxCachePrefixConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.data.annotation.DictClass;
import com.bosssoft.bigdata.common.data.annotation.DictParam;
import com.bosssoft.bigdata.common.data.dictionary.TransnationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fanghuaizheng
 * @Description:字典项key转化为value
 * @date 2019-04-10 11:05
 */
@Component
@Slf4j
public class CodeToDataComponent {

   @Autowired
   TransnationUtils transnationUtils;

    public void transData(Object data)throws Exception{
        if (data==null){
            return;
        }
        if (data instanceof List){
            ((List) data).forEach(x-> {
                try {
                    transnationUtils.startTrans(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }else if (data instanceof Map){
            ((Map) data).forEach((x,y)->{
                try {
                    transnationUtils.startTrans(y);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }else transnationUtils.startTrans(data);
    }




}

package com.bosssoft.bigdata.common.data.dictionary;

import com.bosssoft.bigdata.admin.api.entity.SysDictItem;
import com.bosssoft.bigdata.common.core.constant.BosxCachePrefixConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.data.annotation.DictClass;
import com.bosssoft.bigdata.common.data.annotation.DictParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-04-18 10:17
 */
@Component
@Slf4j
public class TransnationUtils {

    @Autowired
    CacheManager cacheManager;

    private static String CACHE_PREFIX = BosxCachePrefixConstants.DICT;

    public void startTrans(Object trans) throws Exception {
        Class<?> dataClass = trans.getClass();
        DictClass dictClass = dataClass.getAnnotation(DictClass.class);

        if (dictClass!=null){
            List<Field> fields = getAllFieldsList(dataClass);
            if (fields!=null){

                for (Field field:fields){
                    field.setAccessible(true);
                    DictParam dictParam = field.getAnnotation(DictParam.class);
                    if (dictParam==null){
                        continue;
                    }else {
                        String nameField = dictParam.nameFiled();
                        Field transField = dataClass.getDeclaredField(nameField);
                        //如果获取到字典key为空，直接跳过
                        Object o = field.get(trans);
                        if (o==null){
                            continue;
                        }
                        //从缓存里面开始获取字典值
                        List<SysDictItem> dicts = null;
                        if (dicts==null){//从换从里面获取数据
                            if (cacheManager==null){
                                log.info("注入的属性cacheManager {} ",cacheManager);
                                return;
                            }
                            R r = cacheManager.getCache(CACHE_PREFIX).get(dictParam.dictType(), R.class);
                            log.info("通过查询缓存 {}-{} 获取到的值{}", CACHE_PREFIX,dictParam.dictType(),r);
                            if (r==null){
                                return;
                            }
                            dicts = (List<SysDictItem>) r.getData();
                        }
                        if (dicts==null||dicts.size()==0){
                            return ;
                        }
                        for(SysDictItem sysDict : dicts){
                            if (StringUtils.equalsIgnoreCase(sysDict.getValue(),field.get(trans).toString())){
                                transField.setAccessible(true);
                                transField.set(trans,sysDict.getLabel());
                                transField.setAccessible(false);
                            }
                        }
                    }
                    field.setAccessible(false);
                }

            }
        }
    }

    private List<Field> getAllFieldsList(final Class<?> cls) {
        Validate.isTrue(cls != null, "The class must not be null");
        final List<Field> allFields = new ArrayList<Field>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            for (final Field field : declaredFields) {
                allFields.add(field);
            }
            currentClass = currentClass.getSuperclass();
        }
        return allFields;
    }

}

package com.bosssoft.bigdata.user.center.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fanghuaizheng
 * @Description:工具类
 * @date 2019-03-14 10:58
 */
public class CommonUtils {

    public static String getUUID(){


       return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 字符串大写字母转下划线
     * @param param
     * @return
     */
    public static String upperCharToUnderLine(String param) {
        Pattern p=Pattern.compile("[A-Z]");
        if(param==null ||param.equals("")){
            return "";
        }
        StringBuilder builder=new StringBuilder(param);
        Matcher mc=p.matcher(param);
        int i=0;
        while (mc.find()) {
            builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
            i++;
        }

        if('_' == builder.charAt(0)){
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }


    public static Map<String, Object> objectToMap(Object obj) throws Exception {
                if(obj == null)
                         return null;

                 Map<String, Object> map = new HashMap<String, Object>();

                 BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
                 PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                 for (PropertyDescriptor property : propertyDescriptors) {
                         String key = property.getName();
                         if (key.compareToIgnoreCase("class") == 0) {
                                 continue;
                             }
                         Method getter = property.getReadMethod();
                         Object value = getter!=null ? getter.invoke(obj) : null;
                         map.put(key, value);
                     }

                 return map;
    }




}

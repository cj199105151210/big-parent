package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.user.center.utils.CommonUtils;
import com.bosssoft.bigdata.usercenter.api.annotation.OrderType;
import com.bosssoft.bigdata.usercenter.api.annotation.SearchType;
import com.bosssoft.bigdata.usercenter.api.constant.Constant;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: data-centre
 * @description: 基础sql提供工具类
 * @author: fanghuaizheng
 * @create: 2018-10-11 11:17
 */
@Slf4j
public class BaseSqlProviderUtils<T> {

    /**
     * 根据实体类，自动生成where条件语句
     * @param t
     * @return
     * @throws Exception
     */
    public String getWhereSql(T t) throws Exception{

        StringBuilder whereSql = new StringBuilder();

        if (t==null){
            return whereSql.toString();
        }

        Field[] fields = t.getClass().getDeclaredFields();

        if (fields.length==0){
            return whereSql.toString();
        }

        whereSql.append("select * from "+CommonUtils.upperCharToUnderLine(t.getClass().getSimpleName())+" where 1=1");

        List<String> orderList = new ArrayList<>();

        for (int i= 0;i<fields.length;i++){
            Field item = fields[i];
            item.setAccessible(true);
            SearchType searchType = item.getAnnotation(SearchType.class);
            Boolean isLike = false;
            if (searchType!=null){
                String value = searchType.value();
                if (StringUtils.isNotBlank(value)&&(value.toString().indexOf(Constant.LIKE)>-1)){
                    isLike = true;
                }
            }

            //开始验证order
            OrderType orderType = item.getAnnotation(OrderType.class);
            if (orderType!=null){
                String value = orderType.value();
                if (StringUtils.isBlank(value)){
                   orderList.add(CommonUtils.upperCharToUnderLine(item.getName())+" ASC");
                }else orderList.add(CommonUtils.upperCharToUnderLine(item.getName())+" "+value);
            }




            Object value = item.get(t);
            if (value!=null&&StringUtils.isNotBlank(value.toString())){
                String name = item.getName();
                if (isLike){ //进行模糊查询
                    String typeValue = searchType.value();
                    if (StringUtils.equalsIgnoreCase(typeValue,Constant.LEFT_LIKE)){
                        whereSql.append(" and "+ CommonUtils.upperCharToUnderLine(name)+" like '%"+value+"'");

                    }else if (StringUtils.equalsIgnoreCase(typeValue,Constant.RIGHT_LIKE)){
                        whereSql.append(" and "+CommonUtils.upperCharToUnderLine(name)+" like '"+value+"%'");
                    }else {
                        whereSql.append(" and "+CommonUtils.upperCharToUnderLine(name)+" like '%"+value+"%'");
                    }

                }else {
                    whereSql.append(" and "+CommonUtils.upperCharToUnderLine(name)+"='"+value+"'");
                }

            }
            item.setAccessible(false);
        }

        log.info("获取到的条件是:"+whereSql);

        if (orderList.size()!=0){
            whereSql.append(" order by "+StringUtils.join(orderList,","));
        }

        return whereSql.toString();

    }


    public static void main(String[] args) throws Exception {

        UserGroupSqlProvider provider = new UserGroupSqlProvider();
        UserGroup group = new UserGroup();
        group.setCname("123");
        String sql = provider.getWhereSql(group);

        System.out.println(sql);

    }

}

package com.bosssoft.bigdata.https.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.hutool.core.util.IdUtil;
import com.bosssoft.bigdata.common.mongo.entity.RequestData;
import com.bosssoft.bigdata.common.mongo.service.MongoTemplate;
import com.bosssoft.bigdata.common.mongo.util.MongoDbUtils;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Lucky
 * @Description: 第三方请求模块Demo
 * @Date: Created in 21:54 2019/2/28
 * @Modified By:
 */

//输入地址：http://localhost:2999/https/test/lucky
//1.默认放行url,子模块重写时application-dev.yml中的公共配置会被覆盖，所以要把公共配罡中的放行urZ再写一次
//2.bigdata-https-dev.yml里面的 ignore-urls:- /test/**  对应这边
//3.数据库里面sys_route_conf响应添加模块，里面配置需要修改
//4.在修改config配置,需要再次运行模块
@Slf4j
@RestController
@AllArgsConstructor //注解在类上；为类提供一个全参的构造方法
@RequestMapping("/test") //
@Api(value = "test", tags = "第三方请求服务模块")
public class Test {
    private final MongoTemplate template;

    @GetMapping("/{id}")
    public String view(@PathVariable("id") String id) {
        //入库之前需先转换为document
        RequestData data = new RequestData();
        data.setCardNo(IdUtil.simpleUUID());
		data.setCardPwd("第三方请求服务:"+id);
		data.setFirstDate(new Date());
		//入库之前需先转换为document
        Document dc = data.toDocument(data);
        MongoDbUtils.insertOne("student", dc);//入库单条
        //查询当前五分钟之内的数据   firstDate在mongodb中是直接以Date类型存储的   此处注意Filters.and的用法  可以同时包含多个条件

        Date date = new Date();
        date.setMinutes(-5);
        Bson filter = Filters.and(Filters.lte("firstDate", new Date()),Filters.gte("firstDate",date));
        MongoCursor<Document> result = MongoDbUtils.findByFilter("student", filter);//条件查询

        List<RequestData> listData = new ArrayList<RequestData>();
        while (result.hasNext()) {
            Document document=result.next();
            RequestData requestData=new RequestData();
            requestData.toRequestData(document);//将查询结果Document转换为实体bean
            listData.add(requestData);
        }
        System.out.println("★★★Lucky★★★ listData值=" + listData.toArray());
        return  "hello:"+id;
    }

}

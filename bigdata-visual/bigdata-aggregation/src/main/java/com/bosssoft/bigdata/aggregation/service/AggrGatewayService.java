package com.bosssoft.bigdata.aggregation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.bigdata.aggregation.entity.AggrGateway;

/**
 * @author bosx code aggregation
 * @date 2019-03-18 10:51:11
 */
public interface AggrGatewayService extends IService<AggrGateway> {


    /**
     * 查询门户名称是否重复
     *
     * @param name
     * @return
     */
    Boolean checkName(String name, Integer gatewayId);

    /**
     * 查询门户编码是否重复
     *
     * @param code
     * @return
     */
    Boolean checkCode(String code, Integer gatewayId);


}

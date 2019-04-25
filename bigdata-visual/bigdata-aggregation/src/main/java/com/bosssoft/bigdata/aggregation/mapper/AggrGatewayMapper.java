package com.bosssoft.bigdata.aggregation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.bigdata.aggregation.entity.AggrGateway;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author bosx code aggregation
 * @date 2019-03-18 10:51:11
 */
public interface AggrGatewayMapper extends BaseMapper<AggrGateway> {
    /**
     * 查询门户名称是否重复
     * @param name
     * @return
     */
    int checkName(String name);

    /**
     * 查询门户编码是否重复
     * @param code
     * @return
     */
    int checkCode(String code);

    /**
     * 修改时查询门户名称是否重复
     * @param name
     * @return
     */
    List<AggrGateway> updateCheckName(String name);

    /**
     * 修改时查询门户编码是否重复
     * @param code
     * @return
     */
    List<AggrGateway> updateCheckCode(String code);

}

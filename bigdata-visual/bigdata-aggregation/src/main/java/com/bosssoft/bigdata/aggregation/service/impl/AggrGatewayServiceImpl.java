package com.bosssoft.bigdata.aggregation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.aggregation.entity.AggrGateway;
import com.bosssoft.bigdata.aggregation.mapper.AggrGatewayMapper;
import com.bosssoft.bigdata.aggregation.service.AggrGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author bosx code aggregation
 * @date 2019-03-18 10:51:11
 */
@Service
public class AggrGatewayServiceImpl extends ServiceImpl<AggrGatewayMapper, AggrGateway> implements AggrGatewayService {

    @Autowired
    private AggrGatewayMapper aggrGatewayMapper;


    @Override
    public Boolean checkName(String name, Integer gatewayId) {
        QueryWrapper<AggrGateway> aggrGatewayQueryWrapper = new QueryWrapper<>();
        aggrGatewayQueryWrapper.eq("name", name);
        if (gatewayId != null) {
            aggrGatewayQueryWrapper.ne("gateway_id", gatewayId);
        }
        Integer result = aggrGatewayMapper.selectCount(aggrGatewayQueryWrapper);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean checkCode(String code, Integer gatewayId) {
        QueryWrapper<AggrGateway> aggrGatewayQueryWrapper = new QueryWrapper<>();
        aggrGatewayQueryWrapper.eq("code", code);
        if (gatewayId != null) {
            aggrGatewayQueryWrapper.ne("gateway_id", gatewayId);
        }
        Integer result = aggrGatewayMapper.selectCount(aggrGatewayQueryWrapper);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

}

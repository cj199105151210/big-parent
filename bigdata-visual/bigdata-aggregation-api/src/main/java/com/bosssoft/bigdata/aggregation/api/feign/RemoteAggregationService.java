package com.bosssoft.bigdata.aggregation.api.feign;

import com.bosssoft.bigdata.aggregation.api.entity.AggrSubjectRel;
import com.bosssoft.bigdata.aggregation.api.entity.MobileLog;
import com.bosssoft.bigdata.common.core.constant.SecurityConstants;
import com.bosssoft.bigdata.common.core.constant.ServiceNameConstants;
import com.bosssoft.bigdata.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: Bigdata
 * @Description: 聚合平台对外接口
 * @Date: Created in 14:37 2019/3/2
 * @Modified By:
 */
@FeignClient(value = ServiceNameConstants.AGGREGAT_SERVICE)
public interface RemoteAggregationService {

    /**
     * 保存用户自定义栏目信息
     *
     * @param aggrSubjectRelList 用户自定义栏目信息
     * @return 处理结果
     */
    @PostMapping("/subject/subjectRel")
    R subjectRel(@RequestBody List<AggrSubjectRel> aggrSubjectRelList);

    /**
     * 根据登录用户获取页面内容
     *
     * @param userId    登陆用户ID
     * @param gatewayId 门户id
     * @return 页面内容
     */
    @PostMapping("/subject/getGatewayPage")
    R getGatewayPage(@RequestParam String userId, Integer gatewayId);


}

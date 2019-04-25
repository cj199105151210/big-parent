package com.bosssoft.bigdata.admin.api.feign;

import com.bosssoft.bigdata.common.core.constant.ServiceNameConstants;
import com.bosssoft.bigdata.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Author: GuanYZ
 * @Description: 数据字典feign接口
 * @Date: Created in 2019/3/21.
 */
@FeignClient(contextId = "remoteDictService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteDictService {

    /**
     * 根据字典类型获取字典信息
     * @param type
     * @return
     */
    @GetMapping("/dict/type/{type}")
    R getDictByType(@PathVariable("type") String type);
}

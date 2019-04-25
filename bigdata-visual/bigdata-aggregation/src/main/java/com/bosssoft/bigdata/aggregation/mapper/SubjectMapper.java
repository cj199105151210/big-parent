package com.bosssoft.bigdata.aggregation.mapper;

import com.bosssoft.bigdata.aggregation.entity.AggrSubject;
import com.bosssoft.bigdata.aggregation.po.SubjectPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author bosx code aggregation
 * @date 2019-03-15 16:30:44
 */
public interface SubjectMapper {


    List<AggrSubject> getGatewayPage(@Param("userId") String userId, @Param("identity") String identity, @Param("code") String code, @Param("groupIdList") List<String> groupIdList);

    List<SubjectPO> getAddAppByUser(@Param("userId") String userId, @Param("code") String code, @Param("type") String type);



}

package com.bosssoft.bigdata.user.center.mapper.usercenterMapper;

import com.bosssoft.bigdata.user.center.sqlProvider.UserAndOrgSqlProvider;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserAndOrgMapper {
    @Insert({
        "insert into user_and_org (user_id, org_id, ",
        "create_time, update_time, ",
        "create_user,is_directly)",
        "values (#{userId,jdbcType=VARCHAR}, #{orgId,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createUser,jdbcType=VARCHAR}),#{isDirectly,jdbcType=VARCHAR}"
    })
    int insert(UserAndOrg record);

    @InsertProvider(type= UserAndOrgSqlProvider.class, method="insertSelective")
    int insertSelective(UserAndOrg record);

    @SelectProvider(type= UserAndOrgSqlProvider.class, method="selectByOrgId")
    List<String> selectByOrgId(String orgId);

    @UpdateProvider(type = UserAndOrgSqlProvider.class,method = "updateDirectly")
    int updateDirectly(@Param("userId") String userId,@Param("orgId")String orgId);

    @UpdateProvider(type = UserAndOrgSqlProvider.class,method = "updateDirectlyByUserId")
    int updateDirectlyByUserId(String userId);

    @Delete({
            "delete from user_and_org",
            "where org_Id = #{orgId,jdbcType=VARCHAR}"
    })
    int deletePeopleByNodeId(String orgId);

    @Delete({
            "delete from user_and_org",
            "where org_Id = #{orgId,jdbcType=VARCHAR}",
            "and user_id = #{userId,jdbcType=VARCHAR}"
    })
    int deleteFromRelation(UserAndOrg userAndOrg);

 	@SelectProvider(type = UserAndOrgSqlProvider.class,method = "selectRecord")
    List<UserAndOrg> selectRecord(@Param("userId") String userId, @Param("orgId") String orgId);

}
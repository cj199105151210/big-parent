package com.bosssoft.bigdata.user.center.mapper.usercenterMapper;

import com.bosssoft.bigdata.user.center.model.UserDepartment;
import com.bosssoft.bigdata.user.center.sqlProvider.FlowPositionSqlProvider;
import com.bosssoft.bigdata.user.center.sqlProvider.OrganizationSqlProvider;
import com.bosssoft.bigdata.user.center.sqlProvider.UserAndFlowSqlProvider;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndFlow;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserAndFlowMapper {
    @Insert({
            "insert into user_and_flow (user_id, org_id, ",
            "create_time, update_time, ",
            "create_user)",
            "values (#{userId,jdbcType=VARCHAR}, #{orgId,jdbcType=VARCHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
            "#{createUser,jdbcType=TIMESTAMP})"
    })
    int insert(UserAndFlow record);

    @InsertProvider(type = UserAndFlowSqlProvider.class, method = "insertSelective")
    int insertSelective(UserAndFlow record);

    @Delete({
            "delete from user_and_flow",
            "where org_Id = #{orgId,jdbcType=VARCHAR}"
    })
    int deletePeopleByNodeId(String orgId);

    @Delete({
            "delete from user_and_flow",
            "where org_Id = #{orgId,jdbcType=VARCHAR}",
            "and user_id = #{userId,jdbcType=VARCHAR}"
    })
    int deleteFromRelation(UserAndFlow userAndFlow);

    @Select({
            "select user_id, org_Id",
            "from user_and_flow",
            "where org_Id = #{orgId,jdbcType=VARCHAR}",
            "and user_id = #{userId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "org_id", property = "orgId", jdbcType = JdbcType.VARCHAR)
    })
    UserAndFlow selectUserRelation(UserAndFlow userAndFlow);

    @SelectProvider(type = FlowPositionSqlProvider.class,method = "selectFlowByUserId")
    List<UserDepartment> selectFlowByUserId(String userId);

    @SelectProvider(type = FlowPositionSqlProvider.class,method = "fuzzSearch")
    List<UserDepartment> fuzzSearch(@Param("userId") String userId, @Param("keyWord") String keyWord);
}
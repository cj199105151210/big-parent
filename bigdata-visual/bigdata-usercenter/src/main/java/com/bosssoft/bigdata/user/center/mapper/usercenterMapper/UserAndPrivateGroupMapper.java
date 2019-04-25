package com.bosssoft.bigdata.user.center.mapper.usercenterMapper;

import com.bosssoft.bigdata.user.center.sqlProvider.UserAndPrivateGroupSqlProvider;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndPrivateGroup;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserAndPrivateGroupMapper {
    @Insert({
            "insert into user_and_private_group (user_id, org_id, ",
            "create_time, update_time, ",
            "create_user)",
            "values (#{userId,jdbcType=VARCHAR}, #{orgId,jdbcType=VARCHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
            "#{createUser,jdbcType=TIMESTAMP})"
    })
    int insert(UserAndPrivateGroup record);

    @InsertProvider(type = UserAndPrivateGroupSqlProvider.class, method = "insertSelective")
    int insertSelective(UserAndPrivateGroup record);

    @Delete({
            "delete from user_and_private_group",
            "where org_Id = #{orgId,jdbcType=VARCHAR}"
    })
    int deletePeopleByNodeId(String orgId);

    @Delete({
            "delete from user_and_private_group",
            "where org_Id = #{orgId,jdbcType=VARCHAR}",
            "and user_id = #{userId,jdbcType=VARCHAR}"
    })
    int deleteFromRelation(UserAndPrivateGroup userAndPrivateGroup);

    @Select({
            "select user_id, org_Id",
            "from user_and_private_group",
            "where org_Id = #{orgId,jdbcType=VARCHAR}",
            "and user_id = #{userId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "org_id", property = "orgId", jdbcType = JdbcType.VARCHAR)
    })
    UserAndPrivateGroup selectUserRelation(UserAndPrivateGroup userAndPrivateGroup);
}
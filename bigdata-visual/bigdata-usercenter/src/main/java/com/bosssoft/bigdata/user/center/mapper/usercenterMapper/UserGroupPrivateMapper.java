package com.bosssoft.bigdata.user.center.mapper.usercenterMapper;

import com.bosssoft.bigdata.user.center.model.Tree;
import com.bosssoft.bigdata.user.center.sqlProvider.UserGroupPrivateSqlProvider;
import com.bosssoft.bigdata.user.center.sqlProvider.UserGroupSqlProvider;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroupPrivate;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserGroupPrivateMapper {
    @Delete({
            "delete from user_group_private",
            "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String guid);

    @Insert({
            "insert into user_group_private (guid, name, ",
            "cname, short_name, ",
            "code, parent_id, create_id",
            "sort_no, is_delete, ",
            "create_time, update_tme, ",
            "create_user, create_user_name, ",
            "extend, description)",
            "values (#{guid,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
            "#{cname,jdbcType=VARCHAR}, #{shortName,jdbcType=VARCHAR}, ",
            "#{code,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, #{createId,jdbcType=VARCHAR},",
            "#{sortNo,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{updateTme,jdbcType=TIMESTAMP}, ",
            "#{createUser,jdbcType=VARCHAR}, #{createUserName,jdbcType=VARCHAR}, ",
            "#{extend,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR})"
    })
    int insert(UserGroupPrivate record);

    @InsertProvider(type = UserGroupPrivateSqlProvider.class, method = "insertSelective")
    int insertSelective(UserGroupPrivate record);

    @Select({
            "select",
            "guid, name, cname, short_name, code, parent_id, create_id, sort_no, is_delete, create_time, ",
            "update_tme, create_user, create_user_name, extend, description",
            "from user_group_private",
            "where guid = #{guid,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "short_name", property = "shortName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "parentId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_id", property = "createId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sort_no", property = "sortNo", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_tme", property = "updateTme", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user", property = "createUser", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_user_name", property = "createUserName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "extend", property = "extend", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    UserGroupPrivate selectByPrimaryKey(String guid);

    @UpdateProvider(type = UserGroupPrivateSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserGroupPrivate record);

    @Update({
            "update user_group_private",
            "set name = #{name,jdbcType=VARCHAR},",
            "cname = #{cname,jdbcType=VARCHAR},",
            "short_name = #{shortName,jdbcType=VARCHAR},",
            "code = #{code,jdbcType=VARCHAR},",
            "parent_id = #{parentId,jdbcType=VARCHAR},",
            "create_id = #{createId,jdbcType=VARCHAR},",
            "sort_no = #{sortNo,jdbcType=INTEGER},",
            "is_delete = #{isDelete,jdbcType=INTEGER},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "update_tme = #{updateTme,jdbcType=TIMESTAMP},",
            "create_user = #{createUser,jdbcType=VARCHAR},",
            "create_user_name = #{createUserName,jdbcType=VARCHAR},",
            "extend = #{extend,jdbcType=VARCHAR},",
            "description = #{description,jdbcType=VARCHAR}",
            "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(UserGroupPrivate record);

    @SelectProvider(type = UserGroupPrivateSqlProvider.class, method = "selectAllUserGroupPrivate")
    @Results({
            @Result(column = "guid", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "pId", jdbcType = JdbcType.VARCHAR)
    })
    List<Tree> selectAllUserGroupPrivate(String userId);

    @SelectProvider(type = UserGroupPrivateSqlProvider.class, method = "selectUserByNodeId")
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "login_name", property = "loginName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "xm", property = "xm", jdbcType = JdbcType.VARCHAR)
    })
    List<UserMessageVo> selectUserByNodeId(String nodeId);

    @SelectProvider(type = UserGroupPrivateSqlProvider.class, method = "selectUserByXM")
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "login_name", property = "loginName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "xm", property = "xm", jdbcType = JdbcType.VARCHAR)
    })
    List<UserMessage> selectUserByXM(String xm);

    @Select({
            "select guid, cname, parent_id",
            "from user_group_private where parent_id = #{nodeId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "guid", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "pId", jdbcType = JdbcType.VARCHAR)
    })
    List<Tree> selectUserGroupPrivateByNodeId(String nodeId);

    @Select({
            "select guid, cname",
            "from user_group_private where cname = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR)
    })
    List<UserGroupPrivate> selectByName(String name);

    @SelectProvider(type = UserGroupPrivateSqlProvider.class, method = "selectUserByNamePhone")
    List<UserMessageVo> selectUserByNamePhone(@Param("queryParam") String queryParam);
}
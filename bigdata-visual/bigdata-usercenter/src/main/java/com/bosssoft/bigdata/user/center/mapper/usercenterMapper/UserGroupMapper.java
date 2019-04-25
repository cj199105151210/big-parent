package com.bosssoft.bigdata.user.center.mapper.usercenterMapper;

import com.bosssoft.bigdata.user.center.model.Tree;
import com.bosssoft.bigdata.user.center.model.UserDepartment;
import com.bosssoft.bigdata.user.center.sqlProvider.UserGroupSqlProvider;
import com.bosssoft.bigdata.user.center.sqlProvider.UserMessageSqlProvider;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroup;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserGroupMapper {
    @Delete({
            "delete from user_group",
            "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String guid);

    @Insert({
            "insert into user_group (guid, cname, ",
            "short_name, code, ",
            "parent_id, sort_no, ",
            "is_delete, create_time, ",
            "update_tme, create_user, ",
            "create_user_name, extend, ",
            "description)",
            "values (#{guid,jdbcType=VARCHAR}, #{cname,jdbcType=VARCHAR}, ",
            "#{shortName,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, ",
            "#{parentId,jdbcType=VARCHAR}, #{sortNo,jdbcType=INTEGER}, ",
            "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{updateTme,jdbcType=TIMESTAMP}, #{createUser,jdbcType=VARCHAR}, ",
            "#{createUserName,jdbcType=VARCHAR}, #{extend,jdbcType=VARCHAR}, ",
            "#{description,jdbcType=VARCHAR})"
    })
    int insert(UserGroup record);

    @InsertProvider(type = UserGroupSqlProvider.class, method = "insertSelective")
    int insertSelective(UserGroup record);

    @Select({
            "select",
            "guid, cname, short_name, code, parent_id, sort_no, is_delete, create_time, update_tme, ",
            "create_user, create_user_name, extend, description",
            "from user_group",
            "where guid = #{guid,jdbcType=VARCHAR}"
    })
    @Results(id = "userGroupMap", value = {
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "short_name", property = "shortName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "parentId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sort_no", property = "sortNo", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_tme", property = "updateTme", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user", property = "createUser", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_user_name", property = "createUserName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "extend", property = "extend", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    UserGroup selectByPrimaryKey(String guid);

    @UpdateProvider(type = UserGroupSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserGroup record);


    @SelectProvider(type =UserGroupSqlProvider.class,method = "getWhereSql")
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "short_name", property = "shortName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "parentId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sort_no", property = "sortNo", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_tme", property = "updateTme", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user", property = "createUser", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_user_name", property = "createUserName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "extend", property = "extend", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    List<UserGroup> getAllByRecord(UserGroup userGroup);

    @Update({
            "update user_group",
            "set cname = #{cname,jdbcType=VARCHAR},",
            "short_name = #{shortName,jdbcType=VARCHAR},",
            "code = #{code,jdbcType=VARCHAR},",
            "parent_id = #{parentId,jdbcType=VARCHAR},",
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
    int updateByPrimaryKey(UserGroup record);

    @Select({
            "select",
            "guid, cname, code, parent_id, sort_no, is_delete",
            "from user_group",
            "where parent_id = #{parentId,jdbcType=VARCHAR} order by sort_no"
    })
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "parentId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sort_no", property = "sortNo", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER)
    })
    List<UserGroup> selectUserGroupByParentId(String parentId);

    @SelectProvider(type = UserGroupSqlProvider.class, method = "selectUserByNodeId")
    @Results({
            @Result(column="guid", property="guid", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_type", property="cardType", jdbcType=JdbcType.INTEGER),
            @Result(column="id_card", property="idCard", jdbcType=JdbcType.VARCHAR),
            @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
            @Result(column="xb", property="xb", jdbcType=JdbcType.INTEGER),
            @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
            @Result(column="avator", property="avator", jdbcType=JdbcType.VARCHAR),
            @Result(column="sort_no", property="sortNo", jdbcType=JdbcType.INTEGER),
            @Result(column="err_no", property="errNo", jdbcType=JdbcType.INTEGER),
            @Result(column="third_name", property="thirdName", jdbcType=JdbcType.VARCHAR),
            @Result(column="qq_openid", property="qqOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="wx_openid", property="wxOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="is_auth", property="isAuth", jdbcType=JdbcType.INTEGER),
            @Result(column="extend", property="extend", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<UserMessageVo> selectUserByNodeId(String nodeId);

    @SelectProvider(type = UserGroupSqlProvider.class, method = "selectUserByTypeXM")
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "login_name", property = "loginName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "xm", property = "xm", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cname", property = "orgName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "org_id", property = "orgId", jdbcType = JdbcType.VARCHAR)
    })
    List<UserMessageVo> selectUserByTypeXM(String type, String xm);

    @Select({
            "select",
            "guid, cname, code, parent_id",
            "from user_group where is_delete = 0"
    })
    @Results({
            @Result(column = "guid", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "pId", jdbcType = JdbcType.VARCHAR)
    })
    List<Tree> selectAllUserGroup();

    @SelectProvider(type = UserGroupSqlProvider.class, method = "selectAllUsers")
    List<Tree> selectAllUsers(@Param("type") String type,@Param("nodeId") String nodeId);

    @Select({
            "select guid, cname, parent_id",
            "from user_group where parent_id = #{nodeId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "guid", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "pId", jdbcType = JdbcType.VARCHAR)
    })
    List<Tree> selectUserGroupByNodeId(String nodeId);

	@SelectProvider(type = UserGroupSqlProvider.class,method = "selectGroupUserId")
    List<UserDepartment> selectGroupByUserId(String userId);

    @SelectProvider(type = UserGroupSqlProvider.class,method = "fuzzSearch")
    List<UserDepartment> fuzzSearch(@Param("userId") String userId, @Param("keyWord") String keyWord);

    @Select({
            "select guid, cname",
            "from user_group where cname = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR)
    })
    List<UserGroup> selectByName(String name);

    @DeleteProvider(type = UserGroupSqlProvider.class,method = "deleteFromRelation")
    int deleteFromRelation(@Param("type") String type, @Param("userAndOrg") UserAndOrg userAndOrg);

    @InsertProvider(type = UserGroupSqlProvider.class,method = "insertToRelation")
    int insertToRelation(@Param("type") String type,@Param("record") UserAndOrg record);

    @Select({
            "select",
            "guid, cname, short_name, code, parent_id, sort_no, is_delete, create_time, update_tme, ",
            "create_user, create_user_name, extend, description",
            "from user_group"
    })
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "short_name", property = "shortName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "parentId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sort_no", property = "sortNo", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_tme", property = "updateTme", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user", property = "createUser", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_user_name", property = "createUserName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "extend", property = "extend", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    List<UserGroup> selectAllUserGroupList();

    @SelectProvider(type = UserGroupSqlProvider.class, method = "selectUserByNamePhone")
    List<UserMessageVo> selectUserByNamePhone(@Param("queryParam") String queryParam);
}
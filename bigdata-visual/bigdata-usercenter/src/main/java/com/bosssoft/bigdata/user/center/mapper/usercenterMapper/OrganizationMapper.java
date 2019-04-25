package com.bosssoft.bigdata.user.center.mapper.usercenterMapper;

import com.bosssoft.bigdata.user.center.model.Tree;
import com.bosssoft.bigdata.user.center.model.UserDepartment;
import com.bosssoft.bigdata.user.center.sqlProvider.OrganizationSqlProvider;
import com.bosssoft.bigdata.user.center.sqlProvider.UserMessageSqlProvider;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.Organization;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    @Delete({
        "delete from organization",
        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String guid);

    @Insert({
        "insert into organization (guid, name, ",
        "cname, short_name, ",
        "code, parent_id, ",
        "sort_no, is_delete, ",
        "create_time, update_time, ",
        "create_user, create_user_name, ",
        "extend, description, org_type, is_effective, is_entity)",
        "values (#{guid,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{cname,jdbcType=VARCHAR}, #{shortName,jdbcType=VARCHAR}, ",
        "#{code,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{sortNo,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{createUser,jdbcType=VARCHAR}, #{createUserName,jdbcType=VARCHAR}, ",
        "#{extend,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{orgType,jdbcType=VARCHAR}, #{isEffective,jdbcType=VARCHAR}, ",
        "#{isEntity,jdbcType=VARCHAR})"
    })
    int insert(Organization record);

    @InsertProvider(type= OrganizationSqlProvider.class, method="insertSelective")
    int insertSelective(Organization record);

    @Select({
        "select",
        "guid, name, cname, short_name, code, parent_id, sort_no, is_delete, create_time, ",
        "update_time, create_user, create_user_name, extend, description, org_type, is_effective, is_entity",
        "from organization",
        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    @Results(id="orgresultMap",value = {
        @Result(column="guid", property="guid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="cname", property="cname", jdbcType=JdbcType.VARCHAR),
        @Result(column="short_name", property="shortName", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort_no", property="sortNo", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_user_name", property="createUserName", jdbcType=JdbcType.VARCHAR),
        @Result(column="extend", property="extend", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_type", property="orgType", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_effective", property="isEffective", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_entity", property="isEntity", jdbcType=JdbcType.VARCHAR)
    })
    Organization selectByPrimaryKey(String guid);

    @UpdateProvider(type=OrganizationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Organization record);

    @SelectProvider(type = OrganizationSqlProvider.class,method = "getWhereSql")
    @Results({
            @Result(column="guid", property="guid", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="cname", property="cname", jdbcType=JdbcType.VARCHAR),
            @Result(column="short_name", property="shortName", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
            @Result(column="sort_no", property="sortNo", jdbcType=JdbcType.INTEGER),
            @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_user", property="createUser", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_user_name", property="createUserName", jdbcType=JdbcType.VARCHAR),
            @Result(column="extend", property="extend", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="org_type", property="orgType", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_effective", property="isEffective", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_entity", property="isEntity", jdbcType=JdbcType.VARCHAR)
    })
    List<Organization> selectAllByRecord1(Organization record);

    /**
     *查找用户的直属机构
     * @param userId
     * @return
     */
    @Select({
            "select * from organization where guid= ( " +
                    "select org_id from user_and_org where user_id=#{userId}  and is_directly= 1" +
                    "    );"
    })
    Organization findUserDirectly(String userId);

    @Update({
        "update organization",
        "set name = #{name,jdbcType=VARCHAR},",
          "cname = #{cname,jdbcType=VARCHAR},",
          "short_name = #{shortName,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=VARCHAR},",
          "sort_no = #{sortNo,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "create_user = #{createUser,jdbcType=VARCHAR},",
          "create_user_name = #{createUserName,jdbcType=VARCHAR},",
          "extend = #{extend,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "org_type = #{orgType,jdbcType=VARCHAR},",
          "is_effective = #{isEffective,jdbcType=VARCHAR},",
          "is_entity = #{isEntity,jdbcType=VARCHAR}",
        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Organization record);

    @SelectProvider(type = OrganizationSqlProvider.class,method = "selectByParentId")
    List<Organization> selectByParentId(String parentId);

    @SelectProvider(type = OrganizationSqlProvider.class,method = "selectUserOrg")
    List<UserDepartment> selectUserOrg(String userId);

    @Select({
            "select",
            "guid, cname, code, parent_id",
            "from organization where is_delete = 0"
    })
    @Results({
            @Result(column="guid", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="cname", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="pId", jdbcType=JdbcType.VARCHAR)
    })
    List<Tree> selectAllOrganization();

    @SelectProvider(type = OrganizationSqlProvider.class, method="selectUserByNodeId")
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

    @SelectProvider(type = OrganizationSqlProvider.class, method = "selectUserByXM")
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
    List<UserMessage> selectUserByXM(String xm);

    @SelectProvider(type = OrganizationSqlProvider.class, method = "selectOrganizationByNodeId")
    @Results({
            @Result(column = "guid", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id", property = "pId", jdbcType = JdbcType.VARCHAR)
    })
    List<Tree> selectOrganizationByNodeId(@Param("nodeId") String nodeId);

    @SelectProvider(type = OrganizationSqlProvider.class,method = "fuzzSearch")
    List<UserDepartment> fuzzSearch(@Param("userId") String userId, @Param("keyWord") String keyWord);

    @Select({
            "select guid, cname",
            "from organization where cname = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "cname", property = "cname", jdbcType = JdbcType.VARCHAR)
    })
    List<Organization> selectByName(String name);

    @Select({
            "select guid, code",
            "from organization where code = #{code,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "guid", property = "guid", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR)
    })
    List<Organization> selectByCode(String code);

    @SelectProvider(type = OrganizationSqlProvider.class, method = "selectUserByNamePhone")
    List<UserMessageVo> selectUserByNamePhone(@Param("orgId") String orgId, @Param("queryParam") String queryParam);


}
package com.bosssoft.bigdata.user.center.mapper.casMapper;

import com.bosssoft.bigdata.user.center.sqlProvider.RegexRegisteredServiceSqlProvider;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredService;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredServiceWithBLOBs;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface RegexRegisteredServiceMapper {
    @Delete({
        "delete from regexregisteredservice",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(int id);

    @Insert({
        "insert into regexregisteredservice (id, expression_type, ",
        "description, evaluation_order, ",
        "informationUrl, logo, ",
        "logout_type, logout_url, ",
        "name, privacyUrl, ",
        "serviceId, theme, ",
        "access_strategy, attribute_release, ",
        "expiration_policy, mfa_policy, ",
        "proxy_policy, public_key, ",
        "required_handlers, username_attr)",
        "values (#{id,jdbcType=BIGINT}, #{expressionType,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{evaluationOrder,jdbcType=INTEGER}, ",
        "#{informationurl,jdbcType=VARCHAR}, #{logo,jdbcType=VARCHAR}, ",
        "#{logoutType,jdbcType=INTEGER}, #{logoutUrl,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{privacyurl,jdbcType=VARCHAR}, ",
        "#{serviceid,jdbcType=VARCHAR}, #{theme,jdbcType=VARCHAR}, ",
        "#{accessStrategy,jdbcType=LONGVARBINARY}, #{attributeRelease,jdbcType=LONGVARBINARY}, ",
        "#{expirationPolicy,jdbcType=LONGVARBINARY}, #{mfaPolicy,jdbcType=LONGVARBINARY}, ",
        "#{proxyPolicy,jdbcType=LONGVARBINARY}, #{publicKey,jdbcType=LONGVARBINARY}, ",
        "#{requiredHandlers,jdbcType=LONGVARBINARY}, #{usernameAttr,jdbcType=LONGVARBINARY})"
    })
    int insert(RegexRegisteredServiceWithBLOBs record);

    @InsertProvider(type= RegexRegisteredServiceSqlProvider.class, method="insertSelective")
    int insertSelective(RegexRegisteredServiceWithBLOBs record);

    @Select({
        "select",
        "id, expression_type, description, evaluation_order, informationUrl, logo, logout_type, ",
        "logout_url, name, privacyUrl, serviceId, theme, access_strategy, attribute_release, ",
        "expiration_policy, mfa_policy, proxy_policy, public_key, required_handlers, ",
        "username_attr",
        "from regexregisteredservice",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="expression_type", property="expressionType", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="evaluation_order", property="evaluationOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="informationUrl", property="informationurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="logout_type", property="logoutType", jdbcType=JdbcType.INTEGER),
        @Result(column="logout_url", property="logoutUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="privacyUrl", property="privacyurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="serviceId", property="serviceid", jdbcType=JdbcType.VARCHAR),
        @Result(column="theme", property="theme", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_strategy", property="accessStrategy", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="attribute_release", property="attributeRelease", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="expiration_policy", property="expirationPolicy", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="mfa_policy", property="mfaPolicy", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="proxy_policy", property="proxyPolicy", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="public_key", property="publicKey", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="required_handlers", property="requiredHandlers", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="username_attr", property="usernameAttr", jdbcType=JdbcType.LONGVARBINARY)
    })
    RegexRegisteredServiceWithBLOBs selectByPrimaryKey(Long id);

    @UpdateProvider(type= RegexRegisteredServiceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RegexRegisteredServiceWithBLOBs record);

    @Update({
        "update regexregisteredservice",
        "set expression_type = #{expressionType,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "evaluation_order = #{evaluationOrder,jdbcType=INTEGER},",
          "informationUrl = #{informationurl,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "logout_type = #{logoutType,jdbcType=INTEGER},",
          "logout_url = #{logoutUrl,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "privacyUrl = #{privacyurl,jdbcType=VARCHAR},",
          "serviceId = #{serviceid,jdbcType=VARCHAR},",
          "theme = #{theme,jdbcType=VARCHAR},",
          "access_strategy = #{accessStrategy,jdbcType=LONGVARBINARY},",
          "attribute_release = #{attributeRelease,jdbcType=LONGVARBINARY},",
          "expiration_policy = #{expirationPolicy,jdbcType=LONGVARBINARY},",
          "mfa_policy = #{mfaPolicy,jdbcType=LONGVARBINARY},",
          "proxy_policy = #{proxyPolicy,jdbcType=LONGVARBINARY},",
          "public_key = #{publicKey,jdbcType=LONGVARBINARY},",
          "required_handlers = #{requiredHandlers,jdbcType=LONGVARBINARY},",
          "username_attr = #{usernameAttr,jdbcType=LONGVARBINARY}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(RegexRegisteredServiceWithBLOBs record);

    @Update({
        "update regexregisteredservice",
        "set expression_type = #{expressionType,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "evaluation_order = #{evaluationOrder,jdbcType=INTEGER},",
          "informationUrl = #{informationurl,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "logout_type = #{logoutType,jdbcType=INTEGER},",
          "logout_url = #{logoutUrl,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "privacyUrl = #{privacyurl,jdbcType=VARCHAR},",
          "serviceId = #{serviceid,jdbcType=VARCHAR},",
          "theme = #{theme,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(RegexRegisteredService record);

    @SelectProvider(type = RegexRegisteredServiceSqlProvider.class,method = "pageSelect")
    List<RegexRegisteredService> pageSelect(@Param("start") int start,@Param("rows") int rows);

    @SelectProvider(type = RegexRegisteredServiceSqlProvider.class,method = "totalNum")
    int totalNum();

    @SelectProvider(type = RegexRegisteredServiceSqlProvider.class,method = "selectAll")
    List<RegexRegisteredService> selectAll();
}
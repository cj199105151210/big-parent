package com.bosssoft.bigdata.user.center.mapper.usercenterMapper;

import com.bosssoft.bigdata.user.center.sqlProvider.UserMessageSqlProvider;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserMessageMapper {
    @Delete({
        "delete from user",
        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String guid);

    @Insert({
        "insert into user (guid, login_name, ",
        "card_type, id_card, age, ",
        "xm,xb, user_no, type, ",
        "password, phone, ",
        "mail, avator, sort_no, ",
        "err_no, duty, third_name, ",
        "qq_openid, wx_openid, ",
        "is_delete, is_auth, ",
        "extend, first_login, ",
        "first_letter, description)",
        "values (#{guid,jdbcType=VARCHAR}, #{loginName,jdbcType=VARCHAR}, ",
        "#{cardType,jdbcType=INTEGER}, #{idCard,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, ",
        "#{xm,jdbcType=VARCHAR}, #{xb,jdbcType=INTEGER}, #{userNo,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, ",
        "#{password,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{mail,jdbcType=VARCHAR}, #{avator,jdbcType=VARCHAR}, #{sortNo,jdbcType=INTEGER}, ",
        "#{errNo,jdbcType=INTEGER},#{duty,jdbcType=INTEGER}, #{thirdName,jdbcType=VARCHAR}, ",
        "#{qqOpenid,jdbcType=VARCHAR}, #{wxOpenid,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{isAuth,jdbcType=INTEGER}, ",
        "#{extend,jdbcType=VARCHAR},{firstLogin,jdbcType=INTEGER}, ",
        "#{firstLetter,jdbcType=VARCHAR},#{description,jdbcType=VARCHAR}) "
    })
    int insert(UserMessage record);

    @InsertProvider(type= UserMessageSqlProvider.class, method="insertSelective")
    int insertSelective(UserMessage record);

    @Select({
        "select",
        "guid, login_name, card_type, id_card, xm, age, xb, user_no, type, password, phone, mail, ",
        "avator, duty, sort_no, err_no, third_name, qq_openid, wx_openid, is_delete, is_auth, ",
        "extend, first_login, first_letter, description",
        "from user",
        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="guid", property="guid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_type", property="cardType", jdbcType=JdbcType.INTEGER),
        @Result(column="id_card", property="idCard", jdbcType=JdbcType.VARCHAR),
        @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
        @Result(column="xb", property="xb", jdbcType=JdbcType.INTEGER),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
        @Result(column="avator", property="avator", jdbcType=JdbcType.VARCHAR),
        @Result(column="duty", property="duty", jdbcType=JdbcType.INTEGER),
        @Result(column="sort_no", property="sortNo", jdbcType=JdbcType.INTEGER),
        @Result(column="err_no", property="errNo", jdbcType=JdbcType.INTEGER),
        @Result(column="third_name", property="thirdName", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq_openid", property="qqOpenid", jdbcType=JdbcType.VARCHAR),
        @Result(column="wx_openid", property="wxOpenid", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="is_auth", property="isAuth", jdbcType=JdbcType.INTEGER),
        @Result(column="extend", property="extend", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_login", property="firstLogin", jdbcType=JdbcType.INTEGER),
        @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)

    })
    UserMessage selectByPrimaryKey(String guid);

    @UpdateProvider(type=UserMessageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserMessage record);

    @Update({
        "update user",
        "set login_name = #{loginName,jdbcType=VARCHAR},",
          "card_type = #{cardType,jdbcType=INTEGER},",
          "id_card = #{idCard,jdbcType=VARCHAR},",
          "xm = #{xm,jdbcType=VARCHAR},",
          "xb = #{xm,jdbcType=INTEGER},",
          "age = #{age,jdbcType=INTEGER},",
          "user_no = #{userNo,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "password = #{password,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "mail = #{mail,jdbcType=VARCHAR},",
          "avator = #{avator,jdbcType=VARCHAR},",
          "duty = #{duty,jdbcType=INTEGER},",
          "sort_no = #{sortNo,jdbcType=INTEGER},",
          "err_no = #{errNo,jdbcType=INTEGER},",
          "third_name = #{thirdName,jdbcType=VARCHAR},",
          "qq_openid = #{qqOpenid,jdbcType=VARCHAR},",
          "wx_openid = #{wxOpenid,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "is_auth = #{isAuth,jdbcType=INTEGER},",
          "extend = #{extend,jdbcType=VARCHAR},",
          "first_login = #{firstLogin,jdbcType=INTEGER},",
          "first_letter = #{firstLetter,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",

        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(UserMessage record);

    /**
     * 通过用户名增加错误次数
     * @param loginName
     * @return
     */
    @UpdateProvider(type = UserMessageSqlProvider.class,method = "increasePasswordByLoginName")
    int increasePasswordByLoginName(String loginName);

    @Update({
            "update user",
            "set password = #{password,jdbcType=VARCHAR}",
            "where login_name = #{loginName,jdbcType=VARCHAR}"
    })
    int updatePassword(UserMessage user);

    @Select({
            "select",
            "guid, login_name, card_type, id_card, xm, age, xb, user_no, type, password, phone, mail, ",
            "avator, duty, sort_no, err_no, third_name, qq_openid, wx_openid, is_delete, is_auth, ",
            "extend, first_login, first_letter, description",
            "from user",
            "where login_name = #{loginName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="guid", property="guid", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_type", property="cardType", jdbcType=JdbcType.INTEGER),
            @Result(column="id_card", property="idCard", jdbcType=JdbcType.VARCHAR),
            @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
            @Result(column="xb", property="xb", jdbcType=JdbcType.INTEGER),
            @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
            @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
            @Result(column="avator", property="avator", jdbcType=JdbcType.VARCHAR),
            @Result(column="duty", property="duty", jdbcType=JdbcType.INTEGER),
            @Result(column="sort_no", property="sortNo", jdbcType=JdbcType.INTEGER),
            @Result(column="err_no", property="errNo", jdbcType=JdbcType.INTEGER),
            @Result(column="third_name", property="thirdName", jdbcType=JdbcType.VARCHAR),
            @Result(column="qq_openid", property="qqOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="wx_openid", property="wxOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="is_auth", property="isAuth", jdbcType=JdbcType.INTEGER),
            @Result(column="extend", property="extend", jdbcType=JdbcType.VARCHAR),
            @Result(column="first_login", property="firstLogin", jdbcType=JdbcType.INTEGER),
            @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    UserMessage selectByLoginName(String loginName);


    @SelectProvider(type = UserMessageSqlProvider.class, method = "selectUserForPage")
    @Results({
            @Result(column="guid", property="guid", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_type", property="cardType", jdbcType=JdbcType.INTEGER),
            @Result(column="id_card", property="idCard", jdbcType=JdbcType.VARCHAR),
            @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
            @Result(column="xb", property="xb", jdbcType=JdbcType.INTEGER),
            @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
            @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
            @Result(column="avator", property="avator", jdbcType=JdbcType.VARCHAR),
            @Result(column="duty", property="duty", jdbcType=JdbcType.INTEGER),
            @Result(column="sort_no", property="sortNo", jdbcType=JdbcType.INTEGER),
            @Result(column="err_no", property="errNo", jdbcType=JdbcType.INTEGER),
            @Result(column="third_name", property="thirdName", jdbcType=JdbcType.VARCHAR),
            @Result(column="qq_openid", property="qqOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="wx_openid", property="wxOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="is_auth", property="isAuth", jdbcType=JdbcType.INTEGER),
            @Result(column="extend", property="extend", jdbcType=JdbcType.VARCHAR),
            @Result(column="first_login", property="firstLogin", jdbcType=JdbcType.INTEGER),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<UserMessage> selectUserForPage(@Param("type") String type, @Param("name") String name);

    @SelectProvider(type = UserMessageSqlProvider.class, method = "selectUserByNamePhone")
    List<UserMessageVo> selectUserByNamePhone(@Param("queryParam") String queryParam);

    @SelectProvider(type = UserMessageSqlProvider.class, method = "selectUserByName")
    List<UserMessageVo> selectUserByName(@Param("xm") String xm);

    @Update({
          "update user set first_login = 0",
          "where login_name = #{userName,jdbcType=VARCHAR}"
    })
    int updUserFirstLogin(String userName);

    @Select({
            "select",
            "guid, login_name, card_type, id_card, xm, age, xb, user_no, type, password, phone, mail, ",
            "avator, duty, sort_no, err_no, third_name, qq_openid, wx_openid, is_delete, is_auth, ",
            "extend, first_login, first_letter, description",
            "from user",
            "where login_name = #{loginName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="guid", property="guid", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_type", property="cardType", jdbcType=JdbcType.INTEGER),
            @Result(column="id_card", property="idCard", jdbcType=JdbcType.VARCHAR),
            @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
            @Result(column="xb", property="xb", jdbcType=JdbcType.INTEGER),
            @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
            @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
            @Result(column="avator", property="avator", jdbcType=JdbcType.VARCHAR),
            @Result(column="duty", property="duty", jdbcType=JdbcType.INTEGER),
            @Result(column="sort_no", property="sortNo", jdbcType=JdbcType.INTEGER),
            @Result(column="err_no", property="errNo", jdbcType=JdbcType.INTEGER),
            @Result(column="third_name", property="thirdName", jdbcType=JdbcType.VARCHAR),
            @Result(column="qq_openid", property="qqOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="wx_openid", property="wxOpenid", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="is_auth", property="isAuth", jdbcType=JdbcType.INTEGER),
            @Result(column="extend", property="extend", jdbcType=JdbcType.VARCHAR),
            @Result(column="first_login", property="firstLogin", jdbcType=JdbcType.INTEGER),
            @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    UserMessageVo selectByUserName(String loginName);
}
package com.bosssoft.bigdata.user.center.mapper.usercenterMapper;

import com.bosssoft.bigdata.usercenter.api.entity.UserLog;
import com.bosssoft.bigdata.user.center.sqlProvider.UserLogSqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserLogMapper {
    @Delete({
        "delete from user_log",
        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String guid);

    @Insert({
        "insert into user_log (guid, user_name, ",
        "phone, login_type, ",
        "opt_time, opt_type)",
        "values (#{guid,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{loginType,jdbcType=INTEGER}, ",
        "#{optTime,jdbcType=TIMESTAMP}, #{optType,jdbcType=INTEGER})"
    })
    int insert(UserLog record);

    @InsertProvider(type= UserLogSqlProvider.class, method="insertSelective")
    int insertSelective(UserLog record);

    @Select({
        "select",
        "guid, user_name, phone, login_type, opt_time, opt_type",
        "from user_log",
        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="guid", property="guid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_type", property="loginType", jdbcType=JdbcType.INTEGER),
        @Result(column="opt_time", property="optTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="opt_type", property="optType", jdbcType=JdbcType.INTEGER)
    })
    UserLog selectByPrimaryKey(String guid);

    @UpdateProvider(type=UserLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserLog record);

    @Update({
        "update user_log",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "login_type = #{loginType,jdbcType=INTEGER},",
          "opt_time = #{optTime,jdbcType=TIMESTAMP},",
          "opt_type = #{optType,jdbcType=INTEGER}",
        "where guid = #{guid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(UserLog record);
}
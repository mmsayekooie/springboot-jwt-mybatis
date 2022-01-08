package com.codeex.demo.mapper;

import com.codeex.demo.entity.Task;
import com.codeex.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface UsersMapper {

    @Select("select * from users")
    List<User> findAll();

    @Insert("insert into users(username,password) values(#{userName},#{password})")
    @SelectKey(statement = "SELECT lAST_iNSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    void insert(User user);


    @Select("SELECT userId FROM user where userName=#{userName}")
    @Results(value = {
            @Result(property="id", column = "id"),
            @Result(property="username", column = "username"),
            @Result(property="user", column="id", javaType= List.class, many=@Many(select="selectTasks"))
    })
    List<Task> findAllTask(@PathVariable String userName);

    @Select("SELECT * FROM task WHERE userId = #{userId}")
    @Results(value={
            @Result(property="id", column ="id" ),
            @Result(property="subject", column = "subject"),
            @Result(property="date", column = "date")
    })
    List<Task> selectTasks(String userId);
}

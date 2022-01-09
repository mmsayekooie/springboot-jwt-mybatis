package com.codeex.demo.service;

import com.codeex.demo.entity.User;
import com.codeex.demo.mapper.UsersMapper;

import java.util.HashMap;
import java.util.Map;

public class UserService {
private UsersMapper usersMapper;

    public UserService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    public User login(String userName,String password) {
        User userdb=usersMapper.login(userName,password);
        if(userdb!=null)
        {
            Map<String,String> map=new HashMap<>();
            map.put("name",userdb.getUserName());
            return userdb;
        }
        throw  new RuntimeException("Login failed");
    }
}

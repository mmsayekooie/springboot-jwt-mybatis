package com.codeex.demo.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.codeex.demo.entity.Task;
import com.codeex.demo.entity.User;
import com.codeex.demo.mapper.UsersMapper;
import com.codeex.demo.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/user")
public class UserController {
    @Autowired
    private UsersMapper usersMapper;



@GetMapping("/alltask/")
public List<Task> getAllTask(User user){
    user.getPassword();
    return usersMapper.findAllTask(user.getUserName());
}

    @GetMapping("/all")
    public List<User> getAll(){
       return usersMapper.findAll();
    }

    @GetMapping("/update")
    public List<User> updateUser(){
        User user =new User();
        user.setUserName("hasan");
        user.setPassword("258987");
        usersMapper.insert(user);
        return usersMapper.findAll();
    }

    @GetMapping("/login")
    public Map<String,Object> login(User user)
    {

        Map<String,Object> map=new HashMap<>();
        try {
            usersMapper.login(user);
            map.put("msg","Login succeeded");
            map.put("code","200");

            Map<String,String> payload=new HashMap<>();
            payload.put("name",user.getUserName());
            String token= JwtUtils.getToken(payload);
            map.put("token",token);
        }
        catch (Exception ex)
        {
            map.put("msg","Login failed");
        }

        return map;
    }

    @PostMapping("/verify")
    public  Map<String,String> verifyToken(String token)
    {
        Map<String, String> map=new HashMap<>();
        try {
            DecodedJWT verify = JwtUtils.verify(token);
            map.put("msg","Validation succeeded");
            map.put("state","true");

        }
        catch (Exception exception)
        {
            map.put("msg","Validation failed");
            exception.printStackTrace();
        }
        return map;
    }

    @PostMapping("/saveTask")
    public Task saveTask(Task task){
    return usersMapper.saveTask(task);
    }
}

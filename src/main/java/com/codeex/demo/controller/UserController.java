package com.codeex.demo.controller;

import com.codeex.demo.entity.Task;
import com.codeex.demo.entity.User;
import com.codeex.demo.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserController {
    @Autowired
    private UsersMapper usersMapper;

@GetMapping("/alltask/{userName}")
public List<Task> getAllTask(@PathVariable String userName){
    return usersMapper.findAllTask(userName);
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
}

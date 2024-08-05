package com.example.controller;

import com.example.common.Result;
import com.example.entity.Params;
import com.example.entity.Symptom;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userservice;

    @PostMapping("/edit")
    public Result edit(@RequestBody User user){
        userservice.edit(user);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        userservice.delete(id);
        return Result.success();
    }
    @PostMapping("/signup")
    public Result add(@RequestBody User user){
        userservice.add(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User loginuser = userservice.login(user);
        return Result.success(loginuser);
    }

    @GetMapping
    public Result getuser(){
        List<User> list= userservice.getuser();
        return Result.success(list);
    }
    @GetMapping("/search")
    public  Result findBySearch(Params params){
        PageInfo<User> info = userservice.findBySearch(params);
        return Result.success(info);
    }
}

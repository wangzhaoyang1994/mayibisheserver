package com.example.springbootpro.controller;

import com.example.springbootpro.entity.User;
import com.example.springbootpro.mapper.UserMapper;
import com.example.springbootpro.service.UserService;
import com.example.springbootpro.utils.AjaxResult;
import com.example.springbootpro.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/list")
    public AjaxResult list(){
        List<User> list = userService.selectUser();
        return AjaxResult.success(MessageUtils.get("success"),list);
    }
    @PostMapping("/getReal")
    public AjaxResult getReal(){
        Map<String,Object> map = new HashMap<>();
        map.put("userName","wangzhaoyang");
        map.put("userPassword","123456");
        map.put("userRealName","");
        User user = userMapper.selectRealName(map);
        return AjaxResult.success(MessageUtils.get("success"),user);
    }
    @PostMapping("getRealByEntity")
    public AjaxResult getRealByEntity(){
        User user = new User();
        user.setUserName("wangzhaoyang");
        user.setUserPassword("123456");
        User user1 = userService.selectRealNameByEntity(user);
        return AjaxResult.success(MessageUtils.get("success"),user1.getUserRealName());
    }
}

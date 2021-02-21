package com.example.springbootpro.service.impl;

import com.example.springbootpro.entity.User;
import com.example.springbootpro.mapper.UserMapper;
import com.example.springbootpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectUser() {
        return userMapper.selectUser();
    }

    @Override
    public User selectRealName(Map<String, Object> map) {
        return userMapper.selectRealName(map);
    }

    @Override
    public User selectRealNameByEntity(User user) {
        return userMapper.selectRealNameByEntity(user);
    }

    @Override
    public List<User> selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }
}

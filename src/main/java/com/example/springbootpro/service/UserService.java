package com.example.springbootpro.service;

import com.example.springbootpro.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询用户
     * @return
     */
    public List<User> selectUser();
    /**
     * 存储过程查询真实姓名
     * @param map
     * @return
     */
    public User selectRealName(Map<String,Object> map);
    /**
     * 存储过程查询（传实体类）
     * @param user
     * @return
     */
    public User selectRealNameByEntity(User user);

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    public List<User> selectUserByName(String userName);
}

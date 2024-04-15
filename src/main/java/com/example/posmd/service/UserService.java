package com.example.posmd.service;

import com.example.posmd.bean.User;

import java.util.List;

/**
 * FileName:    UserService
 * Author:      Yuan-Programmer
 * Date:        2021/12/11 9:49
 * Description:
 */
public interface UserService {

    void insertUser(User user);// 添加用户

    void deleteUser(Integer uid);// 删除用户

    void updateUser(User user);// 修改用户

    List<User> selectAllUser();// 查询所有用户

    List<User> selectLike(String search);// 模糊查询
}

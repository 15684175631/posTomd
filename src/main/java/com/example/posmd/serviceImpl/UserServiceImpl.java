package com.example.posmd.serviceImpl;

import com.example.posmd.bean.User;
import com.example.posmd.repository.UserRepository;
import com.example.posmd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * FileName:    UserServieImpl
 * Author:      Yuan-Programmer
 * Date:        2021/12/11 9:56
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer uid) {
        userRepository.deleteById(uid);
    }

    @Override
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> selectAllUser() {
        return userRepository.findAll();
    }

    /**
     * 查询优先级：
     * 1.先查询是否为整型，为整型则通过ID主键查询，返回结果，不为整型则模糊查询其他字段
     * 2.模糊查询字段，忽略密码的模糊查询，对用户名和昵称进行模糊查询，返回结果
     * @param search 查询字段
     * @return 查询列表集合
     */
    @Override
    public List<User> selectLike(String search) {
        List<User> list = new ArrayList<>();// 查询列表集合
        User user = new User();
        user.setUsername(search);
        user.setNickname(search);
        try {
            Integer uid = Integer.parseInt(search);
            Optional<User> optional = userRepository.findById(uid);
            if (!optional.isPresent()) {
                list = selectVague(user);
            } else {
                list.add(optional.get());
            }
        }catch (NumberFormatException e) {
            // 查询字段不为整型数据，捕获异常
            list = selectVague(user);
        }

        return list;
    }

    /**
     * 模糊查询
     * @param user
     * @return
     */
    private List<User> selectVague(User user) {
        List<User> list = null;
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("nickname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase("uid")
                .withIgnoreCase("password");
        Example<User> example = Example.of(user, matcher);
        list = userRepository.findAll(example);
        return list;
    }
}

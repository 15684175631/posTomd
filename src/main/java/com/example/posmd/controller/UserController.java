package com.example.posmd.controller;

import com.example.posmd.bean.User;
import com.example.posmd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * FileName:    UserController
 * Author:      Yuan-Programmer
 * Date:        2021/12/11 11:24
 * Description:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/select")
    public ModelAndView selectLike(String search) {
        ModelAndView view = new ModelAndView("index::userTable");
        view.addObject("user_list", userService.selectLike(search));
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteUser(Integer id) {
        userService.deleteUser(id);
        ModelAndView view = new ModelAndView("index::userTable");
        view.addObject("user_list", userService.selectAllUser());
        return view;
    }

    @PostMapping(value = "/insert")
    public ModelAndView insertUser(User user) {
        // 插入数据
        userService.insertUser(user);
        // 回传代码片段
        ModelAndView view = new ModelAndView("index::userTable");
        view.addObject("user_list", userService.selectAllUser());
        return view;
    }

    @PostMapping(value = "update")
    public ModelAndView updateUser(User user) {
        userService.updateUser(user);
        ModelAndView view = new ModelAndView("index::userTable");
        view.addObject("user_list", userService.selectAllUser());
        return view;
    }
}

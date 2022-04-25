package com.dyh.myspringbootdemo.controller;

import com.dyh.myspringbootdemo.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/index")
    public String getUserInfo(@RequestBody Map<String, Object> data) {
        return userService.getUserInfo(data);
    }
}

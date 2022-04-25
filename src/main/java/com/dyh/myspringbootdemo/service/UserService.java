package com.dyh.myspringbootdemo.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;

    public String getUserInfo(Map<String, Object> data) {
        return "hello spring mvc" + data;
    }

}

package com.dyh.myspringbootdemo.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private SqlSessionFactory sqlSessionFactory;

    public UserService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public String getUserInfo(Map<String, Object> data) {
        return "hello spring mvc" + data;
    }

}

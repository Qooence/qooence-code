package com.qooence.code.service;

import com.qooence.code.api.UserApi;
import com.qooence.code.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Override
    public User getUserById(Long id) {
        logger.info("根据id[{}]获取User",id);
        return new User(1L,"bbo",18);
    }

    @Override
    public List<User> getUserList() {
        logger.info("获取列表");
        List<User> list = new ArrayList<User>();
        list.add(new User(1L,"bbo",18));
        list.add(new User(2L,"qooence",18));
        list.add(new User(3L,"code",18));
        return list;
    }
}

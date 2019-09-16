package com.qooence.code.api;


import com.qooence.code.entity.User;

import java.util.List;

public interface UserApi {

    User getUserById(Long id);

    List<User> getUserList();
}

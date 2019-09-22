package com.qooence.code.usercenter.validator;

import com.qooence.code.usercenter.dto.UserLoginRequest;
import org.springframework.util.StringUtils;

public class UserValidator {

    public static boolean checkUserLoginRequest(UserLoginRequest request) {
        if (StringUtils.isEmpty(request.getName()) || StringUtils.isEmpty(request.getPassword())) {
            return false;
        }
        return true;
    }
}

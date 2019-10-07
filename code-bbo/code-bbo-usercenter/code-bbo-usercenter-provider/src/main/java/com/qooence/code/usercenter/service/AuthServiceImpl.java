package com.qooence.code.usercenter.service;

import com.qooence.code.usercenter.api.IAuthService;
import com.qooence.code.usercenter.common.ResultCode;
import com.qooence.code.usercenter.common.ResultJson;
import com.qooence.code.usercenter.dto.ResponseUserToken;
import com.qooence.code.usercenter.entity.Role;
import com.qooence.code.usercenter.dto.AuthUser;
import com.qooence.code.usercenter.entity.User;
import com.qooence.code.usercenter.entity.UserDetail;
import com.qooence.code.usercenter.exception.CustomException;
import com.qooence.code.usercenter.mapper.AuthMapper;
import com.qooence.code.usercenter.mapper.RoleMapper;
import com.qooence.code.usercenter.mapper.UserMapper;
import com.qooence.code.usercenter.mapper.UserRoleMapper;
import com.qooence.code.usercenter.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service(interfaceName = "authService",timeout = 1000)
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    @Transactional
    public AuthUser register(AuthUser authUser) {
        final String username = authUser.getUsername();
        if(authMapper.findByUsername(username)!=null) {
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在"));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = authUser.getPassword();
        authUser.setPassword(encoder.encode(rawPassword));
        authUser.setLastPasswordResetDate(new Date());
        User user = User.builder().name(authUser.getUsername()).password(authUser.getPassword()).build();
        // 1、插入新增用户
        userMapper.insert(user);

        // 2、判断角色
        if(StringUtils.isNotBlank(authUser.getRoleIds())){
            List<Role> roles = new ArrayList<>();
            for (String roleId : (authUser.getRoleIds().split(","))) {
                Role role = roleMapper.selectById(roleId);
                if(null != role){
                    // 在用户角色关系表中加入记录
                    userRoleMapper.insertRole(user.getId(),role.getId());
                    roles.add(role);
                }
            }
            authUser.setRoleList(roles);
        }
        return authUser;
    }

    @Override
    public ResponseUserToken login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);

        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        //存储token
        final String token = jwtTokenUtil.generateAccessToken(userDetail);

        AuthUser authUser = new AuthUser(userDetail.getId(), userDetail.getUsername(), userDetail.getPassword(),
                new ArrayList<Role>(){{add(userDetail.getRole());}});

        ResponseUserToken responseUserToken = new ResponseUserToken(token, authUser);
        return responseUserToken;

    }

    @Override
    public void logout(String token) {
        token = token.substring(tokenHead.length());
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        jwtTokenUtil.deleteToken(userName);
    }

    @Override
    public ResponseUserToken refresh(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        AuthUser userDetail = (AuthUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, userDetail.getLastPasswordResetDate())){
            token =  jwtTokenUtil.refreshToken(token);
            return new ResponseUserToken(token, userDetail);
        }
        return null;
    }

    @Override
    public AuthUser getUserByToken(String token) {
        token = token.substring(tokenHead.length());
//        return jwtTokenUtil.getUserFromToken(token);
        return null;
    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }

}

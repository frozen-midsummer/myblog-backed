package com.wjx.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.database.convertor.UserConvertor;
import com.wjx.database.dataobject.UserDO;
import com.wjx.database.dataobject.UserInfoDO;
import com.wjx.database.mapper.UserInfoMapper;
import com.wjx.database.mapper.UserMapper;
import com.wjx.dto.RegisterCmd;
import com.wjx.dto.UserDTO;
import com.wjx.utils.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserConvertor userConvertor;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterCmd registerCmd) {
        UserDO userDO = new UserDO();
        Long id = SnowflakeIdGenerator.newSnowflakeId();
        userDO.setId(id);
        userDO.setUsername(registerCmd.getUsername());
        userDO.setPassword(passwordEncoder.encode(registerCmd.getPassword()));
        // 假设User实体有设置角色的方法，这里省略
        userMapper.insert(userDO);
        //插入用户信息表
        UserInfoDO userInfoDO = userConvertor.toUserInfoDO(registerCmd);
        userInfoDO.setId(id);
        userInfoMapper.insert(userInfoDO);
    }

    public boolean usernameExist(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        return userMapper.exists(queryWrapper);
    }
}
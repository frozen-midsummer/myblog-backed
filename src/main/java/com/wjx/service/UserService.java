package com.wjx.service;

import com.wjx.database.dataobject.UserDO;
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
    private PasswordEncoder passwordEncoder;

    public void register(RegisterCmd registerCmd) {
        UserDO userDO = new UserDO();
        userDO.setId(SnowflakeIdGenerator.newSnowflakeId());
        userDO.setUsername(registerCmd.getUsername());
        userDO.setPassword(passwordEncoder.encode(registerCmd.getPassword()));
        // 假设User实体有设置角色的方法，这里省略
        userMapper.insert(userDO);
    }
}
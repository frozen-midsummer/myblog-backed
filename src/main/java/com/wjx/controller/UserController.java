package com.wjx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.database.convertor.UserConvertor;
import com.wjx.database.dataobject.UserDO;
import com.wjx.database.mapper.UserMapper;
import com.wjx.dto.RegisterCmd;
import com.wjx.dto.UserDTO;
import com.wjx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseService {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserConvertor userConvertor;

    @PostMapping("/register")
    public ResponseEntity<ApiResult<UserDTO>> register(@RequestBody RegisterCmd registerCmd) throws Exception {
        userService.register(registerCmd);
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, registerCmd.getUsername());
        return ResponseEntity.ok(ok(userConvertor.toDataTransferObj(userMapper.selectOne(queryWrapper))));
    }
}

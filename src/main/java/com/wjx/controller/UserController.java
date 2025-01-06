package com.wjx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.database.convertor.UserConvertor;
import com.wjx.database.dataobject.UserDO;
import com.wjx.database.dataobject.UserInfoDO;
import com.wjx.database.mapper.UserInfoMapper;
import com.wjx.database.mapper.UserMapper;
import com.wjx.dto.RegisterCmd;
import com.wjx.dto.UserDTO;
import com.wjx.dto.UserInfoDTO;
import com.wjx.dto.UserInfoUpdateCmd;
import com.wjx.service.UserService;
import com.wjx.utils.LongUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseService {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserConvertor userConvertor;

    @PostMapping("/register")
    public ResponseEntity<ApiResult<UserDTO>> register(@RequestBody RegisterCmd registerCmd) throws Exception {
        if (userService.usernameExist(registerCmd.getUsername())) {
            return ResponseEntity.ok(fail(100001, "用户名已存在"));
        }
        userService.register(registerCmd);
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, registerCmd.getUsername());
        return ResponseEntity.ok(ok(userConvertor.toDataTransferObj(userMapper.selectOne(queryWrapper))));
    }

    @PostMapping("/modify")
    public ResponseEntity<ApiResult<UserInfoDTO>> modify(@RequestBody UserInfoUpdateCmd updateCmd) throws Exception {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setId(LongUtil.convertStr2Long(updateCmd.getId()));
        userInfoDO.setUsername(updateCmd.getUsername());
        userInfoDO.setSex(updateCmd.getSex());
        userInfoDO.setBirthday(updateCmd.getBirthday());
        userInfoDO.setLocation(updateCmd.getLocation());
        userInfoDO.setDescription(updateCmd.getDescription());
        userInfoDO.setFeelings(updateCmd.getFeelings());
        userInfoDO.setSkills(updateCmd.getSkills());
        userInfoMapper.updateById(userInfoDO);
        return ResponseEntity.ok(ok(userConvertor.toUserInfoDTO(userInfoMapper.selectById(updateCmd.getId()))));
    }

    @GetMapping("/getByUsername")
    public ResponseEntity<ApiResult<UserInfoDTO>> getByUsername(@RequestParam("username") String username) throws Exception {
        if (!userService.usernameExist(username)) {
            return ResponseEntity.ok(fail(100001, String.format("用户名[%s]不存在", username)));
        }
        LambdaQueryWrapper<UserInfoDO> queryWrapper = Wrappers.lambdaQuery(UserInfoDO.class)
                .eq(UserInfoDO::getUsername, username);
        UserInfoDO userInfoDO = userInfoMapper.selectOne(queryWrapper);
        return ResponseEntity.ok(ok(userConvertor.toUserInfoDTO(userInfoDO)));
    }
}

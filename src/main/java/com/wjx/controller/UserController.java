package com.wjx.controller;

import com.wjx.common.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.dto.RegisterCmd;
import com.wjx.dto.UserDO;
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

    @PostMapping("/register")
    public ResponseEntity<ApiResult<UserDO>> register(@RequestBody RegisterCmd registerCmd) throws Exception {
        UserDO userDO = new UserDO(registerCmd.getUsername(), registerCmd.getPassword());
        userService.register(userDO);
        return ResponseEntity.ok(ok(userDO));
    }
}

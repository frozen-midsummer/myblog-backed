package com.wjx.controller;

import com.wjx.common.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.dto.UserTaskCreateCmd;
import com.wjx.entity.User;
import com.wjx.entity.UserTaskDO;
import com.wjx.mapper.UserTasksMapper;
import com.wjx.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userdata")
public class UserTasksController extends BaseService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserTasksMapper userTasksMapper;

    @PostMapping("/tasks")
    public ResponseEntity<ApiResult<ArrayList<UserTaskDO>>> getTasksByUsername(HttpServletRequest request, HttpServletResponse response) {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        List<UserTaskDO> res = null;
        if (requestTokenHeader != null) {
            String jwtToken = requestTokenHeader.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            res = userTasksMapper.findAllTasksByUserName(username);
        }
        return ResponseEntity.ok(ok(res));
    }

    @PostMapping("/insertTask")
    public ResponseEntity<ApiResult<UserTaskDO>> insertTask(HttpServletRequest request, @RequestBody UserTaskCreateCmd createCmd) throws Exception {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        if (requestTokenHeader != null) {
            String jwtToken = requestTokenHeader.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }
        if (username == null) {
            throw new Exception("无效的token");
        }
        UserTaskDO res = new UserTaskDO(username, LocalDateTime.now(), LocalDateTime.now(), createCmd.getDeadline(), createCmd.getDescription(), createCmd.getAlarm());
        userTasksMapper.insertUserTask(res);
        return ResponseEntity.ok(ok(res));
    }
}

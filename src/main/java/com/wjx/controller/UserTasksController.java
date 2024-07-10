package com.wjx.controller;

import com.wjx.entity.UserTasks;
import com.wjx.mapper.UserTasksMapper;
import com.wjx.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userdata")
public class UserTasksController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserTasksMapper userTasksMapper;

    @PostMapping("/tasks")
    public ResponseEntity<?> getTasksByUsername(HttpServletRequest request, HttpServletResponse response) {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        List<UserTasks> res = null;
        if (requestTokenHeader != null) {
            String jwtToken = requestTokenHeader.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            res = userTasksMapper.findAllTasksByUserName(username);
        }
        return ResponseEntity.ok(res);
    }
}

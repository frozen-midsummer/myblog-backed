package com.wjx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.database.convertor.UserConvertor;
import com.wjx.database.convertor.UserTaskConvertor;
import com.wjx.dto.UserTaskCreateCmd;
import com.wjx.database.dataobject.UserTaskDO;
import com.wjx.database.mapper.UserTasksMapper;
import com.wjx.dto.UserTaskDTO;
import com.wjx.dto.UserTaskDelCmd;
import com.wjx.dto.UserTaskUpdateCmd;
import com.wjx.service.UserTaskService;
import com.wjx.utils.JwtTokenUtil;
import com.wjx.utils.LongUtil;
import com.wjx.utils.SnowflakeIdGenerator;
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
public class UserDataController extends BaseService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserTasksMapper userTasksMapper;
    @Autowired
    private UserTaskConvertor userTaskConvertor;

    @Autowired
    private UserTaskService userTaskService;

    @PostMapping("/getTaskById")
    public ResponseEntity<ApiResult<UserTaskDTO>> getTaskById(@RequestBody UserTaskDelCmd delCmd) {
        UserTaskDO userTaskDO = userTasksMapper.selectById(delCmd.getTaskId());
        return ResponseEntity.ok(ok(userTaskConvertor.toDataTransferObj(userTaskDO)));
    }

    @PostMapping("/getTasks")
    public ResponseEntity<ApiResult<ArrayList<UserTaskDTO>>> getTasksByUsername(HttpServletRequest request, HttpServletResponse response) {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        List<UserTaskDTO> res = null;
        if (requestTokenHeader != null) {
            String jwtToken = requestTokenHeader.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            LambdaQueryWrapper<UserTaskDO> queryWrapper = Wrappers.lambdaQuery(UserTaskDO.class)
                    .eq(UserTaskDO::getUsername, username);
            res = userTaskConvertor.toDataTransferObjList(userTasksMapper.selectList(queryWrapper));
        }
        return ResponseEntity.ok(ok(res));
    }

    @PostMapping("/insertTask")
    public ResponseEntity<ApiResult<UserTaskDTO>> insertTask(HttpServletRequest request, @RequestBody UserTaskCreateCmd createCmd) throws Exception {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        if (requestTokenHeader != null) {
            String jwtToken = requestTokenHeader.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }
        if (username == null) {
            throw new Exception("无效的token");
        }
        UserTaskDO res = new UserTaskDO(SnowflakeIdGenerator.newSnowflakeId(), username, LocalDateTime.now(), LocalDateTime.now(), createCmd.getDeadline(), createCmd.getDescription(), createCmd.getAlarm());
        userTasksMapper.insert(res);
        return ResponseEntity.ok(ok(userTaskConvertor.toDataTransferObj(res)));
    }

    @PostMapping("/deleteTask")
    public ResponseEntity<ApiResult> deleteTask(HttpServletRequest request, @RequestBody UserTaskDelCmd delCmd) throws Exception {
        userTasksMapper.deleteById(LongUtil.convertStr2Long(delCmd.getTaskId()));
        return ResponseEntity.ok(ok());
    }

    @PostMapping("/modifyTask")
    public ResponseEntity<ApiResult<UserTaskDTO>> modifyTask(HttpServletRequest request, @RequestBody UserTaskUpdateCmd updateCmd) throws Exception {
        UserTaskDO userTaskDO = userTasksMapper.selectById(updateCmd.getTaskId());
        userTaskService.ModifyTask(userTaskDO, updateCmd);
        userTasksMapper.updateById(userTaskDO);
        return ResponseEntity.ok(ok(userTaskConvertor.toDataTransferObj(userTaskDO)));
    }
}

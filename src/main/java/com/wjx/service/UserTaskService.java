package com.wjx.service;

import com.wjx.database.dataobject.UserTaskDO;
import com.wjx.dto.UserTaskUpdateCmd;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserTaskService {
    public void ModifyTask(UserTaskDO userTaskDO, UserTaskUpdateCmd updateCmd) {
        userTaskDO.setUpdatedTime(LocalDateTime.now());
        userTaskDO.setDeadline(updateCmd.getDeadline());
        userTaskDO.setDescription(updateCmd.getDescription());
        userTaskDO.setAlarm(updateCmd.getAlarm());
    }
}

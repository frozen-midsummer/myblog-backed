package com.wjx.service;

import com.wjx.database.dataobject.UserTaskDO;
import com.wjx.dto.UserTaskUpdateCmd;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class UserTaskService {
    public void ModifyTask(UserTaskDO userTaskDO, UserTaskUpdateCmd updateCmd) {
        userTaskDO.setUpdatedTime(LocalDateTime.now());
        userTaskDO.setDeadline(updateCmd.getDeadline().withZoneSameInstant(ZoneId.of("Asia/Shanghai")));
        userTaskDO.setDescription(updateCmd.getDescription());
        userTaskDO.setAlarm(updateCmd.getAlarm());
    }
}

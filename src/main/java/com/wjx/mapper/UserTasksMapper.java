package com.wjx.mapper;

import com.wjx.entity.UserTaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserTasksMapper {
    List<UserTaskDO> findAllTasksByUserName(String username);
    /**
     * 插入UserTasks记录
     * @param userTaskDO 待插入的UserTasks对象
     * @return 受影响的行数
     */
    int insertUserTask(UserTaskDO userTaskDO);
}

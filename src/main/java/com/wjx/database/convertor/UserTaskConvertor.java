package com.wjx.database.convertor;

import com.wjx.database.dataobject.UserTaskDO;
import com.wjx.dto.UserTaskDTO;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserTaskConvertor {
    UserTaskDTO toDataTransferObj(UserTaskDO userTaskDO);

    List<UserTaskDTO> toDataTransferObjList(List<UserTaskDO> userTaskDOList);

    UserTaskDO toDataObject(UserTaskDTO userTaskDTO);
}

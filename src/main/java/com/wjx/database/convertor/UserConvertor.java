package com.wjx.database.convertor;

import com.wjx.database.dataobject.UserDO;
import com.wjx.database.dataobject.UserInfoDO;
import com.wjx.dto.RegisterCmd;
import com.wjx.dto.UserDTO;
import com.wjx.dto.UserInfoDTO;
import org.mapstruct.*;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserConvertor {
    UserDTO toDataTransferObj(UserDO userDO);

    UserDO toDataObject(UserDTO userDTO);

    UserInfoDO toUserInfoDO(RegisterCmd registerCmd);

    UserInfoDTO toUserInfoDTO(UserInfoDO userInfoDO);
}

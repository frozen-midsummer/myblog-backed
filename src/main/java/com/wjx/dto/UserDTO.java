package com.wjx.dto;

import com.wjx.common.dto.DTO;
import lombok.Data;

@Data
public class UserDTO extends DTO {
    private String username;
    private String password;
}

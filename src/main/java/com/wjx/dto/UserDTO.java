package com.wjx.dto;

import com.wjx.common.dto.DTO;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO extends DTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
}

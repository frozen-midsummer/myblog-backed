package com.wjx.dto;

import com.wjx.common.dto.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
public class UserDO extends DTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
}

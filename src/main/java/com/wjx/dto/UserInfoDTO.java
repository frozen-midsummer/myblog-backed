package com.wjx.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wjx.common.dto.DTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfoDTO extends DTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long id;
    private String username;
    private String sex;
    private LocalDate birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}

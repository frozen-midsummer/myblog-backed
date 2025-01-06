package com.wjx.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfoUpdateCmd {
    private String id;
    private String username;
    private String sex;
    private LocalDate birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}

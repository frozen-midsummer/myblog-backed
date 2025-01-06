package com.wjx.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class UserInfoUpdateCmd {
    private String id;
    private String username;
    private String sex;
    private ZonedDateTime birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}

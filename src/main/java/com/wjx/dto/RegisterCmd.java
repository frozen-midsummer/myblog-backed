package com.wjx.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterCmd {
    private String username;
    private String password;
    private String sex;
    private LocalDate birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}

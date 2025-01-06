package com.wjx.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class UserTaskCreateCmd {
    private ZonedDateTime deadline;
    private String description;
    private String alarm;
}

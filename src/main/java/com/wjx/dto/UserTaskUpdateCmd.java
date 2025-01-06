package com.wjx.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class UserTaskUpdateCmd {
    private String taskId;
    private ZonedDateTime deadline;
    private String description;
    private String alarm;
}

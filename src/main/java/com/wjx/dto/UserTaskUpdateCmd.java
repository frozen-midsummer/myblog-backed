package com.wjx.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserTaskUpdateCmd {
    private String taskId;
    private LocalDateTime deadline;
    private String description;
    private String alarm;
}

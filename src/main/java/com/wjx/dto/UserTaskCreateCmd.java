package com.wjx.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class UserTaskCreateCmd {
    private LocalDateTime deadline;
    private String description;
    private String alarm;
}

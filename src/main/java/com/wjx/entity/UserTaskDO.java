package com.wjx.entity;

import com.wjx.common.dto.DTO;
import lombok.Data;
import lombok.NonNull;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserTaskDO extends DTO {
    private int taskId;
    @NonNull
    private String username;
    @NonNull
    private LocalDateTime createdTime;
    @NonNull
    private LocalDateTime updatedTime;
    @NonNull
    private LocalDateTime deadline;
    @NonNull
    private String description;
    @NonNull
    private String alarm;
}

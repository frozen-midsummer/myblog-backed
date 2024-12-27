package com.wjx.entity;
import lombok.Data;
import java.util.Date;
@Data
public class UserTasks {
    private int taskId;
    private String username;
    private Date createdTime;
    private Date updatedTime;
    private Date deadline;
    private String description;
    private String alarm;
}

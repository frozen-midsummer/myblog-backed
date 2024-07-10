package com.wjx.entity;

import java.util.Date;

public class UserTasks {
    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    private String username;
    private Date createdTime;
    private Date updatedTime;
    private Date deadline;
    private String description;
    private String alarm;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        return "UserTasks{" +
                "taskId=" + taskId +
                ", username='" + username + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", alarm='" + alarm + '\'' +
                '}';
    }
}

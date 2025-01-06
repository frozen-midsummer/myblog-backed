package com.wjx.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("user_info")
public class UserInfoDO {
    @TableId
    private Long id;
    private String username;
    private String sex;
    private LocalDate birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}

package com.wjx;

import com.wjx.config.CustomUserDetailsService;
import com.wjx.dto.UserDto;
import com.wjx.entity.User;
import com.wjx.entity.UserTasks;
import com.wjx.mapper.UserMapper;
import com.wjx.mapper.UserTasksMapper;
import com.wjx.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = SpringbootMyblogApplication.class)
class SpringbootMyblogApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserTasksMapper userTasksMapper;
    @Autowired
    private UserService userService;
    @Test
    void findAllUsers() {
        System.out.println(userMapper.findAllUsers());
    }
    @Test
    void build(){
        User user = new User();
        user.setUsername("wjx");
        user.setPassword("12546");
        System.out.println(user.toString());
    }
    @Test
    void passwordEncoded(){
        System.out.println(passwordEncoder.encode("Wangjiaxuan12"));
    }
    @Test
    void findByUsername(){
        System.out.println(userMapper.findByUsername("admin"));
    }
    @Test
    void loadUserByUsername(){
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        System.out.println(userDetails);
    }
    @Test
    void insertUserTask(){
        UserTasks task = new UserTasks();
        task.setUsername("test1"); // 假设username需唯一
        task.setCreatedTime(new Date()); // 当前时间
        task.setUpdatedTime(new Date());
        task.setDeadline(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)); // 设置明天为截止日期
        task.setDescription("This is a fake task description.");
        task.setAlarm("REMINDER");
        boolean isInserted = userTasksMapper.insertUserTask(task) > 0;
        assertTrue(isInserted, "插入假数据失败");
    }
    @Test
    void findAllTasksByUserName(){
        System.out.println(userTasksMapper.findAllTasksByUserName("test1"));
    }
    @Test
    void register(){
        UserDto userDto = new UserDto();
        userDto.setUsername("test1");
        userDto.setPassword("123456");
        userService.register(userDto);
    }
    @Test
    void generateKey(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[64]; // 512 bits = 64 bytes
        secureRandom.nextBytes(keyBytes);
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated Base64 encoded 512-bit key: " + base64EncodedKey);
    }
}

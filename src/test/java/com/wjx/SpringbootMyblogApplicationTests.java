package com.wjx;

import com.wjx.dto.UserDTO;
import com.wjx.service.CustomUserDetailsService;
import com.wjx.database.dataobject.UserDO;
import com.wjx.database.dataobject.UserTaskDO;
import com.wjx.database.mapper.UserMapper;
import com.wjx.database.mapper.UserTasksMapper;
import com.wjx.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

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
    void build() {
        UserDO userDO = new UserDO();
        userDO.setUsername("wjx");
        userDO.setPassword("12546");
        System.out.println(userDO.toString());
    }

    @Test
    void passwordEncoded() {
        System.out.println(passwordEncoder.encode("Wangjiaxuan12"));
    }

    @Test
    void loadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        System.out.println(userDetails);
    }

    @Test
    void generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[64]; // 512 bits = 64 bytes
        secureRandom.nextBytes(keyBytes);
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated Base64 encoded 512-bit key: " + base64EncodedKey);
    }
}

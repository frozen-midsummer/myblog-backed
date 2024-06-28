package com.wjx;

import com.wjx.entity.User;
import com.wjx.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = SpringbootMyblogApplication.class)
class SpringbootMyblogApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

}

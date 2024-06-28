package com.wjx.mapper;

import com.wjx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAllUsers();
    User findByUsername(String username);
    void save(User user);
}

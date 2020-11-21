package com.luffy.mapper;

import com.luffy.model.User;

import java.util.List;

/**
 * @author luffy
 */
public interface UserMapper {

    int add(User user);

    int delete(String id);

    int update(User user);

    User selectById(String id);

    User selectByUserName(String userName);

    List<User> queryUser(User user);
}

package com.luffy.mapper;

import com.luffy.model.User;

/**
 * @author luffy
 */
public interface UserMapper {

    User selectByUserName(String userName);
}

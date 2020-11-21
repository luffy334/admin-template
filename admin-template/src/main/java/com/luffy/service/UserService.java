package com.luffy.service;

import bean.ResultBean;
import com.github.pagehelper.PageInfo;
import com.luffy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author luffy
 */
public interface UserService extends UserDetailsService {

    ResultBean<User> add(User user);

    ResultBean<Boolean> delete(String id);

    Boolean update(User user);

    PageInfo<User> query(int page, int pageSize, User user);
}

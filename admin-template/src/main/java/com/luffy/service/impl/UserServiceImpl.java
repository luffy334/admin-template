package com.luffy.service.impl;

import bean.ResultBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luffy.mapper.SysRoleMapper;
import com.luffy.mapper.UserMapper;
import com.luffy.model.SysRole;
import com.luffy.model.User;
import com.luffy.service.UserService;
import enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author luffy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public ResultBean<User> add(User user) {
        ResultBean<User> resultBean = new ResultBean<>(ResultCode.SUCCESS);
        if(userMapper.selectByUserName(user.getUserName()) != null) {
            return new ResultBean<>(ResultCode.ACCOUNT_REGISTERED);
        }
        user.setId(UUID.randomUUID().toString());
        user.setRegisterTime(new Date());
        user.setState(1);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.add(user);
        resultBean.setData(user);
        return resultBean;
    }

    @Override
    public ResultBean<Boolean> delete(String id) {
        ResultBean<Boolean> resultBean = new ResultBean<>(ResultCode.SUCCESS);
        User user = userMapper.selectById(id);
        if ("luffy".equals(user.getUserName())) {
            return new ResultBean<>(ResultCode.ACCOUNT_SUPPER);
        }
        resultBean.setData(userMapper.delete(id) > 0);
        return resultBean;
    }

    @Override
    public Boolean update(User user) {
        if(StringUtils.hasText(user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        return userMapper.update(user) > 0;
    }

    @Override
    public PageInfo<User> query(int page, int pageSize, User user) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(userMapper.queryUser(user));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", user.getUserName()));
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role: sysRoleMapper.queryRoleByUserId(user.getId())){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}

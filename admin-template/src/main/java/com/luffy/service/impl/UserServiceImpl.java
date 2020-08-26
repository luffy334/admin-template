package com.luffy.service.impl;

import com.luffy.mapper.UserMapper;
import com.luffy.model.User;
import com.luffy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luffy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", user.getUserName()));
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (SysRole role:userMapper.findRoleByUsername(s))
//        {
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
//        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}

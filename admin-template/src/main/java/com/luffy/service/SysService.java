package com.luffy.service;

import com.luffy.vo.Menu;

import java.util.List;

public interface SysService {

    List<Menu> queryUserMenu(String userName);
}

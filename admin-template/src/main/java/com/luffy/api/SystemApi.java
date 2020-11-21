package com.luffy.api;

import bean.ResultBean;
import com.luffy.service.SysService;
import com.luffy.vo.Menu;
import enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author luffy
 */
@RestController
@RequestMapping("system")
public class SystemApi {

    @Autowired
    private SysService sysService;

    @GetMapping("menu")
    public ResultBean<List<Menu>> queryMenu(Principal principal) {
        List<Menu> menus = sysService.queryUserMenu(principal.getName());
        ResultBean<List<Menu>> resultBean = new ResultBean<>(ResultCode.SUCCESS);
        resultBean.setData(menus);
        return resultBean;
    }
}

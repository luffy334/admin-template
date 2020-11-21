package com.luffy.api;

import bean.ResultBean;
import com.github.pagehelper.PageInfo;
import com.luffy.model.User;
import com.luffy.service.UserService;
import enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author luffy
 */
@RestController
@RequestMapping("user")
public class UserApi {

    @Autowired
    private UserService userService;

    @PutMapping
    public ResultBean<User> add(@RequestBody User user) {
         return userService.add(user);
    }

    @DeleteMapping("{id}")
    public ResultBean<Boolean> delete(@PathVariable String id) {
        return userService.delete(id);
    }

    @PostMapping
    public ResultBean<Boolean> update(@RequestBody User user) {
        ResultBean<Boolean> resultBean = new ResultBean<>(ResultCode.SUCCESS);
        resultBean.setData(userService.update(user));
        return resultBean;
    }

    @GetMapping
    public ResultBean<PageInfo<User>> query(@RequestParam int page, @RequestParam int pageSize, User user) {
        PageInfo<User> users = userService.query(page, pageSize, user);
        ResultBean<PageInfo<User>> resultBean = new ResultBean<>(ResultCode.SUCCESS);
        resultBean.setData(users);
        return resultBean;
    }
}

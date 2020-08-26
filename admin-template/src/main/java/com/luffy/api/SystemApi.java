package com.luffy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luffy
 */
@RestController
@RequestMapping("system")
public class SystemApi {

    @GetMapping("/")
    public String test() {
        return "Hello Wold";
    }
}

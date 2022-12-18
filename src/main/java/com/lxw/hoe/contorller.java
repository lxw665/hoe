package com.lxw.hoe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxw
 * @date 2022/12/18 17:56
 */

@RestController
public class contorller {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World !";
    }
}

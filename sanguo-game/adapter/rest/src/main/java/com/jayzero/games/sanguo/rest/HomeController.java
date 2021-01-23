package com.jayzero.games.sanguo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "welcome to sanguo world, have fun!";
    }
}

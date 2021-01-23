package com.jayzero.games.sanguo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HeroController
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
@RestController
@RequestMapping(ApiConstants.HERO_API_URL)
public class HeroController {

    @GetMapping("/{heroId}")
    public String get(@PathVariable String heroId) {
        return "a hero by " + heroId;
    }

}

package com.jayzero.games.sanguo.domain.hero.battle;

import com.jayzero.games.sanguo.domain.common.Player;
import com.jayzero.games.sanguo.domain.hero.Hero;

import java.util.List;

/**
 * HeroGroup
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
public interface HeroGroup {

    /**
     * 列出所有的英雄
     *
     * @return {@link Hero}
     */
    List<Hero> listAllHeroes();

    /**
     * 返回当前英雄是被哪个玩家控制
     *
     * @return {@link Player}
     */
    Player controlledBy();
}

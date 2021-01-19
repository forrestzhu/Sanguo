package com.jayzero.games.sanguo.hero.battle;

import com.jayzero.games.sanguo.core.Player;
import com.jayzero.games.sanguo.hero.Hero;

import java.util.List;

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

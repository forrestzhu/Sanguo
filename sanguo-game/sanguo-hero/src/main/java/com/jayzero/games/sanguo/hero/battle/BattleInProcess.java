package com.jayzero.games.sanguo.hero.battle;

public interface BattleInProcess {

    /**
     * 触发下一回合
     *
     * @return {@link BattleResult}
     */
    BattleResult nextRound();


}

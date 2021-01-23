package com.jayzero.games.sanguo.domain.hero.battle;

/**
 * BattleInProcess
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
public interface BattleInProcess {

    /**
     * 触发下一回合
     *
     * @return {@link BattleResult}
     */
    BattleResult nextRound();


}

package com.jayzero.games.sanguo.domain.hero.battle;

/**
 * Battle 武将对战
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
public class Battle {

    private OffenseSide offenseSide;

    private DefenseSide defenseSide;

    /**
     * start a battle
     *
     * @return {@link BattleInProcess}
     */
    public BattleInProcess start() {
        return null;
    }

    /**
     * estimate result, so the outcome is displayed instantly
     *
     * @return {@link BattleResult}
     */
    public BattleResult estimateResult() {
        return null;
    }

}

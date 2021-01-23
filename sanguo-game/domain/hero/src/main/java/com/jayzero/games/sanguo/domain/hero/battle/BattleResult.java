package com.jayzero.games.sanguo.domain.hero.battle;

import com.jayzero.games.sanguo.domain.common.Player;

/**
 * BattleResult
 * Battle的结果
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
public interface BattleResult {

    /**
     * 单挑是否结束
     *
     * @return true if battle has finished
     */
    boolean isFinished();

    /**
     * 告知 player 单挑的结果
     *
     * @param player {@link Player}
     * @return
     */
    BattleResultEnum getResult(Player player);
}

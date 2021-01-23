package com.jayzero.games.sanguo.domain.common;

/**
 * ApiConstants
 *
 * @author ForrestZhu
 * @date 2021/1/23
 */
public interface Player {

    /**
     * 获得玩家的角色, 可能是 AI / Human
     *
     * @return {@link Role}
     */
    Role getRole();

}

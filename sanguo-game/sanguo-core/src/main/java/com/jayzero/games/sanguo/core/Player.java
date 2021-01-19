package com.jayzero.games.sanguo.core;

public interface Player {

    /**
     * 获得玩家的角色, 可能是 AI / Human
     *
     * @return {@link Role}
     */
    Role getRole();

}

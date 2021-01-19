package com.jayzero.games.sanguo.core.person;

import java.util.List;

public class Person {

    /**
     * 姓氏, 如关羽 姓 关, 曹操 姓 曹
     * <p>
     * GuanYu's family name is Guan, ZhaoYun's family name is Zhao
     */
    private FamilyName familyName;

    /**
     * 名, 如关羽 名 羽, 曹操 名 操
     * <p>
     * GuanYu‘s given name is Yu, LiuBei's given name is Bei
     */
    private GivenName givenName;

    /**
     * 字， 如关羽 字 云长, 刘备 字 玄德, 曹操 字 孟德
     * <p>
     * GuanYu's style name is Yunchang, Caocao 's style name is Mengde
     */
    private StyleName styleName;

    /**
     * 号, 三国时期鲜有有号的, 以后古武将的加入可能会有号, 如
     * 李白 号 青莲居士
     * LiBai's pseudoName is Lotus Jushi
     */
    private PseudoName pseudoName;

    /**
     * 昵称, 通常的称呼, 如 李白 昵称 诗仙, 关羽 昵称 关公, 关二爷, 刘备 昵称 皇叔, 大耳 等
     */
    private List<NickName> nickNames;

}

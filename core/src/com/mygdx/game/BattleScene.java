package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;

//This is just a restruction of BattleMap, but didn't want to touch that class :)
public class BattleScene {

    private int x1, y1, x2, y2;
    Texture bg;
    BattleMenu menu;
    HpBar hpBar1, hpBar2;
    Trainer player, opponent;
}

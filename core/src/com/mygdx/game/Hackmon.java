package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hackmon {

    private Texture sprite;
    private int hp,currHP,str,def,speed;

    Hackmon(){
        hp = 100;
        currHP=90;
        str = 50;
        def = 10;
        speed = 100;
        sprite = new Texture(
"/home/tines/IdeaProjects/Hackmons/core/assets/exploudback.png"
        );
    }

    public void setToFront(){
        sprite = new Texture(
"/home/tines/IdeaProjects/Hackmons/core/assets/exploudfront.png"
        );
    }

    public void render(SpriteBatch batch,int x, int y){
        batch.draw(sprite,x,y);
    }

    public void takeDamage(int dmg){
        currHP -= dmg;
    }

    public int getCurrHP() {
        return currHP;
    }

    public int getDef() {
        return def;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStr() {
        return str;
    }

    public boolean isFainted(){
        return currHP<=0;
    }
}

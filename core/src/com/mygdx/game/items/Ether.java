package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Hackmon;

public class Ether implements IConsumable{

    private String name;
    private int healAmount;
    private Texture sprite;
    private String desc;

    public Ether(String name, int healAmount){
         this.name = name;
         this.healAmount = healAmount;
         desc = "Restores Hackmon SP by "+healAmount+".";
         sprite = new Texture("core/assets/items/ether.png");
    }

    @Override
    public void useItem(Hackmon applyTo) {
        applyTo.restoreStam(healAmount);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void render(SpriteBatch batch, int x, int y, int w, int h) {
        batch.draw(sprite,x,y,w,h);
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

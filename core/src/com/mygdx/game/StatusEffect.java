package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public enum StatusEffect {
    NONE, BURN, FEAR, FROZEN, PARALYZED, POISON, SLEEP, TOXIC;

    public static Texture getSprite(StatusEffect statusEffect){
        switch (statusEffect){
            case SLEEP:
                return new Texture("core/assets/status/sleeping.png");
            case BURN:
                return new Texture("core/assets/status/burn.png");
            case FEAR:
                return new Texture("core/assets/status/feared.png");
            case TOXIC:
                return new Texture("core/assets/status/sleeping.png");
            case FROZEN:
                return new Texture("core/assets/status/frozen.png");
            case POISON:
                return new Texture("core/assets/status/poisoned.png");
            case PARALYZED:
                return new Texture("core/assets/status/paralyzed.png");
            default:
                return new Texture("core/assets/status/sleeping.png");
        }
    }
}

package com.mygdx.game.adventure;

public class PosCell {

    private boolean isSolid = false;
    private boolean isSurfable = false;

    public PosCell(char type) {
        switch(type) {
            case '#':
                setSolid();
                break;
            case 'W':
                setSurfable();
                break;
            default:
                break;
        }
    }

    private void setSolid() {
        isSolid = true;
    }

    private void setSurfable() {
        isSurfable = true;
    }
}

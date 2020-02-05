package com.mygdx.game.adventure;

public class PosCell {

    private boolean hasPlayer = false;
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

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public void setPlayer() {
        hasPlayer = true;
    }

    public void removePlayer() {
        hasPlayer = false;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid() {
        isSolid = true;
    }

    public boolean isSurfable() {
        return isSurfable;
    }

    public void setSurfable() {
        isSurfable = true;
    }
}

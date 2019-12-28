package com.mygdx.game;

import java.util.ArrayList;

public class BackPack {
    private ArrayList<IItem> data;
    private int size;

    public BackPack(int size){
        this.size = size;
        data = new ArrayList<IItem>();
    }

    public boolean addItem(IItem item){
        if (data.size()==size) return false;
        data.add(item);
        return true;
    }

    public IItem removeItem(IItem item){
        if (data.contains(item)){
            data.remove(item);
            return item;
        }
        else
            return null;
    }

    public ArrayList<IItem> getContents() {
        return data;
    }
}

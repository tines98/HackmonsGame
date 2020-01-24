package com.mygdx.game.items;

import com.mygdx.game.items.IItem;

import java.util.ArrayList;

public class BackPack {
    private ArrayList<IItem> data;

    public BackPack(){
        data = new ArrayList<IItem>();
    }

    public void addItem(IItem item){
        data.add(item);
    }

    public IItem removeItem(IItem item){
        if (data.contains(item)){
            data.remove(item);
            return item;
        }
        else
            return null;
    }

    public IItem getItem(int i){
        return data.get(i);
    }

    public IItem popItem(int i){
        return data.remove(i);
    }

    public ArrayList<IItem> getContents() {
        return data;
    }
}

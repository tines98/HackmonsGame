package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class Trainer {

    private int id;
    private String name;

    private ArrayList<Hackmon> hackmons = new ArrayList();
    private Random random = new Random();

    public Trainer(String name) {
        this.id = random.nextInt(65536);
        this.name = name;
    }

    public Trainer(String name, Hackmon [] mons) {
        this.id = random.nextInt(65536);
        this.name = name;
        for (Hackmon mon : mons) {
            hackmons.add(mon);
        }
    }

    private void updateName(String newName) {
        this.name = newName;
    }

    private Hackmon getSelected() {
        return hackmons.get(0);
    }

    private Hackmon removeMon(int i) {
        return hackmons.remove(i);
    }

    private boolean addMon(Hackmon mon) {
        if (hackmons.size() < 6) {
            hackmons.add(mon);
            return true;
        }
        else {
            return false;
        }
    }
}

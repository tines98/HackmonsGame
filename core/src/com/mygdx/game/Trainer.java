package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

public class Trainer {

    private int id;
    private String name;

    private ArrayList<Hackmon> party = new ArrayList();
    private Random random = new Random();

    public Trainer(String name) {
        this.id = random.nextInt(65536);
        this.name = name;
    }

    public Trainer(String name, Hackmon [] mons) {
        this.id = random.nextInt(65536);
        this.name = name;
        for (Hackmon mon : mons) {
            party.add(mon);
        }
    }

    private void updateName(String newName) {
        this.name = newName;
    }

    public Hackmon getSelected() {
        return party.get(0);
    }

    public ArrayList<Hackmon> getParty() {
        return party;
    }

    public Hackmon getMon(int n) {
        return party.get(n);
    }

    public void switchMon(int n) {
        Hackmon temp = party.get(0);
        party.set(0, party.get(n));
        party.set(n, temp);
    }

    public int nextMon() {
        for (int i = 1; i < party.size(); i++) {
            if (!party.get(i).isFainted()) {
                return i;
            }
        }
        Gdx.app.exit();
        return 0;
    }

    public Hackmon removeMon(int i) {
        return party.remove(i);
    }

    public boolean addMon(Hackmon mon) {
        if (party.size() < 6) {
            party.add(mon);
            return true;
        }
        else {
            return false;
        }
    }
}

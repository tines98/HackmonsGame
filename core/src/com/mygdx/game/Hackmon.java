package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hackmon {

    private Texture sprite;
    private int baseHP, baseSTAM, baseATK, baseDEF, baseSPATK, baseSPDEF, baseSPEED;
    private int id, lv;
    private int hp, stam;
    private int maxHp, maxStam, atk, def, spAtk, spDef, speed;
    private String name, type1, type2;

    public Hackmon(int id, int lv) {
        this.id = id;
        this.lv = lv;
        initialize(id);
        sprite = new Texture(
                "core/assets/exploudback.png"
        );
        hp = maxHp;
    }

    private void initialize(Integer n) {
        try {
            File pokemonList = new File("core/assets/data/pokemon.txt");
            Scanner sc = new Scanner(pokemonList);
            String id = "[" + n + "]";
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                if (data.equals(id)) {
                    String nameLine = sc.nextLine();
                    String typeLine = sc.nextLine();
                    String statLine = sc.nextLine();

                    String [] nameList = nameLine.split("=");
                    this.name = nameList[1];

                    String [] typeList = typeLine.split("=|,");
                    if (typeList.length == 3) {
                        this.type2 = typeList[2];
                    }

                    this.type1 = typeList[1];
                    String [] statList = statLine.split("=|,");
                    baseHP = Integer.parseInt(statList[0]);
                    baseSTAM = Integer.parseInt(statList[1]);
                    baseATK = Integer.parseInt(statList[2]);
                    baseDEF = Integer.parseInt(statList[3]);
                    baseSPATK = Integer.parseInt(statList[4]);
                    baseSPDEF = Integer.parseInt(statList[5]);
                    baseSPEED = Integer.parseInt(statList[6]);
                    updateStats();
                    break;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: Initialization failed");
            e.printStackTrace();
        }
    }

    public void updateStats() {
        maxHp = ((2 * baseHP * lv) / 100) + lv + 10;
        maxStam = ((baseSTAM + lv) / 10) + lv;
        atk = ((2 * baseATK * lv) / 100) + 10;
        def = ((2 * baseDEF * lv) / 100) + 10;
        spAtk = ((2 * baseSPATK * lv) / 100) + 10;
        spDef = ((2 * baseSPDEF * lv) / 100) + 10;
        speed = ((2 * baseSPEED * lv) / 100) + 10;
    }

    public void setToFront(){
        sprite = new Texture(
"core/assets/exploudfront.png"
        );
    }

    public void render(SpriteBatch batch,int x, int y){
        batch.draw(sprite,x,y);
    }

    public void takeDamage(int dmg){
        hp -= dmg;
    }

    public int getCurrHP() {
        return hp;
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
        return atk;
    }

    public boolean isFainted(){
        return hp<=0;
    }
}

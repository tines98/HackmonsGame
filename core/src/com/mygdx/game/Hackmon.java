package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Hackmon {

    private Texture sprite;
    private int baseHP, baseSTAM, baseSTR, baseDEF, baseWILL, baseRES, baseSPEED;
    private int id, lv, exp;
    private int currHP, currStam;
    private int maxHP, maxStam, str, def, will, res, speed, crit;
    private String name, type1, type2, spriteName;

    private Move [] moves = new Move [4];
    private Move [] levelMoves = new Move [100];

    public Hackmon(int id, int lv) {
        this.id = id;
        this.lv = lv;
        this.exp = (int)Math.pow(lv,3);
        initialize(id);
        updateStats();
        initialMoves();
        spriteName = name.toLowerCase();
        sprite = new Texture(
                //TODO For some reason heracrossback.png is not renderable
                //TODO and from what I've researched it's due to not being 8-bit exported... wtf?
                "core/assets/hackmons/"+spriteName+"back.png"
        );
        currHP = maxHP;
        currStam = maxStam;
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
                    String levelMoveLine = sc.nextLine();

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
                    baseSTR = Integer.parseInt(statList[2]);
                    baseDEF = Integer.parseInt(statList[3]);
                    baseWILL = Integer.parseInt(statList[4]);
                    baseRES = Integer.parseInt(statList[5]);
                    baseSPEED = Integer.parseInt(statList[6]);
                    crit = Integer.parseInt(statList[7]);

                    String [] levelMoveList = levelMoveLine.split("=|,");
                    for (int i = 1; i < levelMoveList.length; i += 2) {
                        //LEARNSET MUST BE DEFINED WITH INTERNAL NAMES FOR THIS TO WORK
                        levelMoves[Integer.parseInt(levelMoveList[i])] = new Move(levelMoveList[i+1]);
                    }
                    break;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: Initialization failed");
            e.printStackTrace();
        }
    }

    public void initialMoves() {
        for(int i=1; i < levelMoves.length; i++) {
            if (i <= this.lv) {
                if (levelMoves[i] != null) {
                    for (int j=0; j < 4; j++) {
                        if (moves[j] == null) {
                            moves[j] = levelMoves[i];
                            break;
                        }
                    }
                }
            }
            else {
                break;
            }
        }
    }

    // lv^3 er formula for medium fast leveling i pokemon
    public void receiveExp(int gain) {
        exp += gain;
        if (exp > (Math.pow(lv+1,3))) {
            lv++;
            updateStats();
            receiveExp(0);
        }
    }

    public void updateStats() {
        maxHP = ((2 * baseHP * lv) / 100) + lv + 10;
        maxStam = ((baseSTAM + lv) / 10) + lv;
        str = ((2 * baseSTR * lv) / 100) + 10;
        def = ((2 * baseDEF * lv) / 100) + 10;
        will = ((2 * baseWILL * lv) / 100) + 10;
        res = ((2 * baseRES * lv) / 100) + 10;
        speed = ((2 * baseSPEED * lv) / 100) + 10;
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public Move[] getMoves() {
        return moves;
    }

    public void setToFront(){
        String spriteName = name.toLowerCase();
        sprite = new Texture(
"core/assets/hackmons/" + spriteName + "front.png"
        );
    }

    public void setToBack(){
        String spriteName = name.toLowerCase();
        sprite = new Texture(
"core/assets/hackmons/" + spriteName + "back.png"
        );
    }

    public void render(SpriteBatch batch, int x, int y){
        batch.draw(sprite,x,y);
    }

    public void render(SpriteBatch batch,int x, int y, float scale){
        batch.draw(sprite,x,y,96*scale,96*scale);
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    /**
     * Heals specified health but doesn't exceed maxHP
     * @param healedHP amount you want to heal
     */
    public void restoreHp(int healedHP) {
        if (currHP + healedHP < maxHP) {
            currHP += healedHP;
        }
        else {
            currHP = maxHP;
        }
    }

    public void restoreStam(int restoredStam) {
        if (currStam + restoredStam < maxStam) {
            currStam += restoredStam;
        }
        else {
            currStam = maxStam;
        }
    }

    public void takeDamage(int dmg){
        if (currHP - dmg > 0) {
            currHP -= dmg;
        }
        else {
            currHP = 0;
        }
    }

    public void useStam(int cost) {
        if (currStam - cost > 0) {
            currStam -= cost;
        }
        else {
            currStam = 0;
        }
    }

    public int getCurrHp() {
        return this.currHP;
    }

    public int getCurrStam() {
        return this.currStam;
    }

    public int getCrit() {
        return this.crit;
    }

    public int getDef() {
        return this.def;
    }

    public int getHp() {
        return this.maxHP;
    }

    public int getLv() {
        return this.lv;
    }

    public int getExp() {
        return exp;
    }

    public String getName() {
        return this.name;
    }

    public int getRes() { return res; }

    public int getSpeed() {
        return this.speed;
    }

    public int getStam() {
        return this.maxStam;
    }

    public int getStr() {
        return this.str;
    }

    public int getWill() {
        return this.will;
    }

    public boolean isFainted() {
        return currHP <= 0;
    }
}

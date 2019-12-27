package com.mygdx.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Move {
    int id, power, cost, accuarcy, effectAccuarcy, priority;
    String internalName, displayName, functionCode, type, category;

    public Move(int id) {
        this.id = id;
        initialize(id);
    }

    public Move(String name) {
        this.internalName = name;
        //TODO Make this constructor able to find the correct move without reading the file twice :)
    }

    private void initialize(int n) {
        try {
            File moveList = new File("core/assets/data/moves.txt");
            Scanner sc = new Scanner(moveList);
            char id = (char) (n + 48);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                if (data.charAt(0) == id) {
                    String [] dataList = data.split(",");
                    internalName = dataList[1];
                    displayName = dataList[2];
                    functionCode = dataList[3];
                    type = dataList[4];
                    power = Integer.parseInt(dataList[5]);
                    cost = Integer.parseInt(dataList[6]);
                    category = dataList[7];
                    accuarcy = Integer.parseInt(dataList[8]);
                    effectAccuarcy = Integer.parseInt(dataList[9]);
                    priority = Integer.parseInt(dataList[10]);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: Initialization failed");
            e.printStackTrace();
        }
    }

    public int getAccuarcy() {
        return this.accuarcy;
    }

    public String getCategory() {
        return this.category;
    }

    public int getCost() {
        return this.cost;
    }

    public int getEffectAccuarcy() {
        return this.effectAccuarcy;
    }

    public String getFunctionCode() {
        return this.functionCode;
    }

    public String getName() {
        return this.displayName;
    }

    public int getPower() {
        return this.power;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getType() {
        return this.type;
    }

    public void output() {
        System.out.println("Name: " + getName());
        System.out.println("Power: " + getPower());
        System.out.println("Cost: " + getCost());
        System.out.println("Function Code: " + getFunctionCode());
    }

    public static void main(String[] args) {
        Move uhoh = new Move(1);
        uhoh.output();
    }
}


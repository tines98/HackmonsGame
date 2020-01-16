package com.mygdx.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Move {
    private int id, power, cost, accuarcy, effectAccuarcy, priority;
    private String internalName, displayName, functionCode, type, category;

    public Move(int id) {
        initialize(id);
    }

    public Move(String name) {
        initialize(name);
    }

    public Move(String [] moveData) {
        setValues(moveData);
    }

    private void initialize(int n) {
        try {
            File moveList = new File("core/assets/data/moves.txt");
            Scanner sc = new Scanner(moveList);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String [] dataList = data.split(",");
                if (Integer.parseInt(dataList[0]) == n) {
                    setValues(dataList);
                    break;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: Initialization failed");
            e.printStackTrace();
        }
    }

    private void initialize(String name) {
        try {
            File moveList = new File("core/assets/data/moves.txt");
            Scanner sc = new Scanner(moveList);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String [] dataList = data.split(",");
                if (name.equals(dataList[1])){
                    setValues(dataList);
                    break;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: Initialization failed");
            e.printStackTrace();
        }
    }

    /*
    Places the data stored in the array from the file into
    the correct variables.
     */
    public void setValues(String [] dataList) {
        id = Integer.parseInt(dataList[0]);
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
}


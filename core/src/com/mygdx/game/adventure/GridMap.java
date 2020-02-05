package com.mygdx.game.adventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Hackmon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GridMap {

    private int playerX, playerY;

    private PosCell [][] tiles;
    private String name;
    private Texture sprite;

    private ArrayList<Hackmon> normalEncounters;

    public GridMap (String name) {
        this.name = name;
        initialize();
    }

    private void initialize() {
        sprite = new Texture("core/assets/maps/" + name.toLowerCase() + ".png");
        try {
            File mapList = new File("core/assets/maps/maplist.txt");
            Scanner sc = new Scanner(mapList);
            String id = "[" + name + "]";
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                if (data.equals(id)) {
                    String routeName = sc.nextLine();
                    String [] routeNameList = routeName.split("=");

                    String normalEncountersData = sc.nextLine();
                    String [] normalEncountersList = normalEncountersData.split("=|,");

                    File map = new File("core/assets/maps/" + routeNameList[1] + ".txt");
                    Scanner mapsc = new Scanner(map);
                    while(mapsc.hasNextLine()) {
                        String dimensions = mapsc.nextLine();
                        String [] dimensionsList = dimensions.split(",");
                        System.out.println(Arrays.toString(dimensionsList));
                        tiles = new PosCell[Integer.parseInt(dimensionsList[1])] [Integer.parseInt(dimensionsList[0])];

                        for (int y = tiles.length - 1; y >= 0; y--) {
                            String line = mapsc.nextLine();
                            for (int x = 0; x < tiles[0].length; x++) {
                                tiles[y][x] = new PosCell(line.charAt(x));
                            }
                        }
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

    public void render(SpriteBatch batch) {
        batch.draw(sprite, 0, 0);
    }

    private void printMap() {
        for (int i = 0; i < tiles.length; i++) {
            System.out.println(Arrays.toString(tiles[i]));
        }
    }

    public static void main(String[] args) {
        GridMap mapu = new GridMap("Route1");
        mapu.printMap();
    }

    public void setPlayerPosition(int x, int y) {
        playerX = x;
        playerY = y;
    }

    public boolean canGo(AnimationState state) {
        switch (state) {
            case WALK_LEFT:
                if (!tiles[playerY][playerX-1].isSolid()) {

                    playerX--;
                    return true;
                }
                break;
            case WALK_RIGHT:
                if (!tiles[playerY][playerX+1].isSolid()) {
                    playerX++;
                    return true;
                }
                break;
            case WALK_UP:
                if (!tiles[playerY+1][playerX].isSolid()) {
                    playerY++;
                    return true;
                }
                break;
            case WALK_DOWN:
                if (!tiles[playerY-1][playerX].isSolid()) {
                    playerY--;
                    return true;
                }
                break;
            default:
                return true;
        }
        return false;
    }
}

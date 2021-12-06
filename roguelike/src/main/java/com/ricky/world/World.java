package com.ricky.world;

import java.lang.Math;
import com.ricky.maze.*;

public class World {
    
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;

    private Tile<Thing>[][] tiles;

    public World() {
        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        int dim = Math.min(WIDTH, HEIGHT);
        MazeGenerator mg = new MazeGenerator(dim);
        mg.generateMaze();
        int[][] maze = mg.getArrayMaze();
        

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setThing(maze[i][j] == 1 ? new Floor(this) : new Wall(this));
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getthing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public boolean validMove(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && this.get(x, y) instanceof Floor;
    }

    // TODO: 判断给定地点放置物类型
    public int checkPos(int x, int y) {
        Thing t = this.get(x, y);
        
        return 0;
    }

    // TODO: 从地图上清除给定物体
    public void removeThing(Thing t) {
        int x = t.getX();
        int y = t.getY();
        this.tiles[x][y].setThing(new Floor(this));
    }

}
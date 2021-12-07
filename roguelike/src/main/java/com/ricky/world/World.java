package com.ricky.world;

import java.lang.Math;

import java.awt.Color;

import com.ricky.maze.*;

public class World {
    
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;

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
        if(x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
            return new Thing(new Color(0, 0, 0), (char)0, this);
        return this.tiles[x][y].getthing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public boolean validMove(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && this.get(x, y) instanceof Floor;
    }

    public boolean inBound(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

}
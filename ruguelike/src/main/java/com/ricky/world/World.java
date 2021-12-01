package com.ricky.world;

import java.lang.Math;

public class World {
    
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;

    private Tile<Thing>[][] tiles;

    public World() {
        
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

}

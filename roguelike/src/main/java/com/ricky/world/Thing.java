package com.ricky.world;

import java.awt.Color;

public class Thing {
    
    protected World world;

    public Tile<? extends Thing> tile;

    private final Color color;
    private final char glyph;

    //上下左右
    protected static final int[][] dirs = 
        {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int getX() {
        return this.tile.getxPos();
    }

    public int getY() {
        return this.tile.getyPos();
    }

    public void setTile(Tile<? extends Thing> tile) {
        this.tile = tile;
    }

    Thing(Color color, char glyph, World world) {
        this.color = color;
        this.glyph = glyph;
        this.world = world;
    }

    public Color getColor() {
        return this.color;
    }

    public char getGlyph() {
        return this.glyph;
    }
}
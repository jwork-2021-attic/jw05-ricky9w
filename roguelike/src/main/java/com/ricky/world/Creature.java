package com.ricky.world;

import java.awt.Color;

import com.ricky.screen.PlayScreen;

public abstract class Creature extends Thing implements Runnable {

    protected final int maxHP;
    protected int power;
    protected int hp;
    protected PlayScreen screen;
    
    Creature(Color color, char glyph, World world, PlayScreen screen) {
        super(color, glyph, world);
        this.maxHP = 5;
        this.hp = this.maxHP;
        this.screen = screen;
    }

    public void moveTo(int xPos, int yPos) {
        this.world.put(this, xPos, yPos);
    }

    public abstract void run();

    public int getPower() {
        return this.power;
    }

    public int getHP() {
        return this.hp;
    }

    public void beAttacked(int damage) {
        this.hp -= damage;
    }
}

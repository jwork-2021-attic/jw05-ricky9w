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
        this.maxHP = 1;
        this.hp = this.maxHP;
        this.screen = screen;
        this.power = 1;
    }

    public void moveTo(int xPos, int yPos) {
        this.world.put(new Floor(world), this.getX(), this.getY());
        this.world.put(this, xPos, yPos);
    }

    public abstract void run();

    public int getPower() {
        return this.power;
    }

    public int getHP() {
        return this.hp;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public synchronized void move(int dir) {
        if(dir < 0 || dir > 4)
            return;
        int xTar = this.getX() + Thing.dirs[dir][0];
        int yTar = this.getY() + Thing.dirs[dir][1];
        if(!world.inBound(xTar, yTar))
            return;
        Thing t = world.get(xTar, yTar);
        if(t instanceof Floor) { //地面, 可以移动
            this.moveTo(xTar, yTar);
            return;
        } else {
            System.out.println(String.format("cannot move to (%d, %d)", xTar, yTar));
            return;
        }
    }

    public synchronized void beAttacked(int damage) {
        System.out.println(String.format("%s attacked of %d", this, damage));
        this.hp -= damage;
    }

}

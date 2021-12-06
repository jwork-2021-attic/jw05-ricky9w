package com.ricky.world;

import java.awt.Color;

import com.ricky.world.Creature;
import com.ricky.world.Monster;
import com.ricky.screen.PlayScreen;

public class Bullet extends Thing {

    private PlayScreen screen;
    private int dir;
    private int damage;
    private Creature parent;
    
    
    public Bullet(Color color, World world, PlayScreen screen, int dir, Creature creature) {
        super(color, (char)7, world);
        this.screen = screen;
        this.dir = dir;
        this.parent = creature;
        this.damage = creature.getPower();
    }

    public void moveTo(int xTar, int yTar) {
        int xPrev = this.getX();
        int yPrev = this.getY();
        world.put(this, xTar, yTar);
        world.put(new Floor(world), xPrev, yPrev);
    }

    public void action() {
        int xTar = this.getX() + Thing.dirs[dir][0];
        int yTar = this.getY() + Thing.dirs[dir][1];
        // TODO:判断子弹下一步目标地点具体情况

    }
    
}

package com.ricky.world;

import java.awt.Color;
import java.lang.annotation.Target;

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

    public synchronized void action() {
        int xTar = this.getX() + Thing.dirs[dir][0];
        int yTar = this.getY() + Thing.dirs[dir][1];
        if(!world.inBound(xTar, yTar)){
            // System.out.println(String.format("bullet out of bound: %d,%d", xTar, yTar));
            screen.deleteBullet(this);
            return;
        }
        // 判断子弹下一步目标地点具体情况
        Thing t = world.get(xTar, yTar);
        if(t instanceof Creature) {
            // 对生物造成伤害同时自身消亡
            Creature c = (Creature) t;
            if(c != this.parent) {
                c.beAttacked(this.damage);
            }
            screen.deleteBullet(this);
        } else if(t instanceof Bullet) {
            Bullet b = (Bullet) t;
            screen.deleteBullet(b);
            screen.deleteBullet(this);
        } else if(t instanceof Prop) {
            Prop p = (Prop) t;
            screen.deleteProp(p);
            screen.deleteBullet(this);
        } else if(t instanceof Floor) {
            this.moveTo(xTar, yTar);
        } else if(t instanceof Wall) {
            screen.deleteBullet(this);
        } else {
            System.out.println("???");
        }
    }

    public int getDamage() {
        return this.damage;
    }

    public Creature getParent() {
        return this.parent;
    }
    
}

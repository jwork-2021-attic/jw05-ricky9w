package com.ricky.world;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Caret;

import com.ricky.world.Creature;
import com.ricky.world.Monster;
import com.ricky.screen.PlayScreen;

public class Monster extends Creature {

    
    
    public Monster(Color color, World world, PlayScreen screen) {
        super(color, (char)2, world, screen);
        
    }

    @Override
    public void run() {
        try {
            while(hp > 0) {
                TimeUnit.MILLISECONDS.sleep(500);
                action();
            }
        } catch(InterruptedException e) {
            System.out.println("Error!");
        } finally {
            // 怪物死亡后掉落道具
            screen.deleteMonster(this);
			Random r = new Random();
			int chance = r.nextInt(4);
			int x = getX();
			int y = getY();
        }
    }

    void action() {
        Random r = new Random();
        int n = r.nextInt(5);
        if(n == 4)
            attack();
        else
            move(n);
    }

    public synchronized void move(int dir) {
        if(dir < 0 || dir > 4)
            return;
        int xTar = this.getX() + Thing.dirs[dir][0];
        int yTar = this.getY() + Thing.dirs[dir][1];
        if(!world.inBound(xTar, yTar))
            return;
        // 查看当前目标位置上的放置物种类
        Thing t = world.get(xTar, yTar);
        if(t instanceof Floor) { // 地面, 正常移动
            this.moveTo(xTar, yTar);
        } else if(t instanceof Bullet) { // 子弹, 受到伤害

            return;
        } else if(t instanceof Player) { // 玩家, 不移动
            return;
        } else { // 其他情况, 不移动

        }
        return;
    }

    public synchronized void attack() {
        Random r = new Random();
        int n = r.nextInt(4);
        int xTar = this.getX() + Thing.dirs[n][0];
        int yTar = this.getY() + Thing.dirs[n][1];
        if(!world.inBound(xTar, yTar))
            return;
        Thing t = world.get(xTar, yTar);
        // monster 攻击逻辑
        if(t instanceof Creature) {
            Creature c = (Creature) t;
            c.beAttacked(this.power);
        } else if(t instanceof Floor) {
            Bullet b = new Bullet(new Color(255, 0, 0), world, screen, n, this);
            world.put(b, xTar, yTar);
            screen.addBullet(b);
        } else if(t instanceof Prop) {
            Prop p = (Prop) t;
            screen.deleteProp(p);
        }
    }
}

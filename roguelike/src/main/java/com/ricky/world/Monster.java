package com.ricky.world;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        // TODO: 查看当前目标位置上的放置物种类
        Thing t = world.get(xTar, yTar);
        if(t instanceof Floor) { // 地面, 正常移动
            
        } else if(t instanceof Bullet) { // 子弹, 受到伤害

        } else if(t instanceof Player) { // 玩家, 不移动

        } else { // 其他情况, 不移动

        }
    }

    public synchronized void attack() {
        Random r = new Random();
        int n = r.nextInt(4);
        int xTar = this.getX() + Thing.dirs[n][0];
        int yTar = this.getY() + Thing.dirs[n][1];
        Thing t = world.get(xTar, yTar);
        if(t instanceof Creature) {
            
        }
    }
}

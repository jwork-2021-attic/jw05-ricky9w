package com.ricky.world;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import com.ricky.screen.PlayScreen;

public class Player extends Creature {

    private int bombs;
    private int dir;
    private long lastAttack = -1;
    private int ATTACK_CD = 100;
    
    public Player(Color color, World world, PlayScreen screen){
        super(color, (char) 2, world, screen);
        this.dir = 0;
    }
    
    @Override
    public void run() {
        try {
            while(hp > 0) {
                TimeUnit.MILLISECONDS.sleep(10);
                /*
                if(hp <= 0)
                    // 游戏结束
                    screen.loseGame();
                    break;
                */
            }
        } catch(InterruptedException e) {
            System.out.println("Player thread interrupted!");
        }
    }

    public int getBombs() {
        return this.bombs;
    }

    public synchronized void move(int dir) {
        if(dir < 0 || dir > 4)
            return;
        this.dir = dir;
        int xTar = this.getX() + Thing.dirs[dir][0];
        int yTar = this.getY() + Thing.dirs[dir][1];
        if(!world.inBound(xTar, yTar))
            return;
        Thing t = world.get(xTar, yTar);
        if(t instanceof Bullet) {
            Bullet b = (Bullet) t;
            this.beAttacked(b.getDamage());
            // 子弹消亡
            screen.deleteBullet(b);
        } else if(t instanceof BombProp) {
            // TODO: 移动到道具位置
            this.gainBomb();
            Prop p = (Prop) t;
            screen.deleteProp(p);
        } else if(t instanceof HPProp) {
            this.gainHP();
            Prop p = (Prop) t;
            screen.deleteProp(p);
        }
         else if(t instanceof Floor) {
            this.moveTo(xTar, yTar);
        }
        else if(t instanceof Player || t instanceof Monster) {
            return;
        }
    }

    public synchronized void attack() {
        if(lastAttack == -1)
            lastAttack = System.currentTimeMillis();
        else {
            long now = System.currentTimeMillis();
            if(now - lastAttack < ATTACK_CD)
                return;
            else {
                lastAttack = now;
            }
        }
        int xTar = this.getX() + Thing.dirs[dir][0];
        int yTar = this.getY() + Thing.dirs[dir][1];
        if(!world.inBound(xTar, yTar))
            return;
        Thing t = world.get(xTar, yTar);
        if(t instanceof Floor) {
            // 地面, 生成子弹
            Bullet b = new Bullet(new Color(255, 0, 0), world, screen, this.dir, this);
            world.put(b, xTar, yTar);
            screen.addBullet(b);
        } else if(t instanceof Creature) {
            // 生物, 造成伤害
            Creature c = (Creature) t;
            c.beAttacked(this.power);
        } else if(t instanceof Bullet) {
            // 子弹, 销毁
            Bullet b = (Bullet) t;
            screen.deleteBullet(b);
        } else if(t instanceof Prop) {
            // 道具, 销毁
            Prop p = (Prop) t;
            screen.deleteProp(p);
        }
    }
    
    private void gainBomb() {
        this.bombs++;
    }

    private void gainHP() {
        if(this.hp < maxHP) 
            this.hp++;
        else
            System.out.println("max HP!");
    }

    private void gainPower() {
        this.power++;
    }

    public void setBomb() {
        int xTar = this.getX() + Thing.dirs[dir][0];
        int yTar = this.getY() + Thing.dirs[dir][1];
        Thing t = world.get(xTar, yTar);
        if(!world.inBound(xTar, yTar))
            return;
        if(t instanceof Floor) {
            // 地面, 放置炸弹
            Bomb b = new Bomb(this.getColor(), world, screen, this, System.currentTimeMillis());
            world.put(b, xTar, yTar);
            screen.addBomb(b);
        }
    }

}

package com.ricky.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.ricky.world.Player;
import com.ricky.world.Bullet;
import com.ricky.world.Floor;
import com.ricky.world.Monster;
import com.ricky.world.World;
import com.ricky.world.Thing;
import com.ricky.world.Prop;
import com.ricky.world.Bomb;

import com.ricky.asciiPanel.AsciiPanel;
import com.ricky.control.GameControl;

public class PlayScreen implements Screen {
    
    private World world;
    private Player player;

    private GameControl control;

    private ArrayList<Monster> monsters;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> deletedBullets;
    private ArrayList<Bomb> bombs;
    private ArrayList<Bomb> deletedBombs;
    private ArrayList<Prop> props;
    private ArrayList<Prop> deletedProps;

    // 信息显示区域
    private final int PANEL_LEFT = 42;
    private final int PANEL_TOP = 6;

    // 最大怪物数量
    private final int MAX_MONSTERS = 10;
    private int cnt_monsters;

    // 子弹飞行速度
    private final int BULLET_SPEED = 10;
    private int bullet_wait = 0;

    private boolean lose;
    private boolean win;

    public PlayScreen() {
        world = new World();
        player = new Player(new Color(255, 0, 0), world, this);
        world.put(player, 0, 0);

        this.monsters = new ArrayList<Monster>();
        this.bullets = new ArrayList<Bullet>();
        this.deletedBullets = new ArrayList<Bullet>();
        this.bombs = new ArrayList<Bomb>();
        this.deletedBombs = new ArrayList<Bomb>();
        this.props = new ArrayList<Prop>();
        this.deletedProps = new ArrayList<Prop>();

        lose = false;
        win = false;

        control = new GameControl(this);
        control.start();


        for(int i = 0; i < MAX_MONSTERS; i++) {
            this.addMonster();
        }
        this.cnt_monsters = MAX_MONSTERS;
    }

    private void messageUpdate(AsciiPanel terminal) {
        String hp = String.format("HP: %2d/%2d", player.getHP(), player.getMaxHP());
        String pw = String.format("Power: %d", player.getPower());
        String bm = String.format("Bomb: %d", player.getBombs());
        terminal.write(hp, PANEL_LEFT, PANEL_TOP);
        terminal.write(pw, PANEL_LEFT, PANEL_TOP + 5);
        terminal.write(bm, PANEL_LEFT, PANEL_TOP + 10);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {
                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());
            }
        }
        messageUpdate(terminal);
    }

    // 更新所有物体
    public synchronized void update() {
        // 更新子弹位置
        bullet_wait++;
        if(bullet_wait >= BULLET_SPEED){
            for(Bullet b:bullets) {
                b.action();
            }
            bullet_wait = 0;
        }
        // 删除应该消亡的子弹
        for(Bullet b:deletedBullets) {
            int x = b.getX();
            int y = b.getY();
            bullets.remove(b);
            world.put(new Floor(world), x, y);
        }
        deletedBullets.clear();
        // 删除消亡的道具
        for(Prop p:deletedProps) {
            props.remove(p);
            world.put(new Floor(world), p.getX(), p.getX());
        }
        deletedProps.clear();
        // 删除消亡的炸弹
        
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        System.out.println(String.format("win: %b, lose: %b, hp: %d", win, lose, player.getHP()));
        if(lose == true || player.getHP() <= 0)
            return new LoseScreen();
        if(win == true)
            return new WinScreen();
        int code = key.getKeyCode();
        if(code == KeyEvent.VK_SPACE) {
            // 放置炸弹
            player.setBomb();
            return this;

        } else if(code == KeyEvent.VK_J) {
            // 发射子弹
            player.attack();
            return this;
        }
        // 移动
        switch(code) {
            case KeyEvent.VK_W:
                player.move(0);
                break;
            case KeyEvent.VK_S:
                player.move(1);
                break;
            case KeyEvent.VK_A:
                player.move(2);
                break;
            case KeyEvent.VK_D:
                player.move(3);
                break;
            default:
                break;
        }
        return this;
    }

    public Player getPlayer() {
        return this.player;
    }

    private void addMonster() {
        Random r = new Random();
        int x = r.nextInt(World.WIDTH);
        int y = r.nextInt(World.HEIGHT);
        while(true) {
            Thing t = world.get(x, y);
            if(t instanceof Floor)
                break;
            x = r.nextInt(World.WIDTH);
            y = r.nextInt(World.HEIGHT);
        }
        Monster m = new Monster(new Color(0, 255, 0), world, this);
        monsters.add(m);
        world.put(m, x, y);
        control.addMonster(m);
    }

    public synchronized void deleteMonster(Monster m) {
        int x = m.getX();
        int y = m.getY();
        world.put(new Floor(world), x, y);
        monsters.remove(m);
        cnt_monsters--;
        if(cnt_monsters <= 0)
            winGame();
    }

    public synchronized void addBullet(Bullet b) {
        bullets.add(b);
    }

    public synchronized void deleteBullet(Bullet b) {
        deletedBullets.add(b);
    }

    public synchronized void addBomb(Bomb b) {
        bombs.add(b);
    }
    
    public synchronized void deleteBomb(Bomb b) {
        deletedBombs.add(b);
    }

    public synchronized void triggerBomb() {
        // TODO: 炸弹爆炸特效
    }

    public synchronized void addProp(Prop p) {
        this.props.add(p);
    }

    public synchronized void deleteProp(Prop p) {
        this.deletedProps.add(p);
    }

    public void loseGame() {
        this.lose = true;
    }

    public void winGame() {
        this.win = true;
    }
}
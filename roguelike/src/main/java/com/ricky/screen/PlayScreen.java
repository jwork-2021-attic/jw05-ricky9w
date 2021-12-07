package com.ricky.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.ricky.world.Player;
import com.ricky.world.Floor;
import com.ricky.world.Monster;
import com.ricky.world.World;
import com.ricky.world.Thing;

import com.ricky.asciiPanel.AsciiPanel;
import com.ricky.control.GameControl;

public class PlayScreen implements Screen {
    
    private World world;
    private Player player;

    private GameControl control;

    private ArrayList<Monster> monsters;

    // 信息显示区域
    private final int PANEL_LEFT = 40;
    private final int PANEL_TOP = 6;

    // 最大怪物数量
    private final int MAX_MONSTERS = 5;

    public PlayScreen() {
        world = new World();
        player = new Player(new Color(255, 0, 0), world, this);
        world.put(player, 0, 0);

        this.monsters = new ArrayList<Monster>();

        control = new GameControl(this);
        control.start();


        for(int i = 0; i < MAX_MONSTERS; i++) {
            this.addMonster();
        }
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

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int code = key.getKeyCode();
        if(code == KeyEvent.VK_SPACE) {
            // TODO: 放置炸弹
        } else if(code == KeyEvent.VK_J) {
            // TODO: 发射子弹
        }
        // 移动
        int xCurr = player.getX();
        int yCurr = player.getY();
        int xTar = xCurr;
        int yTar = yCurr;
        switch(code) {
            case KeyEvent.VK_W:
                yTar--;
                break;
            case KeyEvent.VK_S:
                yTar++;
                break;
            case KeyEvent.VK_A:
                xTar--;
                break;
            case KeyEvent.VK_D:
                xTar++;
                break;
            default:
                break;
        }
        if (world.validMove(xTar, yTar)) {
            player.moveTo(xTar, yTar);
            world.put(new Floor(world), xCurr, yCurr);
        }
        return this;
    }

    public Player getPlayer() {
        return this.player;
    }

    private void addMonster() {
        Random r = new Random();
        int x = r.nextInt(world.WIDTH);
        int y = r.nextInt(world.HEIGHT);
        while(true) {
            Thing t = world.get(x, y);
            if(t instanceof Floor)
                break;
            x = r.nextInt(world.WIDTH);
            y = r.nextInt(world.HEIGHT);
        }
        Monster m = new Monster(new Color(0, 255, 0), world, this);
        monsters.add(m);
        world.put(m, x, y);
        control.addMonster(m);
    }
    
}
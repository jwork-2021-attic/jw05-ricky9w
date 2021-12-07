package com.ricky.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import com.ricky.world.Player;
import com.ricky.world.Floor;
import com.ricky.world.World;

import com.ricky.asciiPanel.AsciiPanel;
import com.ricky.control.GameControl;

public class PlayScreen implements Screen {
    
    private World world;
    private Player player;

    private GameControl control;

    // 信息显示区域
    private final int PANEL_LEFT = 40;
    private final int PANEL_TOP = 6;

    // 最大怪物数量
    private final int MAX_MONSTERS = 5;

    public PlayScreen() {
        world = new World();
        player = new Player(new Color(255, 0, 0), world, this);
        world.put(player, 0, 0);
    }

    private void messageUpdate(AsciiPanel terminal) {
        String hp = String.format("HP: %2d/%2d", player.getHP(), player.getMaxHP());
        String pw = String.format("Power: %d", player.getPower());
        terminal.write(hp, PANEL_LEFT, PANEL_TOP);
        terminal.write(pw, PANEL_LEFT, PANEL_TOP + 10);
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
    
}
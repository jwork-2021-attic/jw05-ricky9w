package com.ricky.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.ricky.world.Player;
import com.ricky.world.Floor;
import com.ricky.world.World;

import com.ricky.asciiPanel.AsciiPanel;

public class MazeScreen implements Screen {
    
    private World world;
    private Player player;

    public MazeScreen() {
        world = new World();
        player = new Player(new Color(255, 0, 0), world);
        world.put(player, 0, 0);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {
                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());
            }
        }
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int code = key.getKeyCode();
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
    
}
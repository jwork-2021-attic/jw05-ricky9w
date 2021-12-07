package com.ricky.world;

import java.awt.Color;

import com.ricky.world.Creature;
import com.ricky.world.Monster;
import com.ricky.screen.PlayScreen;

public class Bomb extends Thing{

    private Player parent;
    private PlayScreen screen;
    private int damage = 10;
    private long startTime = 0;
    
    public Bomb(Color color, World world, PlayScreen screen, Player player, long time) {
        super(color, (char)15, world);
        this.screen = screen;
        this.parent = player;
        this.startTime = time;
    }

    public void action() {
        // TODO: 根据时间判断爆炸
    }


}

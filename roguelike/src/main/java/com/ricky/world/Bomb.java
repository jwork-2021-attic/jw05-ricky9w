package com.ricky.world;

import java.awt.Color;

import com.ricky.world.Creature;
import com.ricky.world.Monster;
import com.ricky.screen.PlayScreen;

public class Bomb extends Thing{

    private Player parent;
    
    public Bomb(Color color, World world, PlayScreen screen) {
        super(color, (char)15, world);
        
    }
}

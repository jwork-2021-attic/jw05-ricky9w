package com.ricky.world;

import java.awt.Color;
import com.ricky.screen.PlayScreen;

public class Player extends Creature {
    
    public Player(Color color, World world, PlayScreen screen){
        super(color, (char) 2, world, screen);
    }
    
    @Override
    public void run() {

    }

}

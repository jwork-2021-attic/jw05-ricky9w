package com.ricky.world;

import com.ricky.asciiPanel.AsciiPanel;

public class Wall extends Thing {
    
    Wall(World world) {
        super(AsciiPanel.cyan, (char) 177, world);
    }
}
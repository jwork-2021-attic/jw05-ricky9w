package com.ricky.world;

import java.awt.Color;

// 道具类, 一个抽象类, 为其他道具提供接口
abstract class Prop extends Thing {
    
    public Prop(Color color, char glyph, World world) {
        super(color, glyph, world);
    }
}

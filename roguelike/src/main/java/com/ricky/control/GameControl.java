package com.ricky.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ricky.world.Monster;
import com.ricky.world.Player;
import com.ricky.screen.PlayScreen;

// 游戏控制模块, 控制玩家和怪物的生成与对战逻辑
public class GameControl implements Runnable {

    private Player player;
    private ExecutorService exec;
    
    public GameControl(PlayScreen screen) {
        this.player = screen.getPlayer();
        exec = Executors.newCachedThreadPool();
    }

    public void start() {
        
    }

    @Override
    public void run() {
        // 开始执行玩家线程
        exec.execute(player);
    }

    // 开始怪兽线程
    public void addMonster(Monster m) {
        exec.execute(m);
    }
}

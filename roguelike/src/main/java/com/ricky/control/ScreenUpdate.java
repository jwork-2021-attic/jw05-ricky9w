package com.ricky.control;

import javax.swing.JFrame;

import java.util.concurrent.TimeUnit;

public class ScreenUpdate implements Runnable {
    
    private JFrame frame;
    private final int FPS = 30;

    public ScreenUpdate(JFrame f) {
        this.frame = f;
    }

    @Override
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(1000 / FPS);
                frame.repaint();
            }
        } catch(InterruptedException e) {
            System.out.println("ScreenUpdate interrupted!");
        }
    }
}

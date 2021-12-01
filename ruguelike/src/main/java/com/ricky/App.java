package com.ricky;

/**
 * Hello world!
 *
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.ricky.asciiPanel.AsciiFont;
import com.ricky.asciiPanel.AsciiPanel;
import com.ricky.screen.Screen;
import com.ricky.screen.MazeScreen;
import com.ricky.world.World;

/**
 *
 * @author Aeranythe Echosong
 */
public class App extends JFrame implements KeyListener {


    private AsciiPanel terminal;
    private Screen screen;

    public App() {
        super();
        terminal = new AsciiPanel(World.WIDTH, World.HEIGHT, AsciiFont.TALRYTH_15_15);
        add(terminal);
        pack();
        screen = new MazeScreen();
        addKeyListener(this);
        repaint();
    }

    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    /**
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    /**
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     *
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        App app = new App();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

}

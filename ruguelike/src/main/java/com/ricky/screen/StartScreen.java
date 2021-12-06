package com.ricky.screen;

import com.ricky.asciiPanel.AsciiPanel;

/**
 *
 * @author Aeranythe Echosong
 */
public class StartScreen extends RestartScreen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("This is the start screen.", 0, 0);
        terminal.write("Press ENTER to continue...", 0, 1);
    }

}
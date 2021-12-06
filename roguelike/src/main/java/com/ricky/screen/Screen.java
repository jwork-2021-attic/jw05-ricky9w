package com.ricky.screen;

import com.ricky.asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;

/**
 *
 * @author Aeranythe Echosong
 */
public interface Screen {

    public void displayOutput(AsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);
}

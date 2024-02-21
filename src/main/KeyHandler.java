package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// This class handles keyboard events for player movement
public class KeyHandler implements KeyListener {
    // Boolean variables to track the state of specific keys (whether they are pressed or not)
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is part of the KeyListener interface but is not used in this class
        // It's called when a key is typed (pressed and released), but it's not implemented here
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // This method is called whenever a key is pressed

        // Get the integer code of the key that was pressed
        int code = e.getKeyCode();

        // Check if the 'W' key is pressed
        if (code == KeyEvent.VK_W) {
            upPressed = true; // Set the 'upPressed' flag to true
        }

        // Check if the 'S' key is pressed
        if (code == KeyEvent.VK_S) {
            downPressed = true; // Set the 'downPressed' flag to true
        }

        // Check if the 'A' key is pressed
        if (code == KeyEvent.VK_A) {
            leftPressed = true; // Set the 'leftPressed' flag to true
        }

        // Check if the 'D' key is pressed
        if (code == KeyEvent.VK_D) {
            rightPressed = true; // Set the 'rightPressed' flag to true
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // This method is called whenever a key is released

        // Get the integer code of the key that was released
        int code = e.getKeyCode();

        // Check if the 'W' key is released
        if (code == KeyEvent.VK_W) {
            upPressed = false; // Set the 'upPressed' flag to false
        }

        // Check if the 'S' key is released
        if (code == KeyEvent.VK_S) {
            downPressed = false; // Set the 'downPressed' flag to false
        }

        // Check if the 'A' key is released
        if (code == KeyEvent.VK_A) {
            leftPressed = false; // Set the 'leftPressed' flag to false
        }

        // Check if the 'D' key is released
        if (code == KeyEvent.VK_D) {
            rightPressed = false; // Set the 'rightPressed' flag to false
        }
    }
}

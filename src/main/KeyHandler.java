package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// This class is designed to handle keyboard events for controlling the player's movement
public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed; // These boolean variables keep track of whether specific keys are pressed




    @Override
    public void keyTyped(KeyEvent e) {
        // This method is part of the KeyListener interface but is not used in this class
    }




    @Override
    public void keyPressed(KeyEvent e) { // This method is called whenever a key is pressed

        // Get the integer code of the key that was pressed
        int code = e.getKeyCode();

        // Check if the 'W' key is pressed
        if (code == KeyEvent.VK_W) {
            // If so, set the 'upPressed' flag to true
            upPressed = true;

        }

        // Check if the 'S' key is pressed
        if (code == KeyEvent.VK_S) {
            // If so, set the 'downPressed' flag to true
            downPressed = true;

        }

        // Check if the 'A' key is pressed
        if (code == KeyEvent.VK_A) {
            // If so, set the 'leftPressed' flag to true
            leftPressed = true;

        }

        // Check if the 'D' key is pressed
        if (code == KeyEvent.VK_D) {
            // If so, set the 'rightPressed' flag to true
            rightPressed = true;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // This method is called whenever a key is released

        // Get the integer code of the key that was released
        int code = e.getKeyCode();

        // Check if the 'W' key is released
        if (code == KeyEvent.VK_W) {
            // If so, set the 'upPressed' flag to false
            upPressed = false;
        }

        // Check if the 'S' key is released
        if (code == KeyEvent.VK_S) {
            // If so, set the 'downPressed' flag to false
            downPressed = false;
        }

        // Check if the 'A' key is released
        if (code == KeyEvent.VK_A) {
            // If so, set the 'leftPressed' flag to false
            leftPressed = false;
        }

        // Check if the 'D' key is released
        if (code == KeyEvent.VK_D) {
            // If so, set the 'rightPressed' flag to false
            rightPressed = false;
        }
    }
}

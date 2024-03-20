package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// This class handles keyboard events for player movement
public class KeyHandler implements KeyListener {
    // Flags for Player 1
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean canMoveUp = true, canMoveDown = true, canMoveLeft = true, canMoveRight = true;
    public boolean rPressed;

    // Flags for Player 2
    public boolean wPressed, sPressed, aPressed, dPressed;
    private boolean canMoveW = true, canMoveS = true, canMoveA = true, canMoveD = true;


    @Override
    public void keyTyped(KeyEvent e) {
        // This method is part of the KeyListener interface but is not used in this class
        // It's called when a key is typed (pressed and released), but it's not implemented here
    }


    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Player 1 movement
        if (code == KeyEvent.VK_UP && canMoveUp) {
            upPressed = true;
            canMoveUp = false;
        }
        if (code == KeyEvent.VK_DOWN && canMoveDown) {
            downPressed = true;
            canMoveDown = false;
        }
        if (code == KeyEvent.VK_LEFT && canMoveLeft) {
            leftPressed = true;
            canMoveLeft = false;
        }
        if (code == KeyEvent.VK_RIGHT && canMoveRight) {
            rightPressed = true;
            canMoveRight = false;
        }

        // Player 2 movement
        if (code == KeyEvent.VK_W && canMoveW) {
            wPressed = true;
            canMoveW = false;
        }
        if (code == KeyEvent.VK_S && canMoveS) {
            sPressed = true;
            canMoveS = false;
        }
        if (code == KeyEvent.VK_A && canMoveA) {
            aPressed = true;
            canMoveA = false;
        }
        if (code == KeyEvent.VK_D && canMoveD) {
            dPressed = true;
            canMoveD = false;
        }
        if (code == KeyEvent.VK_R) {
            rPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // Player 1 movement
        if (code == KeyEvent.VK_UP) {
            upPressed = false;
            canMoveUp = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
            canMoveDown = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
            canMoveLeft = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
            canMoveRight = true;
        }

        // Player 2 movement
        if (code == KeyEvent.VK_W) {
            wPressed = false;
            canMoveW = true;
        }
        if (code == KeyEvent.VK_S) {
            sPressed = false;
            canMoveS = true;
        }
        if (code == KeyEvent.VK_A) {
            aPressed = false;
            canMoveA = true;
        }
        if (code == KeyEvent.VK_D) {
            dPressed = false;
            canMoveD = true;
        }
        if (code == KeyEvent.VK_R) {
            rPressed = false;
        }


    }
}
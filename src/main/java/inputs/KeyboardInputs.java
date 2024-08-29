package inputs;
import org.tutorial.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private final GamePanel game_panel;
    public static boolean [] keysPressed = new boolean[]{false,false,false,false};
    public KeyboardInputs(GamePanel game_panel){
        this.game_panel = game_panel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                keysPressed[0] = true;
                break;
            case KeyEvent.VK_A:
                keysPressed[1] = true;
                break;
            case KeyEvent.VK_S:
                keysPressed[2] = true;
                break;
            case KeyEvent.VK_D:
                keysPressed[3] = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                keysPressed[0] = false;
                break;
            case KeyEvent.VK_A:
                keysPressed[1] = false;
                break;
            case KeyEvent.VK_S:
                keysPressed[2] = false;
                break;
            case KeyEvent.VK_D:
                keysPressed[3] = false;
                break;
        }
    }
}

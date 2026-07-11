package inputs;
import org.tutorial.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.HashMap;

public class KeyboardInputs implements KeyListener {
    private final GamePanel game_panel;
    //public static boolean [] keysPressed = new boolean[]{false,false,false,false};
    public static HashMap<Integer,Boolean> keysPressed = new HashMap<>();
    public static HashMap<Integer,Integer> continuedKeyPress = new HashMap<>();
    //public static HashMap<Integer,Boolean> keysPressed
    public static int [] keyPressDuration = new int[]{0,0,0,0};
    public KeyboardInputs(GamePanel game_panel){
        this.game_panel = game_panel;
        keysPressed.put(KeyEvent.VK_W,false);
        keysPressed.put(KeyEvent.VK_A,false);
        keysPressed.put(KeyEvent.VK_S,false);
        keysPressed.put(KeyEvent.VK_D,false);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                keysPressed.put(KeyEvent.VK_W,true);
                break;
            case KeyEvent.VK_A:
                keysPressed.put(KeyEvent.VK_A,true);
                break;
            case KeyEvent.VK_S:
                keysPressed.put(KeyEvent.VK_S,true);
                break;
            case KeyEvent.VK_D:
                keysPressed.put(KeyEvent.VK_D,true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                keysPressed.put(KeyEvent.VK_W,false);
                continuedKeyPress.put(KeyEvent.VK_W,0);
                //System.out.println("W : " + continuedKeyPress.get(KeyEvent.VK_W));
                break;
            case KeyEvent.VK_A:
                keysPressed.put(KeyEvent.VK_A,false);
                continuedKeyPress.put(KeyEvent.VK_A,0);
                //System.out.println("A : " + continuedKeyPress.get(KeyEvent.VK_A));
                break;
            case KeyEvent.VK_S:
                keysPressed.put(KeyEvent.VK_S,false);
                continuedKeyPress.put(KeyEvent.VK_S,0);
                //System.out.println("S : " + continuedKeyPress.get(KeyEvent.VK_S));
                break;
            case KeyEvent.VK_D:
                keysPressed.put(KeyEvent.VK_D,false);
                continuedKeyPress.put(KeyEvent.VK_D,0);
               // System.out.println("D : " + continuedKeyPress.get(KeyEvent.VK_D));
                break;
        }
    }
}

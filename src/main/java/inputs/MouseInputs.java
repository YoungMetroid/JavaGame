package inputs;


import org.tutorial.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private final GamePanel game_panel;
    public MouseInputs(GamePanel game_panel){
        this.game_panel = game_panel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.printf("Mouse Dragged X: %d -- Y: %d%n",e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.printf("Mouse moved X: %d -- Y: %d%n",e.getX(),e.getY());


    }
}

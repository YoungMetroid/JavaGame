package org.tutorial;

import javax.swing.*;

public class GameWindow {
    public GameWindow(GamePanel GAME_PANEL){
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(GAME_PANEL);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

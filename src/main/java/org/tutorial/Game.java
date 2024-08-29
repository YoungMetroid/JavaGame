package org.tutorial;

import Entities.Entity;
import inputs.KeyboardInputs;

import java.util.Arrays;

public class Game implements Runnable{

    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int ONE_NANO_SECOND = 1_000_000_000;
    private final GamePanel gamePanel;
    private final GameState gameState;
    private final GameWindow gameWindow;
    public Game(){
        gamePanel = new GamePanel();
        gameState = new GameState();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        starGameLoop();
    }
    private void starGameLoop(){
        gameState.loadLevel();
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double timePerFrame = ONE_NANO_SECOND/FPS_SET;
        long lastFrame = System.nanoTime();
        long now;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while(true){
            now = System.nanoTime();
            if(now-lastFrame >= timePerFrame){
                Camara.tick++;
                if(Camara.tick == 60){
                    Camara.tick = 0;
                }
                Arrays.fill(Renderer.pixeles,0);
                gameState.update();
                gameState.renderLevel();
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >=1000){
                lastCheck = System.currentTimeMillis();
                //System.out.printf("FPS: %d%n",frames);
                frames = 0;
            }
        }
    }
}

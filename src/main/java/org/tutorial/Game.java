package org.tutorial;

import Entities.Entity;
import inputs.KeyboardInputs;

import java.util.Arrays;
import java.util.Map;

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
                for(Map.Entry<Integer, Boolean> entry:KeyboardInputs.keysPressed.entrySet()){
                    if(entry.getValue()){
                        Integer tick = KeyboardInputs.continuedKeyPress.getOrDefault(entry.getKey(),0);
                        tick++;
                        KeyboardInputs.continuedKeyPress.put(entry.getKey(),tick);
                    }
                }
                gameState.blackFill();
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

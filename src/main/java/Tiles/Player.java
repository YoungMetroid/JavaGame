package Tiles;

import inputs.KeyboardInputs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tutorial.Renderer;

import java.awt.event.KeyEvent;
import java.util.*;

public class Player extends Tile{
    private float previousX = 0;
    private float previousY = 0;
    private float previousDeltaX = 0;
    private float previousDeltaY = 0;
    private float deltaX = 0;
    private float deltaY = 0;
    int tick = 0;
    public int[] ticks = new int[]{0,0,0,0};
    private static final Logger logger = LogManager.getLogger(Player.class);
    public Player(int xLocation, int yLocation) {
        super(0,48,xLocation, yLocation);
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
    public float getPreviousX(){
        return previousX;
    }
    public float getPreviousY(){
        return previousY;
    }
    public float getDeltaX(){return deltaX;}
    public float getDeltaY(){return deltaY;}
    public void update(){
        //boolean[] keysPressed = KeyboardInputs.keysPressed;

        previousX =  this.getXLocation();
        previousY =  this.getYLocation();

        ticks[0] = KeyboardInputs.continuedKeyPress.getOrDefault(KeyEvent.VK_W,0);
        ticks[2] = KeyboardInputs.continuedKeyPress.getOrDefault(KeyEvent.VK_S,0);

        ticks[1] = KeyboardInputs.continuedKeyPress.getOrDefault(KeyEvent.VK_A,0);
        ticks[3] = KeyboardInputs.continuedKeyPress.getOrDefault(KeyEvent.VK_D,0);

        deltaX=0;
        deltaY=0;

        if(KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_W,false)){
            deltaY--;
        }
        if(KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_S,false)){
            deltaY++;
        }
        if(KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_A,false)){
            deltaX--;
        }
        if(KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_D,false)){
            deltaX++;
        }

        float length = (float)Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        if (length != 0) {
            deltaX /= length;
            deltaY /= length;
        }

        previousDeltaX = deltaX;
        previousDeltaY = deltaY;
        float x = this.getXLocation();
        float y = this.getYLocation();
        this.setLocation(x+deltaX*1.0f,y+deltaY*1.0f);

        //System.out.printf("%.3f %.3f%n", getXLocation(), getYLocation());
        //this.setXLocation(KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_A,false) ? this.getXLocation() - 1 : this.getXLocation());
        //this.setXLocation(KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_D,false) ? this.getXLocation() + 1 : this.getXLocation());
        //this.setYLocation(KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_W,false) ? this.getYLocation() - 1 : this.getYLocation());
        //this.setYLocation(KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_S,false) ? this.getYLocation() + 1 : this.getYLocation());

        int direction = getMaxIndex(ticks);

        switch(direction) {
            case 0 -> updateSprite(0,32,48);
            case 2 -> updateSprite(2,0,16);
            case 1 -> updateSprite(1,64,80);
            case 3 -> updateSprite(3,96,112);
        }
        tick++;
    }
    public void revertPosition(){
        this.setLocation(previousX,previousY);
    }
    public int getMaxIndex(int[] keys){
        int max = 0;
        int index = 0;
        for(int i = 0; i < keys.length; i++){
            if(keys[i] > max) {
                max = keys[i];
                index = i;
            }
        }
        if(max == 0) return -1;
        else return index;
    }
    public void updateSprite(int direction,int spriteX1, int spriteX2){
        if(ticks[direction]==1){
            this.setSpriteXLocation(this.getSpriteXLocation() == spriteX1 ? spriteX2 : spriteX1);
            return;
        }
        if(tick >=12 && tick% 12 == 0) {
            this.setSpriteXLocation(this.getSpriteXLocation() == spriteX1 ? spriteX2 : spriteX1);
            tick = tick >= 100_000 ? 12 :tick;
        }
    }
}

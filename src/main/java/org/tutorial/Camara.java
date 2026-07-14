package org.tutorial;

import Levels.Level;
import Tiles.Player;
import Tiles.Tile;
import inputs.KeyboardInputs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.KeyEvent;

public class Camara {
    private static final Logger logger = LogManager.getLogger(Camara.class);
    public static float x = 0;
    public static float y = 0;
    public static float leftScroll = 16;
    public static float rightScroll = 128;
    public static float upScroll = 16;
    public static float downScroll = 112;
    public static int tick = 0;
    public static void update(Player player, Level currentLevel){
        float deltaX = Math.abs(player.getDeltaX());
        float deltaY = Math.abs(player.getDeltaY());
        if((player.getXLocation() > Camara.rightScroll &&  KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_D,false)) &&
                player.getXLocation()+player.getTileSize() <= (currentLevel.levelWidth* 16)-16
        ){
            Camara.rightScroll +=deltaX;
            Camara.leftScroll +=deltaX;
            Camara.x = Camara.x - deltaX;
            //print(player.getXLocation(), player.getYLocation());
        }
        else if((player.getXLocation() < Camara.leftScroll && KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_A,false)) &&
                player.getXLocation() >= 16
        ) {
            Camara.rightScroll -= deltaX;
            Camara.leftScroll -=deltaX;
            Camara.x = Camara.x +deltaX;
           // print(player.getXLocation(), player.getYLocation());
        }
        if((player.getYLocation() > Camara.downScroll && KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_S,false))
                && player.getYLocation()+player.getTileSize() <= (currentLevel.levelHeight * 16)-16
            //||
             //(player.getYLocation() < Camara.upScroll && KeyboardInputs.keysPressed[2] && player.getYLocation() > 16)
        ){
            Camara.upScroll += deltaY;
            Camara.downScroll +=deltaY;
            Camara.y = Camara.y - deltaY;
            //print(player.getXLocation(), player.getYLocation());
        }
        else if(//((player.getYLocation() > Camara.downScroll && KeyboardInputs.keysPressed[0]) ||
                player.getYLocation() < Camara.upScroll && KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_W,false) &&
                        player.getYLocation() >= 16
        ){
            Camara.upScroll -=deltaY;
            Camara.downScroll -= deltaY;
            Camara.y = Camara.y +deltaY;
            //print(player.getXLocation(), player.getYLocation());
        }
    }
    public static void print(int playerXLocation, int playerYLocation){
        System.out.printf(
                "Camara X:%f Camara Y:%f " +
                        "leftScroll:%f rightScroll:%f " +
                        "upScroll:%f downScroll:%f " +
                        "Player X: %d Player Y: %d\n",
                x,y,leftScroll,rightScroll, upScroll,downScroll, playerXLocation, playerYLocation);
    }
    public static void camaraCalculation(float playerXLocation, float playerYLocation, int levelWidth, int levelHeight){
       camaraXCalculation(playerXLocation,levelWidth);
       camaraYCalculation(playerYLocation,levelHeight);

    }

    private static void camaraXCalculation(float playerXLocation,float levelWidth){
        logger.debug("PlayerXLocation: {} Level Width: {}",playerXLocation, levelWidth);
        //Camara X Calculation
        float left = playerXLocation-64;
        float right = playerXLocation+80+16;
        if(right > levelWidth * 16){
            left = left - (right - levelWidth *16);
        }
        left = Math.max(left,0);
        Camara.x = left *-1;
        leftScroll  = Math.abs( Camara.x) +16;
        rightScroll = Math.abs( Camara.x) +128;

        if(levelWidth < 10){
            Camara.x = (Renderer.WIDTH - levelWidth*16) /2;
        }
        logger.debug("Camara X: {}", Camara.x);
        logger.debug("UpScroll: {} DownScroll: {}", leftScroll, rightScroll);

    }
    private static void camaraYCalculation(float playerYLocation, float levelHeight){
        //Camara Y Calculation
        logger.debug("PlayerYLocation: {} Level Height: {}",playerYLocation, levelHeight);

        float top  = playerYLocation - 56;
        float bottom = playerYLocation+16+72;
        if(bottom > levelHeight*16){
            top = top - (bottom - levelHeight*16);
        }

        top  = Math.max(top, 0);
        Camara.y = top *-1;
        upScroll = Math.abs( Camara.y) + 16;
        downScroll = Math.abs( Camara.y) + 112;

        logger.debug("Camara Y: {}", Camara.y);
        logger.debug("UpScroll: {} DownScroll: {}", upScroll, downScroll);


        if(levelHeight < 9){
            Camara.y = (Renderer.HEIGHT - levelHeight*16) /2;
        }
    }
}


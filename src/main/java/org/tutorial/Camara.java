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
    public static int x = 0;
    public static int y = 0;
    public static int leftScroll = 16;
    public static int rightScroll = 128;
    public static int upScroll = 16;
    public static int downScroll = 112;
    public static int tick = 0;
    public static void update(Tile player, Level currentLevel){
        if((player.getXLocation() > Camara.rightScroll &&  KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_D,false)) &&
                player.getXLocation()+player.getTileSize() <= (currentLevel.levelWidth* 16)-16
        ){
            Camara.rightScroll++;
            Camara.leftScroll++;
            Camara.x--;
            print(player.getXLocation(), player.getYLocation());
        }
        else if((player.getXLocation() < Camara.leftScroll && KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_A,false)) &&
                player.getXLocation() >= 16
        ) {
            Camara.x++;
            Camara.rightScroll--;
            Camara.leftScroll--;
            print(player.getXLocation(), player.getYLocation());
        }
        if((player.getYLocation() > Camara.downScroll && KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_S,false))
                && player.getYLocation()+player.getTileSize() <= (currentLevel.levelHeight * 16)-16
            //||
             //(player.getYLocation() < Camara.upScroll && KeyboardInputs.keysPressed[2] && player.getYLocation() > 16)
        ){
            Camara.y--;
            Camara.downScroll++;
            Camara.upScroll++;
            print(player.getXLocation(), player.getYLocation());
        }
        else if(//((player.getYLocation() > Camara.downScroll && KeyboardInputs.keysPressed[0]) ||
                player.getYLocation() < Camara.upScroll && KeyboardInputs.keysPressed.getOrDefault(KeyEvent.VK_W,false) &&
                        player.getYLocation() >= 16
        ){
            Camara.y++;
            Camara.downScroll--;
            Camara.upScroll--;
            print(player.getXLocation(), player.getYLocation());
        }
    }
    public static void print(int playerXLocation, int playerYLocation){
        System.out.printf("Camara X:%d Camara Y:%d leftScroll:%d rightScroll:%d upScroll:%d downScroll:%d Player X: %d Player Y: %d\n",
                x,y,leftScroll,rightScroll, upScroll,downScroll, playerXLocation, playerYLocation);
    }
    public static void camaraCalculation(int playerXLocation, int playerYLocation, int levelWidth, int levelHeight){
       camaraXCalculation(playerXLocation,levelWidth);
       camaraYCalculation(playerYLocation,levelHeight);

    }

    private static void camaraXCalculation(int playerXLocation,int levelWidth){
        logger.debug("PlayerXLocation: {} Level Width: {}",playerXLocation, levelWidth);
        //Camara X Calculation
        int left = playerXLocation-64;
        int right = playerXLocation+80+16;
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
    private static void camaraYCalculation(int playerYLocation, int levelHeight){
        //Camara Y Calculation
        logger.debug("PlayerYLocation: {} Level Height: {}",playerYLocation, levelHeight);

        int top  = playerYLocation - 56;
        int bottom = playerYLocation+16+72;
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


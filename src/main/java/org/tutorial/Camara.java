package org.tutorial;

import Levels.Level;
import Tiles.Tile;
import inputs.KeyboardInputs;

public class Camara {
    public static int x = 0;
    public static int y = 0;
    public static int leftScroll = 16;
    public static int rightScroll = 128;
    public static int upScroll = 16;
    public static int downScroll = 112;
    public static int tick = 0;
    public static void update(Tile player, Level currentLevel){
        if((player.xLocation > Camara.rightScroll &&  KeyboardInputs.keysPressed[3]) &&
                player.xLocation+player.getTileSize() <= (currentLevel.levelWidth* 16)-16
        ){
            Camara.rightScroll++;
            Camara.leftScroll++;
            Camara.x--;
            print(player.xLocation, player.yLocation);
        }
        else if((player.xLocation < Camara.leftScroll && KeyboardInputs.keysPressed[1]) &&
                player.xLocation >= 16
        ) {
            Camara.x++;
            Camara.rightScroll--;
            Camara.leftScroll--;
            print(player.xLocation, player.yLocation);
        }
        if((player.yLocation > Camara.downScroll && KeyboardInputs.keysPressed[2])
                && player.yLocation+player.getTileSize() <= (currentLevel.levelHeight * 16)-16
            //||
             //(player.yLocation < Camara.upScroll && KeyboardInputs.keysPressed[2] && player.yLocation > 16)
        ){
            Camara.y--;
            Camara.downScroll++;
            Camara.upScroll++;
            print(player.xLocation, player.yLocation);
        }
        else if(//((player.yLocation > Camara.downScroll && KeyboardInputs.keysPressed[0]) ||
                player.yLocation < Camara.upScroll && KeyboardInputs.keysPressed[0] &&
                        player.yLocation >= 16
        ){
            Camara.y++;
            Camara.downScroll--;
            Camara.upScroll--;
            print(player.xLocation, player.yLocation);
        }
    }
    public static void print(int playerXLocation, int playerYLocation){
        System.out.printf("Camara X:%d Camara Y:%d leftScroll:%d rightScroll:%d upScroll:%d downScroll:%d Player X: %d Player Y: %d\n",
                x,y,leftScroll,rightScroll, upScroll,downScroll, playerXLocation, playerYLocation);
    }
    public static void camaraCalculation(int playerXLocation, int playerYLocation, int levelWidth, int levelHeight){
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

        //Camara Y Calculation
        int top  = playerYLocation - 56;
        int bottom = playerYLocation+16+72;
        if(bottom > levelHeight*16){
            top = top - (bottom - levelHeight*16);
        }
        top  = Math.max(top, 0);
        Camara.y = top *-1;
        upScroll = Math.abs( Camara.y) + 16;
        downScroll = Math.abs( Camara.y) + 112;
    }
}


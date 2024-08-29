package Menus;

import Tiles.*;

abstract class Menu {
    public int xLocation = 0;
    public int yLocation = 0;
    public Menu(){
    }
    public abstract void render();

    public Tile generateTile(int tile_id, int xPosition, int yPosition){
        return switch (tile_id) {
            default -> new MenuBackGroundBlock(8,3*8 ,xPosition, yPosition);
            //Left Corner
            case 1 ->  new MenuBackGroundBlock(0,11*8 ,xPosition, yPosition);
            //Right Corner
            case 2 ->  new MenuBackGroundBlock(8,11*8 ,xPosition, yPosition);
            //Bottom Left Corner
            case 3 ->  new MenuBackGroundBlock(0,12*8 ,xPosition, yPosition);
            //Bottom Right Corner
            case 4 ->  new MenuBackGroundBlock(8,12*8 ,xPosition, yPosition);
            //Left Side
            case 5 ->  new MenuBackGroundBlock(0,9*8 ,xPosition, yPosition);
            //Right Side
            case 6 ->  new MenuBackGroundBlock(16,9*8 ,xPosition, yPosition);
            //TopSide
            case 7 ->  new MenuBackGroundBlock(8,8*8 ,xPosition, yPosition);
            //BottomSide
            case 8 ->  new MenuBackGroundBlock(8,10*8 ,xPosition, yPosition);
        };
    }

}

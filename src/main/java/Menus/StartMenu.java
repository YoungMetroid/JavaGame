package Menus;

import Tiles.Tile;
import org.tutorial.Renderer;

import java.util.ArrayList;
import java.util.List;

public class StartMenu extends Menu{

    private final static int[] menu ={
        1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,2,
        5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
        5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
        5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,
        3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,4
    };
    List<Tile> village = new ArrayList<>();

    public void generateMenu(){
        int y =0;
        int x = 0;
        for(int i = 0; i < menu.length; i++, x++){
            if(x == 10) {
                x = -10;
                y +=16;
            }
            village.add(generateTile(menu[i],x*16,y));
            System.out.println("X: " + x*16 + " Y: " + y);
        }
    }

    @Override
    public void render() {

    }
}

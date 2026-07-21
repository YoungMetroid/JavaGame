package Tiles;

import org.tutorial.Renderer;

public class Building extends Tile{

    public Building(int id, int xLocation, int yLocation){
        super(xLocation,yLocation);
        if(id != 47){
            setCollisionStatus(true);
        }
        int[] spritePosition = getSpriteTileLocation(id);
        setSpriteXLocation(spritePosition[0]);
        setSpriteYLocation(spritePosition[1]);
    }
    private int[] getSpriteTileLocation(int id){
        return switch(id){
            default-> new int[]{0,64};
            case 41-> new int[]{16,64};
            case 42-> new int[]{32,64};
            case 43-> new int[]{0,80};
            case 44-> new int[]{16,80};
            case 45-> new int[]{32,80};
            case 46-> new int[]{0,96};
            case 47-> new int[]{16,96};
            case 48-> new int[]{32,96};
        };
    }
    public void render() {
        Renderer.render(this);
    }
}

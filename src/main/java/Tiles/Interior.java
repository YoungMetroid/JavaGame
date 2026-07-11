package Tiles;

import org.tutorial.Renderer;

public class Interior extends Tile{
    public Interior(int id, int xLocation, int yLocation) {
        super(xLocation, yLocation);
        if(id != 50 && id !=1){
            setCollisionStatus(true);
        }
        int[] spritePosition = getSpriteTileLocation(id);
        setSpriteXLocation(spritePosition[0]);
        setSpriteYLocation(spritePosition[1]);
    }
    private int[] getSpriteTileLocation(int id){
        return switch(id){
            default-> new int[]{0,112};
            case 51-> new int[]{16,112};
            case 52-> new int[]{32,112};
            case 53-> new int[]{48,112};
            case 54-> new int[]{64,112};
            case 55-> new int[]{80,112};
            case 56-> new int[]{96,112};
            case 57-> new int[]{112,112};
            case 58-> new int[]{128,112};
            case 59-> new int[]{144,112};
            case 60-> new int[]{160,112};
            case 61-> new int[]{0,128};
        };
    }
    public void render() {
        Renderer.render(this);
    }
}

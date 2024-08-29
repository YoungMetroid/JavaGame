package Tiles;

import org.tutorial.Renderer;

public class Walls extends Tile{
    public Walls(int id,int xLocation, int yLocation) {
        super(xLocation, yLocation);
        setCollisionStatus(true);
        int[] spritePositions = getSpriteTileLocation(id);
        setSpriteXLocation(spritePositions[0]);
        setSpriteYLocation(spritePositions[1]);
    }
    private int[] getSpriteTileLocation(int id){
        return switch(id){
            default-> new int[]{48,0};
            case 11-> new int[] {0,16};
            case 12-> new int[]{16,16};
            case 13-> new int[]{32,16};
            case 14-> new int[]{48,16};
            case 15-> new int[]{64,16};
            case 16-> new int[]{80,16};
            case 17-> new int[]{96,16};
            case 18-> new int[]{112,16};
            case 19-> new int[]{128,16};
            case 20-> new int[]{144,16};
            case 21-> new int[]{160,16};
            case 22-> new int[]{176,16};
            case 23-> new int[]{192,16};

        };
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
}

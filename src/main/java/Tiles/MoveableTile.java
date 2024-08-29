package Tiles;

import org.tutorial.Renderer;

public class MoveableTile extends Tile{
    public MoveableTile(int id,int xLocation, int yLocation) {
        super(xLocation, yLocation);
        int[] spritePositions = getSpriteTileLocation(id);
        setSpriteXLocation(spritePositions[0]);
        setSpriteYLocation(spritePositions[1]);
        setCollisionStatus(true);
    }

    private int[] getSpriteTileLocation(int id){
        return switch(id){
            default-> new int[]{0,32};
            case 31-> new int[] {16,32};
            case 32-> new int[]{32,32};
            case 33-> new int[]{48,32};
            case 34-> new int[]{64,32};
        };
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
}

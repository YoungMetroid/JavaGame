package Tiles;

import org.tutorial.Renderer;

public class NotInteractableTile extends Tile{


    public NotInteractableTile(int id, int xLocation, int yLocation) {
        super(xLocation, yLocation);
        int[] spritePositions = getSpriteTileLocation(id);
        setSpriteXLocation(spritePositions[0]);
        setSpriteYLocation(spritePositions[1]);
    }
    private int[] getSpriteTileLocation(int id){
        return switch(id){
            default-> new int[]{0,0};
            case 1-> new int[] {16,0};
            case 2-> new int[]{32,0};
        };
    }

    @Override
    public void render() {
        Renderer.render(this);
    }
}

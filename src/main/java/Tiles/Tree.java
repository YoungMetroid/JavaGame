package Tiles;

import org.tutorial.Renderer;

public class Tree extends Tile{

    public Tree(int xPosition, int yPosition) {
        super(48,0,xPosition,yPosition);
        setCollisionStatus(true);
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
}

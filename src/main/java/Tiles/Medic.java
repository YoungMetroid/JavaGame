package Tiles;

import org.tutorial.Renderer;

public class Medic extends Tile{
    public Medic(int xLocation, int yLocation) {
        super(0, 48, xLocation, yLocation);
        setCollisionStatus(true);
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
}

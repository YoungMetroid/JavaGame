package Tiles;

import org.tutorial.Renderer;

public class MenuBackGroundBlock extends Tile{

    public MenuBackGroundBlock(int x_Sheet_Location, int y_Sheet_Location, int xLocation, int yLocation) {
        super(x_Sheet_Location, y_Sheet_Location, xLocation, yLocation);
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
}

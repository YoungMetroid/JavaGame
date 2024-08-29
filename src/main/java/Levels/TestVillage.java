package Levels;

import Tiles.Tile;
import java.util.ArrayList;
import java.util.List;
public class TestVillage extends Level  {

    List<Tile> village = new ArrayList<>();
    String[][] level;
    public TestVillage(String[][] level){
        this.level = level;
        levelHeight = level.length;
        levelWidth = level[0].length;
    }
    @Override
    public void render() {
        for(Tile tile: village){
            tile.render();
        }}

    @Override
    public void generateLevel() {
        for(int y = 0; y < level.length; y++){
            for(int x = 0; x < level[y].length; x++) {
                village.add(generateTile(Double.parseDouble(level[y][x]), x * 16, y*16));
            }
        }
        collidableObjects = village.stream().filter(Tile::getCollisionStatus).toList();

    }
    public void setLevel(String[][] level){
        this.level = level;
        levelHeight = level.length;
        levelWidth = level[0].length;
    }
    public void clearLevel(){
        village.clear();
    }
}

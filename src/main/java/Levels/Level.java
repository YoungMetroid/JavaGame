package Levels;

import Tiles.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Level {
    public int levelId = 0;
    public List<Level> maps = new ArrayList<>();
    public List<int[]> exits = new ArrayList<>();
    List<Tile> collidableObjects = new ArrayList<>();
    public int levelWidth = 0;
    public int levelHeight = 0;

    //Make so that collision is not exact and there is room to manoeuvre around the player
    public final int collisionOffSet =2;
    public final int exitOffSet = 0;
    public abstract void render();
    public abstract void generateLevel();
    public abstract void clearLevel();
    public abstract void setLevel(String[][] level);
    public boolean isThereACollision(Tile tile){
        List<Tile>collision = collidableObjects.stream().filter(
                //Right Side compare to Left Player
                walls-> (   ((tile.xLocation+collisionOffSet >= walls.xLocation  &&
                        tile.xLocation+collisionOffSet < walls.xLocation+walls.getTileSize() ) ||

                        (tile.xLocation+tile.getTileSize()-collisionOffSet > walls.xLocation  &&
                                tile.xLocation+tile.getTileSize()-collisionOffSet <= walls.xLocation+walls.getTileSize())
                )
                        &&
                        ((tile.yLocation+collisionOffSet >= walls.yLocation  &&
                                tile.yLocation+collisionOffSet < walls.yLocation+walls.getTileSize() ) ||

                                (tile.yLocation+tile.getTileSize()-collisionOffSet > walls.yLocation  &&
                                        tile.yLocation+tile.getTileSize()-collisionOffSet <= walls.yLocation+walls.getTileSize())
                        ))
        ).findAny().stream().toList();
        return !collision.isEmpty();
    }
    public Optional<int[]> isPlayerAtExit(Tile tile){
        Optional<int[]> exit = exits.stream().filter(
                allExits-> (
                        //LeftSide Player collision with rightSide Exit
                        ((tile.xLocation >= allExits[1]  &&
                            tile.xLocation <= allExits[2] - exitOffSet) ||
                        //RightSide Player collision with leftSide Exit
                        (tile.xLocation + tile.getTileSize() >= allExits[1] + exitOffSet &&
                                tile.xLocation + tile.getTileSize()  <= allExits[2]))
                        &&
                        //TopSide Player collision with BottomSide Exit
                        ((tile.yLocation >= allExits[3] &&
                                tile.yLocation <= allExits[4] - exitOffSet) ||
                        //BottomSide Player collision with TopSide Exit
                        (tile.yLocation + tile.getTileSize()-1 >= allExits[3] + exitOffSet &&
                                tile.yLocation + tile.getTileSize()-1  <= allExits[4]))
                )
        ).findFirst();
       return exit;
    }

    public Tile generateTile(double tId, int xPosition, int yPosition){
        int tileId = (int) tId;
        return switch (
                (tileId <= 9  ) ? 0:
                (tileId <= 29 ) ? 10:
                        (tileId <= 39) ? 30 : 40)
             {
            default -> new NotInteractableTile(tileId, xPosition, yPosition);
            case 10 -> new Walls(tileId, xPosition, yPosition);
            case 30 -> new MoveableTile(tileId, xPosition, yPosition);
        };

    }
    public void setExits(int[][]exits){
        this.exits = Arrays.stream(exits).toList();
    }

}

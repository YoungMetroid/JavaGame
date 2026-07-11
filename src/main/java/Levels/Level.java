package Levels;

import Tiles.*;
import inputs.KeyboardInputs;
import org.tutorial.ExitsDef;

import java.util.*;

public abstract class Level {
    public int levelId = 0;
    public final int spriteSize=16;
    public final int playerCollisionOffset=15;
    public List<Level> maps = new ArrayList<>();
    public List<int[]> exits = new ArrayList<>();
    List<Tile> collidableObjects = new ArrayList<>();
    List<Tile> collisionList = new ArrayList<>();
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
        boolean collision = false;
        collisionList = new ArrayList<>();
        for(int i = 0; i < collidableObjects.size(); i++){
            Tile wall = collidableObjects.get(i);
            if(
                ((tile.getXLocation() + collisionOffSet >=
                wall.getXLocation()
                &&  tile.getXLocation() + collisionOffSet <
                wall.getXLocation()+wall.getTileSize())
                ||
                (tile.getXLocation() + tile.getTileSize() - collisionOffSet >
                wall.getXLocation()
                &&  tile.getXLocation() + tile.getTileSize() - collisionOffSet <=
                wall.getXLocation() + wall.getTileSize()))

                &&

                ((tile.getYLocation() + collisionOffSet >=
                wall.getYLocation()
                && tile.getYLocation() + collisionOffSet <
                wall.getYLocation()+wall.getTileSize())
                ||
                (tile.getYLocation() + tile.getTileSize() >
                wall.getYLocation()
                && tile.getYLocation() + tile.getTileSize()  <=
                wall.getYLocation() + wall.getTileSize()))
            ){
                collision = true;
                collisionList.add(wall);
            }
        }
        /*
        List<Tile>collision = collidableObjects.stream().filter(
                //Right Side compare to Left Player
                walls-> (   ((tile.getXLocation()+collisionOffSet >= walls.getXLocation()  &&
                        tile.getXLocation()+collisionOffSet < walls.getXLocation()+walls.getTileSize() ) ||

                        (tile.getXLocation()+tile.getTileSize()-collisionOffSet > walls.getXLocation()
                        && tile.getXLocation()+tile.getTileSize()-collisionOffSet <= walls.getXLocation()+walls.getTileSize())
                )
                        &&
                        ((tile.getYLocation()+collisionOffSet >= walls.getYLocation()  &&
                                tile.getYLocation()+collisionOffSet < walls.getYLocation()+walls.getTileSize() ) ||

                                (tile.getYLocation()+tile.getTileSize() > walls.getYLocation()  &&
                                        tile.getYLocation()+tile.getTileSize() <= walls.getYLocation()+walls.getTileSize())
                        ))
        ).findAny().stream().toList();
        collisionList = collision;
        return !collision.isEmpty();

         */
        return collision;
    }
    public boolean isTileMoveable(Tile tile){
        Optional<Tile> firstMoveableTile = collisionList.stream()
                .filter(t -> t instanceof MoveableTile)
                .findFirst();
        if(firstMoveableTile.isPresent()){
           Optional<Map.Entry<Integer,Integer>> maxEntry = KeyboardInputs.continuedKeyPress.entrySet()
                   .stream()
                   .max(Map.Entry.comparingByValue());
           maxEntry.ifPresent(entry-> System.out.printf("%d pressed for %d ticks\n",entry.getKey(), entry.getValue()));

        }
        return false;
    }
    public Optional<int[]> isPlayerAtExit(Tile tile){

        Optional<int[]> exit = exits.stream().filter(
                allExits-> (
                        //LeftSide Player collision with rightSide Exit
                        ((tile.getXLocation() >= allExits[ExitsDef.LEFT_X.getValue()]*spriteSize  &&
                            tile.getXLocation() <= allExits[ExitsDef.RIGHT_X.getValue()]*spriteSize - exitOffSet+playerCollisionOffset) ||
                        //RightSide Player collision with leftSide Exit
                        (tile.getXLocation() + tile.getTileSize() >= allExits[ExitsDef.LEFT_X.getValue()]*spriteSize + exitOffSet &&
                                tile.getXLocation() + tile.getTileSize()  <= allExits[ExitsDef.RIGHT_X.getValue()]*spriteSize+playerCollisionOffset))
                        &&
                        //TopSide Player collision with BottomSide Exit
                        ((tile.getYLocation() >= allExits[ExitsDef.TOP_Y.getValue()]*spriteSize &&
                                tile.getYLocation () <= allExits[ExitsDef.BOTTOM_Y.getValue()]*spriteSize - exitOffSet) ||
                        //BottomSide Player collision with TopSide Exit
                        (tile.getYLocation() + tile.getTileSize()-1 >= allExits[ExitsDef.TOP_Y.getValue()]*spriteSize + exitOffSet &&
                                tile.getYLocation() + tile.getTileSize()-1  <= allExits[ExitsDef.BOTTOM_Y.getValue()]*spriteSize+playerCollisionOffset))
                )
        ).findFirst();
       return exit;
    }

    public Tile generateTile(double tId, int xPosition, int yPosition){
        int tileId = (int) tId;
        return switch (
                (tileId <= 9  ) ? 0:
                (tileId <= 29 ) ? 10:
                (tileId <= 39) ?  30:
                (tileId <= 49) ?  40:50)
             {
            default -> new NotInteractableTile(tileId, xPosition, yPosition);
            case 10 -> new Walls(tileId, xPosition, yPosition);
            case 30 -> new MoveableTile(tileId, xPosition, yPosition);
            case 40 -> new Building(tileId,xPosition,yPosition);
            case 50 -> new Interior(tileId,xPosition,yPosition);
        };

    }
    public void setExits(int[][]exits){
        this.exits = Arrays.stream(exits).toList();
    }

}

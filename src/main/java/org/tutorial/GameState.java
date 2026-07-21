package org.tutorial;
import Levels.Level;
import Levels.LevelLoader;
import Levels.TestVillage;
import Tiles.Player;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class GameState {
    Map<Integer,String[][]> levelList;
    Map<Integer,int[][]> exitsList;
    Player player;
    Level currentLevel;

    public GameState(){
    }
    public void blackFill(){
        int defaultColor = 0xffaa00aa;
        Arrays.fill(Renderer.pixeles,defaultColor);
    }
    public void renderLevel(){
        currentLevel.render();
        player.render();
    }
    public void update(float delta){
        //All player, movement, and enemies interactions
        //As well as camara movement calculation should be done here.
            if(!Renderer.loading && !Renderer.unFading) {
                player.update(delta);
                if (currentLevel.isThereACollision(player)) {
                    if(currentLevel.isTileMoveable(player)){

                    }
                    player.revertPosition();
                }
                areThereNextLevel();
                Camara.update(player, currentLevel);
            }
            else if(Renderer.loading && Renderer.unFading){
                loadNextLevel();

                Renderer.justLoaded = true;
            }
    }

    public void areThereNextLevel(){
        Optional<int[]> exits = currentLevel.isPlayerAtExit(player);
        if(exits.isPresent() ){
            Renderer.loading = true;
        }
    }
    public void loadNextLevel(){
        Optional<int[]> exits = currentLevel.isPlayerAtExit(player);
        if(exits.isPresent() ){
            int[] exit = exits.get();
            int playerOffset = exit[ExitsDef.PLAYER_OFFSET.getValue()];
            int pixelOffSet = mapPlayerOffSetToPixel(playerOffset);

            int xOffset = playerOffset == 2 || playerOffset == 4 ? pixelOffSet:0;
            int yOffset = playerOffset == 1 || playerOffset == 3 ? pixelOffSet:0;
            player.setXLocation(exit[ExitsDef.PLAYER_X.getValue()]*16+xOffset);
            player.setYLocation(exit[ExitsDef.PLAYER_Y.getValue()]*16+yOffset);
            currentLevel.setLevel(levelList.get(exit[ExitsDef.LEVEL.getValue()]));
            currentLevel.setExits(exitsList.get(exit[ExitsDef.LEVEL.getValue()]));
            currentLevel.clearLevel();
            currentLevel.generateLevel();
            Camara.camaraCalculation(player.xLocation,player.yLocation,
                                    currentLevel.levelWidth,currentLevel.levelHeight);
        }
    }
    public void loadLevel(){
        LevelLoader levelLoader = new LevelLoader();
        levelLoader.loadLevels();
        levelLoader.exitsLoader();
        levelList = levelLoader.getLevelList();
        exitsList = levelLoader.getExistsList();

        int[][] exits = exitsList.get(3);
        currentLevel = new TestVillage(levelList.get(3));
        currentLevel.generateLevel();
        currentLevel.setExits(exits);
        player = new Player(128,200);
        Camara.camaraCalculation((int)player.getXLocation(), (int)player.getYLocation(),
                                currentLevel.levelWidth, currentLevel.levelHeight);
        Camara.print((int)player.getXLocation(),(int)player.getYLocation());
    }
    public int mapPlayerOffSetToPixel(int playerOffset){
        return  playerOffset==1 ? -4:
                playerOffset==2 ? -4:
                        4;
    }
}

package org.tutorial;
import Levels.Level;
import Levels.LevelLoader;
import Levels.TestVillage;
import Tiles.Tree;
import inputs.KeyboardInputs;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

public class GameState {
    Map<Integer,String[][]> levelList;
    Map<Integer,int[][]> exitsList;
    Tree player;
    Level currentLevel;
    //int semafore = 0;
    public GameState(){
    }
    public void renderLevel(){
        currentLevel.render();
        player.render();
    }
    public void update(){
        //All player, movement, and enemies interactions
        //As well as camara movement calculation should be done here.


            if(!Renderer.loading && !Renderer.unFading) {
                int x = player.getXLocation();
                int y = player.getYLocation();

                player.xLocation = KeyboardInputs.keysPressed[1] ? player.xLocation - 1 : player.xLocation;
                player.xLocation = KeyboardInputs.keysPressed[3] ? player.xLocation + 1 : player.xLocation;
                player.yLocation = KeyboardInputs.keysPressed[0] ? player.yLocation - 1 : player.yLocation;
                player.yLocation = KeyboardInputs.keysPressed[2] ? player.yLocation + 1 : player.yLocation;

                if (currentLevel.isThereACollision(player)) {
                    player.xLocation = x;
                    player.yLocation = y;
                }
                //loadNextLevel();
                areThereNextLevel();
                Camara.update(player, currentLevel);
            }
            else if(Renderer.loading && Renderer.unFading){
              loadNextLevel();
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
            //&& semafore == 0
           // semafore = 1;
            int[] exit = exits.get();
            player.xLocation = exit[5];
            player.yLocation = exit[6];
            currentLevel.setLevel(levelList.get(exit[0]));
            currentLevel.setExits(exitsList.get(exit[0]));
            currentLevel.clearLevel();
            currentLevel.generateLevel();
            Camara.camaraCalculation(player.xLocation,player.yLocation,
                                    currentLevel.levelWidth,currentLevel.levelHeight);
        }
  /*      else if(exits.isPresent() && semafore == 1){
            System.out.println("Already loaded level no need to load");
        }
        else if(!exits.isPresent() && semafore == 1){
            semafore = 0;
        }

   */
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
        player = new Tree(128,460);
        Camara.camaraCalculation(player.xLocation, player.yLocation,
                                currentLevel.levelWidth, currentLevel.levelHeight);
        Camara.print(player.xLocation,player.yLocation);
        /*
        Camara.x =-160;
        Camara.y =-64;
        Camara.leftScroll += 160;
        Camara.rightScroll += 160;
        Camara.upScroll += 64;
        Camara.downScroll += 64;
         */
    }
}

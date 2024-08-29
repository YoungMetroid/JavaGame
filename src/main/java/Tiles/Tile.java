package Tiles;
public abstract class Tile {
    public int xSpriteLocation=0;
    public int ySpriteLocation=0;
    public int xLocation = 0;
    public int yLocation = 0;
    private int tileSize = 16;
    private boolean collisionStatus = false;

    public Tile(int xSpriteLocation, int ySpriteLocation, int xLocation, int yLocation ){
         this.xSpriteLocation = xSpriteLocation;
         this.ySpriteLocation =  ySpriteLocation;
         this.xLocation = xLocation;
         this.yLocation = yLocation;
    }
    public Tile(int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public int getSpriteXLocation() {
        return xSpriteLocation;
    }
    public int getSpriteYLocation(){
        return ySpriteLocation;
    }
    public void setSpriteXLocation(int x){this.xSpriteLocation = x;}
    public void setSpriteYLocation(int y){
        this.ySpriteLocation = y;
    }
    public int getXLocation(){return xLocation;}
    public int getYLocation(){return yLocation;}
    public void setCollisionStatus(boolean hasCollision){
        this.collisionStatus = hasCollision;
    }
    public int getTileSize(){return tileSize;}

    public boolean getCollisionStatus(){
        return collisionStatus;
    }
    public void setTileSize(int tileSize){this.tileSize = tileSize;}
    public abstract void render();

}

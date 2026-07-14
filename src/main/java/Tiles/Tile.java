package Tiles;
public abstract class Tile {
    public float xSpriteLocation=0;
    public float ySpriteLocation=0;
    private float xLocation = 0;
    private float yLocation = 0;
    private int tileSize = 16;
    private boolean collisionStatus = false;

    public Tile(float xSpriteLocation, float ySpriteLocation, float xLocation, float yLocation ){
         this.xSpriteLocation = xSpriteLocation;
         this.ySpriteLocation =  ySpriteLocation;
         this.xLocation = xLocation;
         this.yLocation = yLocation;
    }
    public Tile(float xLocation, float yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public float getSpriteXLocation() {
        return xSpriteLocation;
    }
    public float getSpriteYLocation(){
        return ySpriteLocation;
    }
    public void setSpriteXLocation(int x){this.xSpriteLocation = x;}
    public void setSpriteYLocation(int y){
        this.ySpriteLocation = y;
    }
    public float getXLocation(){return xLocation;}
    public float getYLocation(){return yLocation;}
    public void setXLocation(float xLocation){this.xLocation = xLocation;}
    public void setYLocation(float yLocation){
        this.yLocation = yLocation;
    }
    public void setLocation(float xLocation, float yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
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

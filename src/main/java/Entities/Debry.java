package Entities;

public class Debry extends Entity{

    public Debry(){
        generateRandomStartingPosition();
    }
    private void generateRandomStartingPosition(){
        x = (int)(Math.random() *100) +1;
        y = (int)(Math.random() *100) +1;
    }



}

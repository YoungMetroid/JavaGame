package org.tutorial;

public enum ExitsDef {
    LEVEL(0),
    LEFT_X(1),
    RIGHT_X(2),
    TOP_Y(3),
    BOTTOM_Y(4),
    PLAYER_X(5),
    PLAYER_Y(6);
    private final int value;
    ExitsDef(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }

}

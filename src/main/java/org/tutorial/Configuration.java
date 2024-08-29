package org.tutorial;
public class Configuration {
    public Configuration(){
        setScreenSize(screenSize.x4);
    }
    public enum screenSize{
        x1, x2, x3, x4, x5, x6, x7
    }
    private static int screenWidth = 160;
    private static int screenHeight = 144;
    public int getscreenWidth(){return screenWidth;}
    public int getscreenHeight(){return screenHeight;}

    public void setScreenSize(screenSize s){
        int render_width = 160;
        int render_height = 144;
        switch(s){

            case x1:
                screenHeight = render_height;
                screenWidth = render_width;
                break;
            case x2:
                screenHeight = render_height *2;
                screenWidth = render_width *2;
                break;
            case x3:
                screenHeight = render_height *3;
                screenWidth = render_width *3;
                break;
            default:
            case x4:
                screenHeight = render_height *4;
                screenWidth = render_width *4;
                break;
            case x5:
                screenHeight = render_height *5;
                screenWidth = render_width *5;
                break;
            case x6:
                screenHeight = render_height *6;
                screenWidth = render_width *6;
                break;
            case x7:
                screenHeight = render_height *7;
                screenWidth = render_width *7;
                break;
        }
    }
}

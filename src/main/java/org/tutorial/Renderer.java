package org.tutorial;

import Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.RunnableScheduledFuture;

public class Renderer {
    public static final int WIDTH = 160, HEIGHT = 144;
    public static int[]pixeles = new int[WIDTH* HEIGHT];

    public static boolean loading = false;
    public static boolean fading = false;
    public static boolean unFading = false;
    public static float fadeStart = 0.0f;
    public static float fadeAmount = 0.02f;
    public static float currentFadeAmount = 0.00f;
    public static SpriteSheet spritesheet = new SpriteSheet("/GameboySprites-Sheet.png");

    public static void render(Tile tile){
        int yTileLocation = tile.getSpriteYLocation();
        int y = tile.getYLocation() + Camara.y ;
        int x = tile.getXLocation() + Camara.x ;

        int yy = WIDTH*y;

        for(int i = WIDTH*y; i < (WIDTH*y) + tile.getTileSize();i++){
            int xTileLocation = tile.getSpriteXLocation();
            int rightEdge = yy+WIDTH;

            int leftEdge = yy;
            for(int j = x; j < x+tile.getTileSize(); j++, xTileLocation++){
                //int pixelPosition  = (yy + (Camara.y * WIDTH)) + j+Camara.x;
                int pixelPosition  = (yy + j);

                //Y edge cases
                if(pixelPosition >= pixeles.length || pixelPosition < 0)
                    continue;
                //X edge cases
                if(pixelPosition >= rightEdge)
                    continue;

                if(pixelPosition <leftEdge)
                    continue;

                pixeles[pixelPosition] = spritesheet.pixels[(1000* yTileLocation) + xTileLocation];
                //System.out.print(pixelPosition + ", ");
            }
            //System.out.println();
            yy += WIDTH;
            yTileLocation++;
        }
    }
    public static BufferedImage fadeImage(BufferedImage image){

        if(!loading) return image;

        fadeCheck();
        int width = image.getWidth();
        int height = image.getHeight();
        //       System.out.printf("Fade Amount: %1.3f\n" , fadeAmount);
        // Create a new BufferedImage to hold the faded version
        BufferedImage fadedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Get a Graphics2D object from the new image
        Graphics2D g2d = fadedImage.createGraphics();

        // Draw the original image
        g2d.drawImage(image, 0, 0, null);

        // Set the composite to control the fade amount
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, currentFadeAmount));

        // Draw a black rectangle over the entire image
        g2d.setColor(java.awt.Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        // Dispose of the Graphics2D object
        g2d.dispose();

        return fadedImage;
    }
    public static void fadeCheck(){
        currentFadeAmount += fadeAmount;
        if(currentFadeAmount >= 1.00){
            fadeAmount *= -1;
            currentFadeAmount = 1.0f;
            unFading = true;
            fading = false;
        }
        else if(currentFadeAmount <= 0.00f){
            fadeAmount *= -1;
            currentFadeAmount = 0;
            loading = false;
            unFading = false;
        }

        System.out.printf("Fade Amount %f and Current Fade: %f \n",fadeAmount,currentFadeAmount);
    }
    public static void fadeArea(BufferedImage image, float fadeAmount, int spriteLocationX, int spriteLocationY){

    }
}

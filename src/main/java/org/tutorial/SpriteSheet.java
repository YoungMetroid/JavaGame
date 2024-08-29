package org.tutorial;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SpriteSheet {

    public int width, height;

    // sprite pixels, mapped into [0..3] space
    public int[] pixels;
    public SpriteSheet(String path) {
        BufferedImage image;
        InputStream is = getClass().getResourceAsStream(path);
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new Error(e);
        }

        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = new int[this.width * this.height];

        int[] imagePixels = image.getRGB(
                0, 0, image.getWidth(), image.getHeight(),
                null, 0, image.getWidth());

        for (int i = 0; i < this.width * this.height; i++) {
            this.pixels[i] = imagePixels[i];
        }
    }

}

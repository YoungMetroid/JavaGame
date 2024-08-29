package org.tutorial;

import Entities.Entity;
import Levels.TestVillage;
import Tiles.Tree;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.util.Arrays;

public class GamePanel extends JPanel {

    private final BufferedImage image;
    private BufferedImage imageResource;
    private int[] buffer;

    private final Configuration configuration;
    boolean go_right = true;
    float fadeAmount = 0.0f;
    int tick = 0;
    public GamePanel() {
        configuration = new Configuration();
        configuration.setScreenSize(Configuration.screenSize.x6);
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();
        image = graphicsConfiguration.createCompatibleImage(
                Renderer.WIDTH, Renderer.HEIGHT, Transparency.OPAQUE);

        buffer = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        MouseInputs mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }
    public void setPanelSize(){
        Dimension size = new Dimension(
                configuration.getscreenWidth(),
                configuration.getscreenHeight());
        setMinimumSize(size);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for(int i =0; i < Renderer.WIDTH*Renderer.HEIGHT; i++) {
            buffer[i] = Renderer.pixeles[i];
        }

        BufferedImage fadedImage = Renderer.fadeImage(image);
        Image scaledimage = fadedImage.getScaledInstance(configuration.getscreenWidth(),
                configuration.getscreenHeight(),
                Image.SCALE_SMOOTH);
        graphics.drawImage(scaledimage,0,0,null);
        graphics.dispose();
    }

}

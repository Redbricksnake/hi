package teste3dfloor3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author leonardo
 */
public class Textures {

    private BufferedImage[] images;
    
    public BufferedImage shadeWall;
    public BufferedImage shadeCeilFloor;

    public Textures(int maxSize) {
        images = new BufferedImage[maxSize];
        
        // shadeWall
        shadeWall = new BufferedImage(256, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics sg = shadeWall.getGraphics();
        for (int x=0; x<=255; x++) {
            sg.setColor(new Color(0, 0, 0, 255 - x));
            sg.drawLine(x, 0, x, 0);
        }

        // shadeWall ceil floor
        shadeCeilFloor = new BufferedImage(1, 290, BufferedImage.TYPE_INT_ARGB);
        sg = shadeCeilFloor.getGraphics();
        sg.setColor(Color.BLACK);
        sg.fillRect(0, shadeCeilFloor.getHeight() / 2 - 60, 1, 120);
        
        for (int y=60; y<=145; y++) {
            sg.setColor(new Color(0, 0, 0, 255 - (y - 60) * 3));
            sg.drawLine(0, shadeCeilFloor.getHeight() / 2 - y, 0, shadeCeilFloor.getHeight() / 2 - y);
            sg.drawLine(0, shadeCeilFloor.getHeight() / 2 + y, 0, shadeCeilFloor.getHeight() / 2 + y);
        }
    }
    
    public BufferedImage get(int index) {
        try {
            return images[index - 1];
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void loadFromResource(int index, String resource) {
        try {
            images[index - 1] = ImageIO.read(getClass().getResourceAsStream(resource));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void init() {
        loadFromResource(1, "/res/wall.png");
        loadFromResource(2, "/res/wallDarker.png");
        loadFromResource(3, "/res/ceil.jpg");
        loadFromResource(4, "/res/floor.png");
    }
    
}

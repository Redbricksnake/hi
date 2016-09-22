package pkg3dmaze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture {
	public int[] pixels;
	private String loc;
	public final int SIZE;
	
	public Texture(String location, int size) {
		loc = location;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(new File(loc));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
        /* Requirements for textures to work:
        The textures produce an error if the specified size is not the same or larger than the image!!!!!
        Then also if the size specified is larger than the image the image will only use the space it needs to show and the rest is blank.
        Then the last requirement is the that image has to be the same width and height or else it will give you an error or look funny.
        */
/*1*/	public static Texture wood = new Texture("res/wood.png", 64);
/*2*/	public static Texture brick = new Texture("res/redbrick.png", 64);
/*3*/	public static Texture bluestone = new Texture("res/bluestone.png", 64);
/*4*/	public static Texture stone = new Texture("res/greystone.png", 64);
/*5*/   public static Texture win = new Texture("res/win.png", 64);
/*6*/   public static Texture start = new Texture("res/start.png", 64);
/*7*/   public static Texture red = new Texture("res/red.png", 64);
/*8*/   public static Texture blue = new Texture("res/blue.png", 64);
/*9*/   public static Texture green = new Texture("res/green.png", 64);
/*10*/   public static Texture yellow = new Texture("res/yellow.png", 64);
}

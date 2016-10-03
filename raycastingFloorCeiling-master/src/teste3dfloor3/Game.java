package teste3dfloor3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Transparency;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author leonardo
 */
public class Game {

    private static JFrame frame;
    private static Canvas canvas;
    private static BufferStrategy canvasBufferStrategy;
    public static boolean running;
    
    public static void show(int width, int height) {
        frame = new JFrame();
        frame.setTitle("Raycasting test #3 - ceil and floor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.add(canvas = new Canvas());
        frame.setVisible(true);
        
        canvas.requestFocus();
        canvas.addKeyListener(new KeyHandler());
        canvas.createBufferStrategy(2);
        canvasBufferStrategy = canvas.getBufferStrategy();
        
        init();
        
        new Thread(new MainLoop()).start();
    }
    
    private static class MainLoop implements Runnable {
        @Override
        public void run() {
            while (running) {
                Time.update();
                Graphics2D g = (Graphics2D) canvasBufferStrategy.getDrawGraphics();
                g.setBackground(Color.black);
                g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
                update();
                draw(g);
                g.dispose();
                canvasBufferStrategy.show();
                Thread.yield();
            }
        }
    }

    private static class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            Keyboard.setKeyDown(e.getKeyCode(), true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Keyboard.setKeyDown(e.getKeyCode(), false);
        }
        
    }
    
    // ---
    
    public static Textures textures = new Textures(4);
    public static Map currentMap = new Map();
    public static Camera camera;
    
    private static void init() {
        camera = new Camera(Math.toRadians(45), 400, canvas.getWidth());
        textures.init();
        currentMap.init(camera);
        running = true;
    }
    
    private static void update() {
        camera.updateMovement(currentMap);
    }
    
    private static void draw(Graphics2D g) {
        currentMap.draw3DCeilOrFloor(currentMap.ceil, g, camera.height, -1, 800, 600, camera, textures);
        currentMap.draw3DCeilOrFloor(currentMap.floor, g, 50 - camera.height, 1, 800, 600, camera, textures);
        currentMap.draw3DWalls(g, camera);

        // draw level 2D minimap
        g.drawImage(currentMap.floorCeilOffscreenImage, 570, 570, 570 + currentMap.floorCeilOffscreenImage.getWidth() / 2, 570 - currentMap.floorCeilOffscreenImage.getHeight() / 2
                , 0, 0, currentMap.floorCeilOffscreenImage.getWidth(), currentMap.floorCeilOffscreenImage.getHeight(), null);
        
        drawFPS(g);
    }
    
    private static void drawFPS(Graphics2D g) {
        Color clear = new Color(255, 255, 255, 100);
        g.setColor(clear);
        g.fillRect(0, 0, 180, 50);
        g.setColor(Color.GREEN);
        g.drawString("FPS: " + Time.fps, 20, 20);
        g.drawString("Delta time: " + Time.delta, 20, 40);
    }
    
}

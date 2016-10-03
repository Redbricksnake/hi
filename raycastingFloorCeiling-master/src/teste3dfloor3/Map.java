package teste3dfloor3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author leonardo
 */
public class Map {

    public int cellSize;
    public int cols;
    public int rows;
    
    // ceil, floor and walls keep the texture index. 0 = empty
    public int[][] walls;
    public int[][] ceil;
    public int[][] floor;

    public BufferedImage floorCeilOffscreenImage;

    public Map() {
    }
    
    public void init(Camera camera) {
        cellSize = 50;
        cols = 20;
        rows = 20;
        
        walls = new int[][] {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        
        ceil = new int[][] {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
        };

        floor = new int[][] {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
        };
        
        floodFillAuxiliarMap = new int[cols][rows];
    }
    
    public Map(int cellSize, int cols, int rows) {
        this.cellSize = cellSize;
        this.cols = cols;
        this.rows = rows;
        walls = new int[cols][rows];
        ceil = new int[cols][rows];
        floor = new int[cols][rows];
    }

    public int getWall(int x, int y) {
        try {
            return walls[x][y];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    public boolean collidesWall(Rectangle collider) {
        int cx = (int) (collider.getCenterX() / cellSize);
        int cy = (int) (collider.getCenterY() / cellSize);
        for (int y=cy-1; y<=cy+1; y++) {
            for (int x=cx-1; x<=cx+1; x++) {
                int textureIndex = getWall(x, y);
                if (textureIndex > 0) {
                    if (collider.intersects(cellSize * x, cellSize * y, cellSize, cellSize)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void draw3DWalls(Graphics2D g, Camera camera) {
        for (int x=0; x<camera.screenHalfWidth * 2; x+=2) {
            double dAngle = Math.atan((camera.screenHalfWidth - x) / camera.screenDistante);
            Camera.Ray ray = camera.castRay(this, dAngle);
            ray.wallDistance = ray.wallDistance * Math.cos(dAngle);
            double t1 = (camera.height / ray.wallDistance * camera.screenDistante);
            double t2 = ((50.0 - camera.height) / ray.wallDistance * camera.screenDistante);
            int tx = ray.textureU;
            g.drawImage(Game.textures.get(ray.direction), x - 1, (int) (300 - t1), x + 1, (int) (300 + t2), tx, 0, tx + 1, 50, null);
            
            // turn further wall darker
            int dark = (int) (255 * (1 - ray.wallDistance / camera.zfar) * 3);
            dark = dark < 25 ? 25 : dark;
            if (dark >=0 && dark < 256) {
                g.drawImage(Game.textures.shadeWall, x - 1, (int) (300 - t1), x + 1, (int) (300 + t2), dark, 0, dark + 1, 1, null);
            }
        }
    }
    
    // --- flood fill method start ---
    
    private int[][] floodFillAuxiliarMap;
    private int floodFilledValue;
    
    public void clearFoodFill() {
        floodFilledValue++;
    }
    
    public void floodFill(int x, int y) {
        try {
            floodFillAuxiliarMap[x][y] = floodFilledValue;
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public boolean isFloodFilled(int x, int y) {
        try {
            return floodFillAuxiliarMap[x][y] == floodFilledValue;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }
    
    private void drawUsingFloodFillMethod(int[][] map, Graphics2D g, Camera camera, Textures textures, int x, int y) {
        if (!isFloodFilled(x, y)) {
            floodFill(x, y);
            if (!camera.frustrum.intersects(x * cellSize, y * cellSize, cellSize, cellSize)) {
                return;
            }
            int textureIndex =  map[x][y];
            if (textureIndex > 0) {
                g.drawImage(textures.get(textureIndex), x * cellSize, y * cellSize, cellSize, cellSize, null);
            }
            drawUsingFloodFillMethod(map, g, camera, textures, x - 1, y);
            drawUsingFloodFillMethod(map, g, camera, textures, x + 1, y);
            drawUsingFloodFillMethod(map, g, camera, textures, x, y - 1) ;
            drawUsingFloodFillMethod(map, g, camera, textures, x, y + 1) ;
        }
    }
    
    public void draw2DTopViewFloorOrCeil_floodFillMethod(int[][] map, Graphics2D g, Textures textures, Camera camera) {
        clearFoodFill();
        int startMapCol = (int) (camera.x / cellSize);
        int startMapRow = (int) (camera.y / cellSize);
        drawUsingFloodFillMethod(map, g, camera, textures, startMapCol, startMapRow);
    }

    // --- flood fill method end ---

    // --- just a test / it's very not efficient as flood fill method ---
    public void draw2DTopWalls(int[][] map, Graphics2D g, Textures textures, Camera camera) {
        for (int y=0; y<rows; y++) {
            for (int x=0; x<cols; x++) {
                int textureIndex = map[x][y];
                if (textureIndex > 0 && camera.frustrum.intersects(x * cellSize, y * cellSize, cellSize, cellSize)) {
                    //g.drawImage(textures.get(textureIndex), x * cellSize, y * cellSize, cellSize, cellSize, null);
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }
    
    private void drawFloorCeilOffscreenImage(int[][] map, Camera camera, Textures textures) {
        if (floorCeilOffscreenImage == null) {
            floorCeilOffscreenImage = new BufferedImage((int) (camera.frustrumHalfWidth * 2), (int) camera.zfar, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D goi = (Graphics2D) floorCeilOffscreenImage.getGraphics();
        goi.clearRect(0, 0, floorCeilOffscreenImage.getWidth(), floorCeilOffscreenImage.getHeight());
        goi.translate(floorCeilOffscreenImage.getWidth() >> 1, 0);
        goi.rotate(-camera.angle + Math.toRadians(90));
        goi.translate(-camera.x, -camera.y);
        goi.clip(camera.frustrum);
        draw2DTopViewFloorOrCeil_floodFillMethod(map, goi, textures, camera);
        draw2DTopWalls(walls, goi, textures, camera);
        goi.dispose();
    }
    
    public void draw3DCeilOrFloor(int[][] map, Graphics g, double height, int signal, int screenWidth, int screenHeight, Camera camera, Textures textures) {
        drawFloorCeilOffscreenImage(map, camera, textures);
        for (int y=(int) height; y<screenHeight / 2; y++) {
            double z = camera.screenDistante * height / y;
            double frustrumWidthAtZ = Math.tan(camera.fov / 2) * z;
            int x1 = (int) (floorCeilOffscreenImage.getWidth() / 2 - frustrumWidthAtZ);
            int x2 = (int) (floorCeilOffscreenImage.getWidth() / 2 + frustrumWidthAtZ);
            int dy = screenHeight / 2 + signal * y;
            g.drawImage(floorCeilOffscreenImage, 0, dy, screenWidth, dy + 1, x1, (int) z, x2, (int) z + 1, null);
        }
        // turn further ceil floor darker
        g.drawImage(Game.textures.shadeCeilFloor, 0, 155, 800, 445, 0, 0, 1, 290, null);
    }
    
}

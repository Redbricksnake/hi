package teste3dfloor3;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

/**
 *
 * @author leonardo
 */
public class Camera {

    public double fov; // field of view. angle in radians
    public double zfar;
    public double frustrumHalfWidth;
    
    public double screenHalfWidth;
    public double screenDistante;
    
    public double x = 100;
    public double y = 100;
    public double angle;
    public double height = 25;
    private double heightAux = 0;
    
    private AffineTransform frustrumTranform = new AffineTransform();
    public Path2D frustrum = new Path2D.Double();
    
    private Rectangle collider = new Rectangle();
    private int colliderHalfSize = 20;

    public Camera(double fov, double zfar, int screenWidth) {
        this.fov = fov;
        this.zfar = zfar;
        this.frustrumHalfWidth = zfar * Math.tan(fov / 2);
        
        screenHalfWidth = screenWidth / 2;
        screenDistante = screenHalfWidth / Math.tan(fov / 2);

        updateFrustrum();
        updateCollider();
    }
    
    private void updateFrustrum() {
        frustrum.reset();
        frustrum.moveTo(0, 0);
        frustrum.lineTo(zfar, -frustrumHalfWidth);
        frustrum.lineTo(zfar, +frustrumHalfWidth);
        frustrum.closePath();
        
        frustrumTranform.setToIdentity();
        frustrumTranform.translate(x, y);
        frustrumTranform.rotate(angle);
        frustrum.transform(frustrumTranform);
    }
    
    private void updateCollider() {
        collider.setRect(x - colliderHalfSize, y - colliderHalfSize, colliderHalfSize << 1, colliderHalfSize << 1);
    }

    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
        updateFrustrum();
        updateCollider();
    }
    
    public void rotate(double dAngle) {
        angle += dAngle;
        updateFrustrum();
    }
    
    public void forward(Map map, double d) {
        move(map, d, 0);
    }
    
    public void strafe(Map map, double d) {
        move(map, d, -1.5707963267948966); // -90.0 deg = -1.5707963267948966 rad
    }
    
    private void move(Map map, double d, double angleOffset) {
        moved = true;
        
        double dx = d * Math.cos(angle + angleOffset);
        dx = dx > 25 ? 25 : dx;
        translate(dx, 0);
        while (map.collidesWall(collider)) {
            translate(dx > 0 ? -1 : 1, 0);
        }
        
        double dy = d * Math.sin(angle + angleOffset);
        dy = dy > 25 ? 25 : dy;
        translate(0, dy);
        while (map.collidesWall(collider)) {
            translate(0, dy > 0 ? -1 : 1);
        }
    }

    private boolean moved;
    
    private void updateHeight() {
        if (moved) {
            height = 25 + 2 * Math.sin(heightAux);
            heightAux += Time.delta * 0.00000001;
        }
    }
    
    private double velocity = 0.0000001;
    
    public void updateMovement(Map map) {
        moved = false;
        
        if (Keyboard.keyDown[37]) { // left
            strafe(map, -Time.delta * velocity);
        }
        else if (Keyboard.keyDown[39]) { // right
            strafe(map, Time.delta * velocity);
        }

        if (Keyboard.keyDown[38]) { // up 
            forward(map, Time.delta * velocity);
        }
        else if (Keyboard.keyDown[40]) { // down
            forward(map, -Time.delta * velocity);
        }
        
        updateHeight();
        
        if (Keyboard.keyDown[37]) { // Left
            rotate(+Time.delta * 0.000000003);
        }
        else if (Keyboard.keyDown[39]) { // right
            rotate(-Time.delta * 0.000000003);
        }
        if (Keyboard.keyDown[38]) {
            if (Keyboard.keyDown[16]) { // up 
                forward(map, Time.delta * 0.00000010);
            }
        }
    }
    
    
    public static class Ray {
        public int direction;
        public int textureU;
        public double wallDistance;

        public Ray(int direction) {
            this.direction = direction;
        }
    }
    
    private Ray rayX = new Ray(1);
    private Ray rayY = new Ray(2);
    
    public Ray castRay(Map map, double angleOffset) {
        double s = Math.sin(angle + angleOffset);
        double c = Math.cos(angle + angleOffset);
        
        double rayDX = c > 0 
                ? map.cellSize - (x % map.cellSize)
                : -(x % map.cellSize) - 0.0001;
        double rayDX2 = c > 0 ? map.cellSize : -map.cellSize;
        rayX.wallDistance = c == 0 ? Double.MAX_VALUE 
                : increaseRayUntilHitsWall(map, rayDX2, rayDX2 / c * s, x + rayDX, y + (rayDX / c * s), rayX);
        
        double rayDY = s > 0 
                ? map.cellSize - (y % map.cellSize) 
                : -(y % map.cellSize) - 0.0001;
        double rayDY2 = s > 0 ? map.cellSize : -map.cellSize;
        rayY.wallDistance = s == 0 ? Double.MAX_VALUE 
                : increaseRayUntilHitsWall(map, rayDY2 / s * c, rayDY2, x + (rayDY / s * c), y + rayDY, rayY);
        
        return rayX.wallDistance < rayY.wallDistance ? rayX : rayY;
    }
    
    private double increaseRayUntilHitsWall(Map map, double rayDX, double rayDY, double rayX, double rayY, Ray ray) {
        double cellSizeInv = 1.0 / map.cellSize;
        int mapCol = (int) (rayX * cellSizeInv);
        int mapRow = (int) (rayY * cellSizeInv);
        
        while (mapCol >= 0 && mapCol < map.walls.length 
            && mapRow >= 0 && mapRow < map.walls[0].length) { 
            
//        while (frustrum.contains(rayX, rayY)) {
            
            if (map.getWall(mapCol, mapRow) > 0) {
                ray.textureU = Math.abs(rayDY) == map.cellSize 
                        ? (int) (rayX % map.cellSize) 
                        : (int) (rayY % map.cellSize);
                rayDX = rayX - x;
                rayDY = rayY - y;
                return Math.sqrt(rayDX * rayDX + rayDY * rayDY);
            }
            rayX += rayDX;
            rayY += rayDY;
            mapCol = (int) (rayX * cellSizeInv);
            mapRow = (int) (rayY * cellSizeInv);
        }
        return Double.MAX_VALUE;
    }
    
}

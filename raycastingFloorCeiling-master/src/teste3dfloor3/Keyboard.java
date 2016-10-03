package teste3dfloor3;

/**
 *
 * @author leonardo
 */
public class Keyboard {

    public static boolean[] keyDown = new boolean[256];
    
    public static void setKeyDown(int keyCode, boolean down) {
        if (keyCode > keyDown.length - 1) {
            return;
        }
        keyDown[keyCode] = down;
    }
    
}

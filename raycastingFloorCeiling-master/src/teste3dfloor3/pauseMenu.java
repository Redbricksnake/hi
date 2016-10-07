/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste3dfloor3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author 9502999
 */
public class pauseMenu {
    public static void pauseMenu() {
                JFrame pause = new JFrame("Pause Menu");
                pause.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pause.setVisible(true);
                pause.setSize(400, 500);
                JPanel panel = new JPanel();
                panel.setVisible(true);
                pause.add(panel);
                JButton Continue = new JButton("Click Here");
                Continue.setVisible(true);
                panel.add(Continue);
                pause.add(panel);
                Continue.addActionListener(new ActionListener(){
                    
                    public void actionPerformed(ActionEvent e){
                        Game.running = true;
                        Game.runGame();
                    }
                    
                });
    }
}

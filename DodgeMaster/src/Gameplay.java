import javax.swing.*;
import java.awt.*;

public class Gameplay extends javax.swing.JFrame implements Runnable{
    // Attributes {
        // Frame size
    private int width = 1360;
    private int height = 768;
    private Player player;
    // }

    // Constructor
    public Gameplay(String dificult) {
        // Method to fetch initial setup
        initComponents();

        // Keyboard listener
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                player.keyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                player.keyRelease(evt);
            }
        });

        setVisible(true);
        // Buffering
        createBufferStrategy(2);
        Thread t = new Thread(this);
        t.start();
    }

    private void initComponents() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));
        player = new Player(100, 100, 80, 90, 100, 2, "../assets/david_sprite_01.png");
        // Entities
        ImagePanel backgroundPanel = new ImagePanel("../assets/bg_city.png");
        backgroundPanel.add(player.getPlayerPanel());
        backgroundPanel.setLayout(null);  // Allows for absolute positioning
        setContentPane(backgroundPanel);
        pack();
    }

    public void run() {
        while(true) {
            player.move(width, height);
            // Buffer to handle the refresh rate
            try {Thread.sleep(6);} catch (InterruptedException ex) {ex.printStackTrace();}
        }
    }
}
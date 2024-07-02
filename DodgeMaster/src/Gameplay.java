import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Gameplay extends javax.swing.JFrame implements Runnable{
    // Attributes {
    private final Image backgroundImage;
    private Player player;
    // }

    // Constructor
    public Gameplay(String dificulty) {
        // Load the background image
        backgroundImage = new ImageIcon("../assets/bg_gameplayCity.png").getImage();

        // Method to fetch initial setup
        initComponents();

        // Keyboard listener
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {player.keyPressed(evt);}
            public void keyReleased(java.awt.event.KeyEvent evt) {player.keyRelease(evt);}
        });

        // ComponentListener to handle window resizing
        addComponentListener(new ComponentAdapter() {   // Player is relative to the window and difficulty
            public void componentResized(ComponentEvent e) {player.updateSize(getWidth(), getHeight());}
        });

        setVisible(true);
        // Buffering:
        createBufferStrategy(2);
        Thread t = new Thread(this);
        t.start();
    }

    private void initComponents() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1360, 768));

        player = new Player((getWidth()/2), (getHeight()/2), 100, 4, "../assets/david_sprite_00.png");

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);    // Draw the background image, stretching to the window width and height
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.add(player.getPlayerPanel());
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        pack(); // Auto layout management
    }

    // Game loop
    public void run() {
        while(true) {
            player.move(getWidth(), getHeight());

            // Buffer to handle the refresh rate
            try {Thread.sleep(17);} catch (InterruptedException ex) {ex.printStackTrace();}
        }
    }
}
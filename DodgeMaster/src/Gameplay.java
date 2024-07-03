import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Gameplay extends javax.swing.JFrame implements Runnable{
    // Attributes
    private static final int SHIELD_DURATION = 5000;
    private final Image backgroundImage;
    private Player player;
    private Shield shield;
    private Timer shieldTimer;

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
        shield = new Shield(100, 100, 50); // Initial position and diameter

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);    // Draw the background image, stretching to the window width and height
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.add(player.getPlayerPanel());
        backgroundPanel.add(shield.getShieldPanel());
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        pack(); // Auto layout management
    }

    public void activateShield() {
        player.activateShield();
        if (shieldTimer != null) {
            shieldTimer.stop();
        }
        shieldTimer = new Timer(SHIELD_DURATION, e -> deactivateShield());
        shieldTimer.setRepeats(false);
        shieldTimer.start();
    }

    public void deactivateShield() {
        player.deactivateShield();
        shield.setActive(false);
        shield.setVisible(true);
        // Código para reposicionar o escudo ou torná-lo visível novamente
        repositionShield();
    }

    private void repositionShield() {
        // Reposicione o escudo para uma nova posição aleatória
        int newX = (int) (Math.random() * (getWidth() - shield.getWidth()));
        int newY = (int) (Math.random() * (getHeight() - shield.getHeight()));
        shield.setX(newX);
        shield.setY(newY);
        shield.getShieldPanel().setBounds(newX, newY, shield.getWidth(), shield.getHeight());
    }

    // Game loop
    public void run() {
        while(true) {
            player.move(getWidth(), getHeight());

            if (player.getBounds().intersects(shield.getBounds()) && shield.isActive()) {
                activateShield();
                shield.setActive(false);
                shield.setVisible(false);
                shieldTimer = new Timer(SHIELD_DURATION, e -> deactivateShield());
                shieldTimer.setRepeats(false);
                shieldTimer.start();
            }

            // Buffer to handle the refresh rate
            try {Thread.sleep(17);} catch (InterruptedException ex) {ex.printStackTrace();}
        }
    }
}

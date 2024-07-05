import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Gameplay extends JFrame implements Runnable {
    // Attributes
        // Screens
    private final Image backgroundImage;
    private final Image pauseImage;
    private JPanel backgroundPanel;
        // Entities
    private Player player;
    private List<Bullet> bullets;
    private List<Shield> shields;
        //
    private double currentTime;
    private boolean isPaused;

    // Constructor
    public Gameplay(String difficulty) {
        // Load images
        backgroundImage = new ImageIcon("../assets/bg_gameplayCity.png").getImage();
        pauseImage = new ImageIcon("../assets/bg_pauseScreen.png").getImage();

        // Initialize lists
        bullets = new ArrayList<>();
        shields = new ArrayList<>();

        // Initialize components
        initComponents();

        // Keyboard listener for player actions and pause
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_P || evt.getKeyCode() == KeyEvent.VK_ESCAPE)
                    togglePause();
                else
                    player.keyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {player.keyRelease(evt);}
        });

        // ComponentListener to handle window resizing
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                player.updateSize(getWidth(), getHeight());
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1360, 768));

        player = new Player((getWidth() / 2), (getHeight() / 2), 100, 4, "../assets/david_sprite_00.png");

        // Gameplay screen initiation and configuration
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                if (isPaused)
                    g.drawImage(pauseImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);
        backgroundPanel.add(player.getPlayerPanel());
        setContentPane(backgroundPanel);
        pack(); // Auto layout management in case something is missing
    }

    // Game loop
    public void run() {
        // Polymorphic entities
        Bullet bullet = new Bullet(100,100,70,40,1,"../assets/laser_sprite_00.png");
        Shield shield = new Shield(100, 100, 50);

        while (true) {
            if (!isPaused) {
                currentTime += .017;

                /* Bullet logic:
                if (((int) Math.floor(currentTime)) % 2 == 0) {
                    bullets.add(bullet);
                    bullet.spawnGen(player.getX(), player.getY(), player.getWidth(), player.getHeight(), getWidth(), getHeight());
                    backgroundPanel.add(bullet.getBulletPanel());
                    backgroundPanel.repaint();
                }

                for (Bullet i : bullets) {
                    i.move(getWidth(), getHeight());
                    i.hasHit(player.getX(), player.getY(), player.getWidth(), player.getHeight());
                }
                 */

                player.move(getWidth(), getHeight());
            }
            // Buffer to handle the refresh rate
            try { Thread.sleep(17);
            } catch (InterruptedException ex) {ex.printStackTrace();}
        }
    }

    // Other Functions:
    public void togglePause() {
        isPaused = !isPaused;
        revalidate();
        backgroundPanel.repaint();
    }

    /*
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
    */
}
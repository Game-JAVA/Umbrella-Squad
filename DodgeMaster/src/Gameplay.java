import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Gameplay extends JFrame implements Runnable {
    // Attributes
        // Screens
    private final Image backgroundImage;
    private final Image pauseImage;
    private JPanel backgroundPanel;
        // Entities
    private Player player;
    private Hud hud;
    private List<Bullet> bullets;
    private List<Shield> shields;
        //
    private double currentTime;
    private boolean isPaused;
    private Clip clip;

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

        // song play
        playSong("../assets/som_cidade.wav");

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

        player = new Player((getWidth() / 2), (getHeight() / 2), 3, 4, "../assets/david_sprite_00.png");
        hud = new Hud(10,10,100,45,"../assets/hearts_sprite_03.png");

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
        backgroundPanel.add(hud.getHudPanel());
        setContentPane(backgroundPanel);
        pack(); // Auto layout management in case something is missing
    }

    // Game loop
    public void run() {
        while (true) {
            if (!isPaused) {
                currentTime += 17;
                System.out.println(currentTime);

                if (currentTime % 2000 < 17) {
                    Bullet bullet = new Bullet(100, 100, 70, 40, 1, "../assets/laser_sprite_00.png");
                    bullets.add(bullet);
                    bullet.spawnGen(player.getX(), player.getY(), player.getWidth(), player.getHeight(), getWidth(), getHeight());
                    backgroundPanel.add(bullet.getBulletPanel());
                    backgroundPanel.repaint();
                }

                Iterator<Bullet> iterator = bullets.iterator();
                while (iterator.hasNext()) {
                    Bullet bullet = iterator.next();
                    bullet.move(getWidth(), getHeight());
                    if (bullet.hasHit(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
                        player.getHit();
                        hud.setFrame(0);

                        backgroundPanel.remove(bullet.getBulletPanel());
                        iterator.remove(); // Remove the bullet from the list
                    }
                }

                if (currentTime % 200 < 17)
                    hud.setFrame(player.getHealth());
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

    private void playSong(String filePath) {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {e.printStackTrace();}
    }
}

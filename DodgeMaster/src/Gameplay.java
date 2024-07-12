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
    private double currentTime;
    private boolean isPaused;
    private final DifficultySettings.DifficultyConfig config;
    // Screens
    private final Image backgroundImage;
    private final Image pauseImage;
    private JPanel backgroundPanel;
    // Entities
    private Player player;
    private Hud hud;
    private final List<Bullet> bullets;
    private final List<Shield> shields;

    // Constructor
    public Gameplay(String difficulty) {
        // Initialize DifficultySettings
        DifficultySettings difficultySettings = new DifficultySettings(difficulty);
        config = difficultySettings.getCurrentConfig();

        // Initializing components
        backgroundImage = new ImageIcon("../assets/bg_gameplayCity.png").getImage();
        pauseImage = new ImageIcon("../assets/bg_pauseScreen.png").getImage();
        bullets = new ArrayList<>();
        shields = new ArrayList<>();

        // Handling entities
        initComponents();

        // Keyboard listener for player actions and pause
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_P || evt.getKeyCode() == KeyEvent.VK_ESCAPE)
                    togglePause();
                else player.keyPressed(evt);
            }

            public void keyReleased(KeyEvent evt) {
                player.keyRelease(evt);
            }
        });

        // ComponentListener to handle window resizing
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                player.updateSize(getWidth(), getHeight());
            }
        });

        setVisible(true);
        playSong();
        // Buffering
        createBufferStrategy(2);
        Thread t = new Thread(this);
        t.start();
    }

    private void initComponents() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1360, 768));

        player = new Player((getWidth() / 2), (getHeight() / 2), 3, config.getPlayerSpeed(), "../assets/david_sprite_00.png");
        hud = new Hud(10, 10, 100, 45, "../assets/hearts_sprite_03.png");

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

                spawnBullet();
                handleBullets();
                spawnShield();
                handleShields();

                updateHUD();
                player.move(getWidth(), getHeight());
            }
            // Buffer to handle the refresh rate
            try {
                Thread.sleep(17);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void spawnBullet() {
        final int transitionMidTime = 60000; // 1 minuto em milissegundos
        final int transitionLateTime = 180000; // 3 minutos em milissegundos

        int bulletGenPeriod;
        int bulletSpeed;

        if (currentTime < transitionMidTime) {
            bulletGenPeriod = interpolate(
                    config.getBulletInitGenPeriod(),
                    config.getBulletMidGenPeriod(),
                    currentTime / (double) transitionMidTime
            );
            bulletSpeed = interpolate(
                    config.getBulletInitSpeed(),
                    config.getBulletMidSpeed(),
                    currentTime / (double) transitionMidTime
            );
        } else if (currentTime < transitionLateTime) {
            bulletGenPeriod = interpolate(
                    config.getBulletMidGenPeriod(),
                    config.getBulletLateGenPeriod(),
                    (currentTime - transitionMidTime) / (double) (transitionLateTime - transitionMidTime)
            );
            bulletSpeed = interpolate(
                    config.getBulletMidSpeed(),
                    config.getBulletLateSpeed(),
                    (currentTime - transitionMidTime) / (double) (transitionLateTime - transitionMidTime)
            );
        } else {
            bulletGenPeriod = config.getBulletLateGenPeriod();
            bulletSpeed = config.getBulletLateSpeed();
        }

        if (currentTime % bulletGenPeriod < 17) {
            Bullet bullet = new Bullet(100, 100, 80, 30, bulletSpeed, "../assets/laser_sprite_00.png");
            bullets.add(bullet);
            bullet.spawnGen(player.getX(), player.getY(), player.getWidth(), player.getHeight(), getWidth(), getHeight());
            backgroundPanel.add(bullet.getBulletPanel());
            backgroundPanel.repaint();
        }
    }

    private void handleBullets() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.move(getWidth(), getHeight());
            if (bullet.hasHit(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
                handleBulletHit(bullet);
                iterator.remove();
            } else if (bullet.isOutOfBounds(getWidth(), getHeight())) {
                backgroundPanel.remove(bullet.getBulletPanel());
                iterator.remove();
            }
        }
    }

    private void spawnShield() {
        // Use ShieldGenGap from config
        if (currentTime % config.getShieldGenGap() < 17 && shields.isEmpty() && !player.isShielded()) {
            Shield shield = new Shield(0, 0, 30);
            shield.spawnGen(getWidth(), getHeight());
            shields.add(shield);
            backgroundPanel.add(shield.getShieldPanel());
            backgroundPanel.repaint();
        }
    }

    private void handleShields() {
        Iterator<Shield> iterator = shields.iterator();
        while (iterator.hasNext()) {
            Shield shield = iterator.next();
            if (shield.hasHit(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
                player.getShield();
                hud.setFrame(4);
                backgroundPanel.remove(shield.getShieldPanel());
                iterator.remove();
            }
        }
    }

    private void updateHUD() {
        if (currentTime % 200 < 17 && !player.isShielded())
            hud.setFrame(player.getHealth());
    }

    // Other Functions:
    private void handleBulletHit(Bullet bullet) {
        if (player.isShielded())
            player.removeShield();
        else player.getHit();
        hud.setFrame(0);
        backgroundPanel.remove(bullet.getBulletPanel());
    }

    private int interpolate(int startValue, int endValue, double fraction) {
        return (int) (startValue + (endValue - startValue) * fraction);
    }

    public void togglePause() {
        isPaused = !isPaused;
        revalidate();
        backgroundPanel.repaint();
    }

    private void playSong() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("../assets/st_city.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

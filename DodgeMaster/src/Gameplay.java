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

public class Gameplay extends JFrame implements Runnable {
    // Attributes
    private static final int SHIELD_DURATION = 5000;
    private final Image backgroundImage;
    private Player player;
    private boolean isPaused;
    private JPanel pausePanel;
    private Shield shield;
    private Timer shieldTimer;
    private Clip clip;
    private Timer shieldInitTimer;
    private JPanel backgroundPanel;

    public Gameplay(String difficulty) {
        // Load the background image
        backgroundImage = new ImageIcon("../assets/bg_gameplayCity.png").getImage();

        // Initialize components
        initComponents();

        // song play
        tocarMusica("../assets/som_cidade.wav");

        // Keyboard listener for player actions and pause
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_P) {
                    togglePause();
                } else {
                    player.keyPressed(evt);
                }
            }

            public void keyReleased(KeyEvent evt) {
                player.keyRelease(evt);
            }
        });

        // ComponentListener to handle window resizing
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                player.updateSize(getWidth(), getHeight());
                pausePanel.setBounds(0, 0, getWidth(), getHeight()); // Adjust pausePanel bounds on resize
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

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image, stretching to the window width and height
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.add(player.getPlayerPanel());
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        pack(); // Auto layout management

        // Create the pause panel
        pausePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the pause image, stretching to the window width and height
                Image pauseImage = new ImageIcon("../assets/PauseScreen.JPEG").getImage();
                g.drawImage(pauseImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pausePanel.setOpaque(false);
        pausePanel.setVisible(false); // Initially invisible
        pausePanel.setBounds(0, 0, getWidth(), getHeight());
        backgroundPanel.add(pausePanel);

        shieldInitTimer = new Timer(20000, e -> initializeShield());
        shieldInitTimer.setRepeats(false);
        shieldInitTimer.start();
    }

    private void initializeShield() {
        shield = new Shield(100, 100, 50); // Posicionamento inicial do escudo
        backgroundPanel.add(shield.getShieldPanel());
        shield.getShieldPanel().setBounds(shield.getX(), shield.getY(), shield.getWidth(), shield.getHeight());
        backgroundPanel.repaint();
    }

    private void tocarMusica(String caminhoArquivo) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(caminhoArquivo).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Faz a música tocar em loop
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void togglePause() {
        isPaused = !isPaused;
        pausePanel.setVisible(isPaused);
        if (isPaused) {
            clip.stop(); // Para a música quando o jogo é pausado
        } else {
            clip.start(); // Retoma a música quando o jogo é retomado
        }
        if (isPaused) {
            requestFocus(); // Ensure the gameplay window retains focus
        }
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
        repositionShield();
    }

    private void repositionShield() {
        int newX = (int) (Math.random() * (getWidth() - shield.getWidth()));
        int newY = (int) (Math.random() * (getHeight() - shield.getHeight()));
        shield.setX(newX);
        shield.setY(newY);
        shield.getShieldPanel().setBounds(newX, newY, shield.getWidth(), shield.getHeight());
        shield.setActive(true); // Garante que o escudo seja ativado
        shield.setVisible(true); // Garante que o escudo seja visível
    }

    // Game loop
    public void run() {
        while (true) {
            if (!isPaused) {
                player.move(getWidth(), getHeight());

                try {
                    Thread.sleep(17);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                if (shield != null && player.getBounds().intersects(shield.getBounds()) && shield.isActive()) {
                    activateShield();
                    shield.setActive(false);
                    shield.setVisible(false);
                }

            } else {
                try {
                    Thread.sleep(100); // Reduce CPU usage while paused
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

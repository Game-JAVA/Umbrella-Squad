import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gameplay extends JFrame implements Runnable {
    private final Image backgroundImage;
    private Player player;
    private boolean isPaused;
    private JPanel pausePanel;

    public Gameplay(String difficulty) {
        // Load the background image
        backgroundImage = new ImageIcon("../assets/bg_gameplayCity.png").getImage();

        // Initialize components
        initComponents();

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

        JPanel backgroundPanel = new JPanel() {
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
        add(pausePanel); // Add pausePanel to the JFrame directly
        setComponentZOrder(pausePanel, 0); // Ensure pausePanel is always on top
    }

    public void togglePause() {
        isPaused = !isPaused;
        pausePanel.setVisible(isPaused);
        if (isPaused) {
            requestFocus(); // Ensure the gameplay window retains focus
        }
    }

    // Game loop
    public void run() {
        while (true) {
            if (!isPaused) {
                player.move(getWidth(), getHeight());

                // Buffer to handle the refresh rate
                try {
                    Thread.sleep(17);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Pause logic
                try {
                    Thread.sleep(100); // Reduce CPU usage while paused
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

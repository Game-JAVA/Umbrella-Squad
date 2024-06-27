import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Player extends Rectangle {
    // Attributes
    private int health;
    private int speedIndex;
    private JPanel playerPanel;         // Turning the player in its own panel
    private BufferedImage playerImage;  // Buffered image to it's sprites
    private Set<Integer> pressedKeys = new HashSet<>(); // Buffering inputs to smother movement

    // Constructor
    public Player(int x, int y, int width, int height, int health, int speedIndex, String imagePath) {
        super(x, y, width, height);
        this.health = health;
        this.speedIndex = speedIndex;

        // Load player image
        try { playerImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {e.printStackTrace();}

        // Initialize the player panel
        playerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        playerPanel.setOpaque(false);
        playerPanel.setBounds(x, y, width, height);
    }
    // }

    // Methods {
    @Override
    public void move(int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);
        playerPanel.setLocation(getX(), getY());
    }

    public void draw(Graphics g) {g.drawImage(playerImage, 0, 0, getWidth(), getHeight(), null);}

    // Recognize key press
    public void keyPressed(KeyEvent key) {
        pressedKeys.add(key.getKeyCode());
        updateSpeed();
    }

    // Recognize key release
    public void keyRelease(KeyEvent key) {
        pressedKeys.remove(key.getKeyCode());
        updateSpeed();
    }

    private void updateSpeed() {
        int speedX = 0;
        int speedY = 0;

        if (pressedKeys.contains(KeyEvent.VK_UP))       {speedY -= speedIndex;}
        if (pressedKeys.contains(KeyEvent.VK_DOWN))     {speedY += speedIndex;}
        if (pressedKeys.contains(KeyEvent.VK_LEFT))     {speedX -= speedIndex;}
        if (pressedKeys.contains(KeyEvent.VK_RIGHT))    {speedX += speedIndex;}

        super.setSpeedX(speedX);
        super.setSpeedY(speedY);
    }
    // }

    // Getters and Setters
    public int getHealth() {return health;}
    public double getSpeedIndex() {return speedIndex;}
    public JPanel getPlayerPanel() {return playerPanel;}
    public BufferedImage getPlayerImage() {return playerImage;}

    public void setHealth(int health) {this.health = health;}
    public void setSpeedIndex(int speedIndex) {this.speedIndex = speedIndex;}
    public void setPlayerPanel(JPanel playerPanel) {this.playerPanel = playerPanel;}
    public void setPlayerImage(BufferedImage playerImage) {this.playerImage = playerImage;}
    // }

}

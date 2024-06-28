import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class Player extends Rectangle {
    // Attributes
    private int health;
    private int speedIndex;
    private JPanel playerPanel;         // Turning the player in its own panel
    private BufferedImage playerImage;  // Buffered image to it's sprites
    private Stack<Integer> xKeys = new Stack<>();   // A stack for each axis of movement
    private Stack<Integer> yKeys = new Stack<>();   // *Vertical keys stack

    // Constructor
    public Player(int x, int y, int width, int height, int health, int speedIndex, String imagePath) {
        super(x, y, width, height);
        this.health = health;
        this.speedIndex = speedIndex;

        // Load player default image
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
        playerPanel.setOpaque(false);   // Handle transparency
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

    // Movement Section {
        // Recognize key press then add the key to its axis stack
    public void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                if (!xKeys.contains(key.getKeyCode())) {
                    xKeys.push(key.getKeyCode());
                } break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                if (!yKeys.contains(key.getKeyCode())) {
                    yKeys.push(key.getKeyCode());
                } break;
        }
        updateSpeed();
    }

        // Recognize key release then remove the key from its axis stack
    public void keyRelease(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                xKeys.remove((Integer) key.getKeyCode());
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                yKeys.remove((Integer) key.getKeyCode());
                break;
        }
        updateSpeed();
    }

    /* The most recent key pressed in an axis is meant to be prioritized, this function take the top one and sums to
    * a local variable to manage which direction the player must be: if I press only right the var ends as '+5' for
    * example, and then it's assigned to the player speed attribute.
    * */
    private void updateSpeed() {
        int speedX = 0;
        int speedY = 0;

        if (!xKeys.isEmpty()) {
            int recentXKey = xKeys.peek();
            if (recentXKey == KeyEvent.VK_LEFT || recentXKey == KeyEvent.VK_A)
                speedX = -speedIndex;
            else if (recentXKey == KeyEvent.VK_RIGHT || recentXKey == KeyEvent.VK_D)
                speedX = speedIndex;
        }

        if (!yKeys.isEmpty()) {
            int recentYKey = yKeys.peek();
            if (recentYKey == KeyEvent.VK_UP || recentYKey == KeyEvent.VK_W)
                speedY = -speedIndex;
            else if (recentYKey == KeyEvent.VK_DOWN || recentYKey == KeyEvent.VK_S)
                speedY = speedIndex;
        }

        super.setSpeedX(speedX);
        super.setSpeedY(speedY);
    }
    // }}

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


    @Override
    public String toString() {
        return super.toString() + " Player{" +
                "health=" + health +
                ", speedIndex=" + speedIndex +
                '}';
    }
}

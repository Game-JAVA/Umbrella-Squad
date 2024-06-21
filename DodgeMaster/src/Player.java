import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Rectangle {
    // Attributes
    private int health;
    private int speedIndex;
    private JPanel playerPanel;         // Turning the player in it's own panel
    private BufferedImage playerImage;  // Buffered image to it's sprites

    // Constructor
    public Player(int x, int y, int speedX, int speedY, int width, int height, int health, int speedIndex, String imagePath) {
        // Basic constructor
        super(x, y, speedX, speedY, width, height);
        this.health = health;
        this.speedIndex = speedIndex;

        // Load player image
        try {
            playerImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {e.printStackTrace();}

        // Initialize the player panel
        playerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (playerImage != null)  // Verifies if the player was fully drawn, then adjust the image to it's size
                    g.drawImage(playerImage, 0, 0, getWidth(), getHeight(), null);
                else {
                    g.setColor(Color.RED); // Fallback in case image fails to load
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
    }
    // }

    // Methods
        // Recognize key press
    public void keyPressed (KeyEvent tecla){
        int code = tecla.getKeyCode();

        if (code == KeyEvent.VK_UP) {super.setSpeedY(-speedIndex);}
        if (code == KeyEvent.VK_DOWN) {super.setSpeedY(speedIndex);}
        if (code == KeyEvent.VK_RIGHT) {super.setSpeedX(speedIndex);}
        if (code == KeyEvent.VK_LEFT) {super.setSpeedX(-speedIndex);}
    }

        // Recognize key release
    public void keyRelease (KeyEvent tecla){
        int code = tecla.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {super.setSpeedY(0);}
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_LEFT) {super.setSpeedX(0);}
    }
    // }

    // Getters and Setters
    public int getHealth() {return health;}
    public int getSpeedIndex() {return speedIndex;}
    public JPanel getPlayerPanel() {return playerPanel;}
    public BufferedImage getPlayerImage() {return playerImage;}

    public void setHealth(int health) {this.health = health;}
    public void setSpeedIndex(int speedIndex) {this.speedIndex = speedIndex;}
    public void setPlayerPanel(JPanel playerPanel) {this.playerPanel = playerPanel;}
    public void setPlayerImage(BufferedImage playerImage) {this.playerImage = playerImage;}
    // }

    // toString

    @Override
    public String toString() {
        return super.toString() + " Player{" +
                "health=" + health +
                ", speedIndex=" + speedIndex +
                '}';
    }
}

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
    private JPanel playerPanel;         // Turning the player in its own panel
    private BufferedImage playerImage;  // Buffered image to it's sprites

    // Constructor
    public Player(int x, int y, int speedX, int speedY, int width, int height, int health, int speedIndex, String imagePath) {
        super(x, y, speedX, speedY, width, height);
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

    // Methods
    @Override
    public void move(int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);
        playerPanel.setLocation(getX(), getY());
    }

    public void draw(Graphics g) {
        g.drawImage(playerImage, 0, 0, getWidth(), getHeight(), null);
    }

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

        if (code == KeyEvent.VK_UP) {super.setSpeedY(0);}
        if (code == KeyEvent.VK_DOWN) {super.setSpeedY(0);}
        if (code == KeyEvent.VK_RIGHT) {super.setSpeedX(0);}
        if (code == KeyEvent.VK_LEFT) {super.setSpeedX(0);}
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

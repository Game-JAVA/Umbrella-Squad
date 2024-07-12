import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Shield extends Rectangle {
    private final JPanel shieldPanel;
    private final BufferedImage shieldImage;

    public Shield(int x, int y, int size, String imagePath) {
        super(x, y, size, size);
        shieldImage = loadImage(imagePath); // Load default image
        shieldPanel = createPanel(); // Initialize the panel
    }

    public void spawnGen(int screenWidth, int screenHeight) {
        Random r = new Random();

        int xMin = (screenWidth * 4) / 100;
        int xMax = (screenWidth - (screenWidth * 6) / 100);
        int yMin = (screenHeight * 2) / 100;
        int yMax = (screenHeight - (screenHeight * 9) / 100);

        setX(r.nextInt((xMax - getWidth()) - xMin + 1) + xMin);
        setY(r.nextInt((yMax - getHeight()) - yMin + 1) + yMin);
        shieldPanel.setLocation(getX(), getY());
    }

    public boolean hasHit(int playerX, int playerY, int playerWidth, int playerHeight) {
        int shieldRight = getX() + getWidth();
        int shieldBottom = getY() + getHeight();
        int playerRight = playerX + playerWidth;
        int playerBottom = playerY + playerHeight;

        return getX() < playerRight && shieldRight > playerX && getY() < playerBottom && shieldBottom > playerY;
    }

    public JPanel getShieldPanel() {return shieldPanel;}
}
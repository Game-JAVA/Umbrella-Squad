import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bullet extends Rectangle {
    // Attributes
    private final JPanel bulletPanel;
    private final BufferedImage bulletImage;
    private int speedIndex;
    private int spawnSide;
    private boolean isVertical;

    // Constructor
    public Bullet(int x, int y, int width, int height, int speedIndex,String imagePath) {
        super(x, y, width, height);
        this.speedIndex = speedIndex;
        bulletImage = loadImage(imagePath); // Load default image
        bulletPanel = createPanel(); // Initialize the panel
    }

    // Methods:
    @Override
    public void move(int screenWidth, int screenHeight) {
        setX(getX()+getSpeedX());
        setY(getY()+getSpeedY());
        bulletPanel.setLocation(getX(), getY());
    }

    public boolean isOutOfBounds(int screenWidth, int screenHeight) {
        return getX() < (-getWidth()*2) || getX() > (screenWidth+getWidth()*2)
                || getY() < (-getHeight()*2) || getY() > (screenHeight+getHeight()*2);
    }

    public void spawnGen(int playerX, int playerY, int playerW, int playerH, int screenW, int screenH) {
        Random r = new Random();
        setSpawnSide(r.nextInt(4)+1);
        if (getSpawnSide()%2 != 0)
            isVertical();

        int verticalMax = playerX+playerW-(getWidth()/2);
        int verticalMin = playerX-(getWidth()/2);
        int horizontalMax = playerY+playerH-(getHeight()/2);
        int horizontalMin = playerY-(getHeight()/2);

        /*  Each bullet will have the chance to spawn randomly or focused, due game design, 70% of them will focus
         * the player that is them generating in a certain range of your current location, these chances may differ
         * when balancing the game
         */
        if (r.nextInt(10)+1 <= 5) {
            switch (getSpawnSide()) { // Targeting the player
                case 1: // Spawns at North
                    setY(-getHeight());
                    setX(r.nextInt(verticalMax-verticalMin+1)+verticalMin);
                    break;
                case 2: // Spawns at East
                    setX(screenW);
                    setY(r.nextInt(horizontalMax-horizontalMin+1)+horizontalMin);
                    break;
                case 3: // Spawns at South
                    setY(screenH);
                    setX(r.nextInt(verticalMax-verticalMin+1)+verticalMin);
                    break;
                case 4: // Spawns at West
                    setX(-getWidth());
                    setY(r.nextInt(horizontalMax-horizontalMin+1)+horizontalMin);
                    break;
            }
        } else {
            switch (getSpawnSide()) { // Full side spawn
                case 1: // Spawns at North
                    setY(-getHeight());
                    setX(r.nextInt(screenW-getWidth()));
                    break;
                case 2: // Spawns at East
                    setX(screenW);
                    setY(r.nextInt(screenH-getHeight()));
                    break;
                case 3: // Spawns at South
                    setY(screenH);
                    setX(r.nextInt(screenW-getWidth()));
                    break;
                case 4: // Spawns at West
                    setX(-getWidth());
                    setY(r.nextInt(screenH-getHeight()));
                    break;
            }
        }

        // Setting speed values
        switch (getSpawnSide()) {
            case 1: // Spawns at North
                setSpeedX(0);
                setSpeedY(speedIndex);
                break;
            case 2: // Spawns at East
                setSpeedX(-speedIndex);
                setSpeedY(0);
                break;
            case 3: // Spawns at South
                setSpeedX(0);
                setSpeedY(-speedIndex);
                break;
            case 4: // Spawns at West
                setSpeedX(speedIndex);
                setSpeedY(0);
                break;
        }
    }

    // Checks player coordinates and if the bullet is anywhere inside it's bounds
    public boolean hasHit(int playerX, int playerY, int playerWidth, int playerHeight) {
        int bulletRight = getX() + getWidth();
        int bulletBottom = getY() + getHeight();
        int playerRight = playerX + playerWidth;
        int playerBottom = playerY + playerHeight;

        return getX() < playerRight && bulletRight > playerX && getY() < playerBottom && bulletBottom > playerY;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Handle rotation of the image by drawing it as a 2d graph

        if (isVertical) {
            g2d.rotate(Math.PI/2, (double) getWidth() /2, (double) getHeight() /2); // Rotates 90º clockwise
            g2d.drawImage(bulletImage, 0, 0, getWidth(), getHeight(), null);
        } else g.drawImage(bulletImage, 0, 0, getWidth(), getHeight(), null);
    }

    // Getters and Setters:
    public JPanel getBulletPanel() {return bulletPanel;}
    public int getSpawnSide() {return spawnSide;}

    public void setSpawnSide(int direction) {spawnSide = direction;}
    public void isVertical() {isVertical= true;}

    // toString
    @Override
    public String toString() {
        return super.toString() + " Bullet{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", speedX=" + getSpeedX() +
                ", speedY=" + getSpeedY() +
                '}';
    }
}
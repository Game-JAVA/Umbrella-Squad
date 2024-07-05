import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Bullet extends Rectangle {
    // Attributes
    private final int damage;
    private final JPanel bulletPanel;
    private BufferedImage bulletImage;
    private int spawnSide;
    private boolean isVertical;

    // Constructor
    public Bullet(int x, int y, int width, int height, int damage, String imagePath) {
        super(x, y, width, height);
        this.damage = damage;

        try { bulletImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {e.printStackTrace();}

        bulletPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        bulletPanel.setOpaque(false);
        bulletPanel.setBounds(x, y, width, height);
    }

    // Methods:
    public void move(int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);
        bulletPanel.setLocation(getX(), getY());
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
        if (r.nextInt(10)+1 <= 7) {
            switch (getSpawnSide()) {
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
                    setX(r.nextInt(verticalMax-verticalMin+1)+verticalMin);
                    break;
            }
        } else {
            switch (getSpawnSide()) {
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
    }

    // Checks player coordinates and if the bullet is anywhere inside it's bounds
    public boolean hasHit(int playerX, int playerY, int playerWidth, int playerHeight) {
        return playerX > getX() && (playerX + playerWidth) < getX()
                && playerY > getY() && (playerY + playerHeight) < getY();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Handle rotation of the image by drawing it as a 2d graph

        if (isVertical) {
            g2d.rotate(Math.PI/2,getWidth()/2,getHeight()/2); // Rotates 90º clockwise
            g2d.drawImage(bulletImage, (getHeight()-getWidth())/2, (getWidth()-getHeight())/2, getWidth(),
                    getHeight(), null);
            g2d.rotate(-Math.PI/2,getWidth()/2,getHeight()/2);
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
                ", damage=" + damage +
                '}';
    }
}
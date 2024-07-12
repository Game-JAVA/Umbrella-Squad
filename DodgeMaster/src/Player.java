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
    private final int speedIndex;
        // Structure and movement variables:
    private final JPanel playerPanel;           // Turning the player in its own panel
    private BufferedImage playerImage;          // Buffered image to it's sprites
    private final Stack<Integer> xKeys = new Stack<>();   // A stack for each axis of movement
    private final Stack<Integer> yKeys = new Stack<>();   // *Vertical keys stack
    private boolean isMoving = false;
    private boolean isFacingLeft = false;
        // Animation handling variables:
    private int frameUpdate = 0;
    private int frameIndex = 0;
        // Shield parameters:
    private boolean haveShield;


    // Constructor
    public Player(int x, int y, int health, int speedIndex, String imagePath) {
        super(x, y);
        this.health = health;
        this.speedIndex = speedIndex;
        playerImage = loadImage(imagePath); // Load default image
        playerPanel = createPanel(); // Initialize the panel
    }
    // }

    // Methods {
    @Override
    public void move(int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);
        playerPanel.setLocation(getX(), getY());

        // Setting custom bounds to the player
        // Changing a fix value to a formula, so then it keeps proportional to the panel size
        if (super.getX() < (screenWidth*4)/100) {
            super.setX((screenWidth*4)/100);
            super.setSpeedX(0);
        } else if (super.getX() + super.getWidth() > (screenWidth - (screenWidth*6)/100)) {
            super.setX((screenWidth - (screenWidth*6)/100) - super.getWidth());
            super.setSpeedX(0);
        }

        if (super.getY() < (screenHeight*2)/100) {
            super.setY((screenHeight*2)/100);
            super.setSpeedY(0);
        } else if (super.getY() + super.getHeight() > (screenHeight - (screenHeight*9)/100)) {
            super.setY((screenHeight - (screenHeight*9)/100) - super.getHeight());
            super.setSpeedY(0);
        }

        // Player walking animation:
        if (isMoving()) {
            frameUpdate = (frameUpdate+1)%7;
            if (frameUpdate == 6)
                frameIndex = (frameIndex+1)%8;
        } else
            frameIndex = 0;
        setFrame(frameIndex);
        playerPanel.setLocation(getX(),getY());
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Handle mirroring the image by drawing it as a 2d graph
        if (isFacingLeft)
            g2d.drawImage(playerImage, getWidth(), 0, -getWidth(), getHeight(), null); // Draw mirrored image
        else
            g2d.drawImage(playerImage, 0, 0, getWidth(), getHeight(), null);
    }

    // Keeping proportion of the player to the screen
    public void updateSize(int newScreenWidth, int newScreenHeight) {
        int playerWidth = (newScreenWidth*5)/100;
        int playerHeight = (newScreenHeight*11)/100;
        setWidth(playerWidth);
        setHeight(playerHeight);
        playerPanel.setBounds(playerPanel.getX(), playerPanel.getY(), playerWidth, playerHeight);
        playerPanel.revalidate();
        playerPanel.repaint();
    }

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
        isMoving = false; // Starts as false, then start moving if there is an input for it

        if (!xKeys.isEmpty()) {
            isMoving = true;
            int recentXKey = xKeys.peek();
            if (recentXKey == KeyEvent.VK_LEFT || recentXKey == KeyEvent.VK_A)
                speedX = -speedIndex;
            else if (recentXKey == KeyEvent.VK_RIGHT || recentXKey == KeyEvent.VK_D)
                speedX = speedIndex;
        }

        if (!yKeys.isEmpty()) {
            isMoving = true;
            int recentYKey = yKeys.peek();
            if (recentYKey == KeyEvent.VK_UP || recentYKey == KeyEvent.VK_W)
                speedY = -speedIndex;
            else if (recentYKey == KeyEvent.VK_DOWN || recentYKey == KeyEvent.VK_S)
                speedY = speedIndex;
        }

        if (xKeys.isEmpty() && yKeys.isEmpty())
            stopMoving();

        if (speedX < 0)
            isFacingLeft = true;
        else if (speedX > 0)
            isFacingLeft = false;

        super.setSpeedX(speedX);
        super.setSpeedY(speedY);
    }

    // Getters and Setters:
    public JPanel getPlayerPanel() {return playerPanel;}
    public boolean isMoving() {return isMoving;}
    public boolean isShielded() {return haveShield;}
    public int getHealth() {return health;}
    public void getShield() {haveShield = true;}

    public void stopMoving() {isMoving = false;}
    public void removeShield() {haveShield = false;}
    public void getHit() {health--;}
    public void setFrame(int frameNumber) {
        if (haveShield) {
            try {
                String imagePath = String.format("../assets/shielded_sprite_%02d.png", frameNumber);
                playerImage = ImageIO.read(new File(imagePath));
            } catch (IOException e) {e.printStackTrace();}
            playerPanel.repaint();
        } else {
            try {
                String imagePath = String.format("../assets/david_sprite_%02d.png", frameNumber);
                playerImage = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            playerPanel.repaint();
        }
    }

    // }

    @Override
    public String toString() {
        return super.toString() + " Player{" +
                "health=" + health +
                ", speedIndex=" + speedIndex +
                '}';
    }
}
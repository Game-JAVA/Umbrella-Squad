import java.awt.event.KeyEvent;

public class Player extends Rectangle {
    // Attributes
    private int health;
    private int speedIndex;

    // Constructor
    public Player(int x, int y, int speedX, int speedY, int width, int height, int health, int speedIndex) {
        super(x, y, speedX, speedY, width, height);
        this.health = health;
        this.speedIndex = speedIndex;
    }

        // Essential attributes
    public Player(int health, int speedIndex) {
        this.health = health;
        this.speedIndex = speedIndex;
    }

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

        if (code == KeyEvent.VK_UP) {super.setSpeedY(0);}
        if (code == KeyEvent.VK_DOWN) {super.setSpeedY(0);}
        if (code == KeyEvent.VK_RIGHT) {super.setSpeedX(0);}
        if (code == KeyEvent.VK_LEFT) {super.setSpeedX(0);}
    }
    // }

    // Getters and Setters
    public int getHealth() {return health;}
    public int getSpeedIndex() {return speedIndex;}

    public void setHealth(int health) {this.health = health;}
    public void setSpeedIndex(int speedIndex) {this.speedIndex = speedIndex;}

    // toString
    @Override
    public String toString() {
        return super.toString() + " Player{" +
                "health=" + health +
                '}';
    }
}

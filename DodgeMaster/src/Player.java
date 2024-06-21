import java.awt.event.KeyEvent;

public class Player extends Rectangle {
    // Attributes
    private int health;

    // Constructor
    public Player() {
        this.health = health;
    }

    // Methods
        // Recognize key press
    public void keyPressed (KeyEvent tecla){
        int code = tecla.getKeyCode();

        if (code == KeyEvent.VK_UP) {super.setSpeedY(-2);}
        if (code == KeyEvent.VK_DOWN) {super.setSpeedY(2);}
        if (code == KeyEvent.VK_RIGHT) {super.setSpeedX(2);}
        if (code == KeyEvent.VK_LEFT) {super.setSpeedX(-2);}
    }

        // Recognize key release
    public void keyRelease (KeyEvent tecla){
        int code = tecla.getKeyCode();

        if (code == KeyEvent.VK_UP) {super.setSpeedY(-2);}
        if (code == KeyEvent.VK_DOWN) {super.setSpeedY(2);}
        if (code == KeyEvent.VK_RIGHT) {super.setSpeedX(2);}
        if (code == KeyEvent.VK_LEFT) {super.setSpeedX(-2);}
    }
    // }

    // Getter and Setter
    public int getHealth() {return health;}
    public void setHealth(int health) {this.health = health;}

    // toString
    @Override
    public String toString() {
        return super.toString() + " Player{" +
                "health=" + health +
                '}';
    }
}

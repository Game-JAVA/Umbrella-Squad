import java.awt.*;

public class Bullet extends Rectangle {
    // Additional attributes for Bullet
    private int damage;


    // Constructors
    public Bullet(int x, int y, int speedX, int speedY, int width, int height, int damage) {
        super(x, y, speedX, speedY, width, height);
        this.damage = damage;
    }

    public Bullet() {
        this.damage = 10; // default damage
    }

    //Methods
    @Override
    public void move(int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);
    }

    // Getters and Setters for additional attributes
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Overriding toString to include new attributes
    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", speedX=" + getSpeedX() +
                ", speedY=" + getSpeedY() +
                ", damage=" + damage +
                '}';
    }
}

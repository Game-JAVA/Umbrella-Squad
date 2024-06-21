import java.awt.*;

public abstract class Bullet extends Shape {
    // Additional attributes for Bullet
    private int damage;

    public abstract void draw(Graphics g);


    // Constructors
    public Bullet(int x, int y, int speedX, int speedY, int damage) {
        super(x, y, speedX, speedY);
        this.damage = damage;
    }

    public Bullet() {
        super();
        this.damage = 10; // default damage
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

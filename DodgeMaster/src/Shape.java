import java.awt.*;
import java.util.Random;

public abstract class Shape {
    // Attributes
    private int x;
    private int y;
    private int speedX;
    private int speedY;

    // Constructors {
    public Shape(int x, int y, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

        // No signatures overload
    public Shape() {
        this.x = 100;
        this.y = 100;
        this.speedX = 5;
        this.speedY = 5;
    }
    // }

    // Constructors {
    public void move() {
        this.x += speedX;
        this.y += speedY;
    }
    // Signatures to handle collision treatment
    protected void move(int screenWidth, int screenHeight) {
        this.x += speedX;
        this.y += speedY;
    }

    public abstract void draw(Graphics g);
    // }

    // Getters and Setters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSpeedX() {
        return speedX;
    }
    public int getSpeedY() {
        return speedY;
    }

    public void setX(int posX) {
        this.x = posX;
    }
    public void setY(int posY) {
        this.y = posY;
    }
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    //ToString
    @Override
    public String toString() {
        return "Shape{" +
                "posX=" + x +
                ", posY=" + y +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                '}';
    }
}


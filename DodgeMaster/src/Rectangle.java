import java.awt.*;

public class Rectangle extends Shape {
    // Attributes
    private int width;
    private int height;

    // Constructor {
    public Rectangle(int x, int y, int speedX, int speedY, int width, int height) {
        super(x, y, speedX, speedY);
        this.width = width;
        this.height = height;
    }

        // Simple Constructor
    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

        // Player Constructor
    public Rectangle(int x, int y) {
        super(x, y);
        this.width = 80;
        this.height = 90;
    }
    // }

    // Methods:
    @Override
    public void move(int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);

        // Collision Treatment with the window border
        if (super.getX() < 0) {
            super.setX(0);
            super.setSpeedX(0);
        } else if (super.getX() + this.width > screenWidth) {
            super.setX(screenWidth - this.width);
            super.setSpeedX(0);
        }

        if (super.getY() < 0) {
            super.setY(0);
            super.setSpeedY(0);
        } else if (super.getY() + this.height > screenHeight) {
            super.setY(screenHeight - this.height);
            super.setSpeedY(0);
        }
    }


    public void draw(Graphics g) {
        g.fillRect(super.getX(), super.getY(), this.width, this.height);
    }

    // Getters and Setters:
    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}

    // toString
    @Override
    public String toString() {
        return super.toString() + " Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
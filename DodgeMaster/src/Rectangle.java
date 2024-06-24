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

        // No signatures overload
    public Rectangle() {
        this.width = 100;
        this.height = 100;
    }
    // }

    // Methods {
    @Override
    public void move(int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);

        // Collision Treatment with the window border
        if (super.getX() < 0 || super.getX() + this.width > screenWidth)
            super.setSpeedX(super.getSpeedX() * -1);
        if (super.getY() < 0 || super.getY() + this.height > screenHeight)
            super.setSpeedY(super.getSpeedY() * -1);
    }

    public void draw(Graphics g) {
        g.fillRect(super.getX(), super.getY(), this.width, this.height);
    }
    // }

    // Getters and Setters
    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}

    //ToString
    @Override
    public String toString() {
        return super.toString() + " Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Rectangle extends Shape {
    // Attributes
    private int width;
    private int height;
    private BufferedImage image;

    // Constructor {
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
    public BufferedImage loadImage(String imagePath) {
        try { return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        panel.setOpaque(false); // Handle transparency
        panel.setBounds(getX(),  getY(), width, height);
        return panel;
    }

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

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.image, super.getX(), super.getY(), this.width, this.height, null);
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

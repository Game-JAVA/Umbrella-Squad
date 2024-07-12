import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hud extends Rectangle {
    private JPanel hudPanel;
    private BufferedImage hudImage;
    private int score;

    // Constructor
    public Hud(int x, int y, int width, int height, String imagePath) {
        super(x, y, width, height);

        try {
            hudImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        hudPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (hudImage != null) {
                    g.drawImage(hudImage, 0, 0, getWidth(), getHeight(), null);
                }
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.setColor(Color.WHITE);
            }
        };
        hudPanel.setOpaque(false);
        hudPanel.setBounds(x, y, width, height);
    }

    // Update the image
    public void setFrame(int frameNumber) {
        try {
            String imagePath = String.format("../assets/hearts_sprite_%02d.png", frameNumber);
            hudImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hudPanel.repaint();
    }

    public JPanel getHudPanel() {
        return hudPanel;
    }

    public void addScore(int points) {
        score += points;
        hudPanel.repaint();
    }

    public int getScore() {
        return score;
    }

    public void drawScore(Graphics g, int screenWidth) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);

        // Draw score background rectangle
        int rectWidth = 150;
        int rectHeight = 30;
        int rectX = (screenWidth - rectWidth) / 2;
        int rectY = 10;

        g.setColor(new Color(0, 0, 0, 0)); // Semi-transparent black
        g.fillRect(rectX, rectY, rectWidth, rectHeight);

        // Draw score text
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, rectX + 10, rectY + 20);
    }
}
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
    private int elapsedTime;
    private int min;
    private int hour;
    private Timer gameTimer;

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

        this.score = 0;
        this.elapsedTime = 0;
        this.min = 0;
        this.hour = 0;

        // Initialize and start the game timer
        startGameTimer();
    }

    public void drawHud(Graphics g, int screenWidth) {
        drawScore(g, screenWidth);
        drawTimer(g, screenWidth);
    }

    private void drawScore(Graphics g, int screenWidth) {
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

    private void drawTimer(Graphics g, int screenWidth) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);

        // Draw timer background rectangle
        int rectWidth = 200;
        int rectHeight = 30;
        int rectX = screenWidth - rectWidth - 10; // Right side of the screen
        int rectY = 10;

        g.setColor(new Color(0, 0, 0, 0)); // Semi-transparent black
        g.fillRect(rectX, rectY, rectWidth, rectHeight);

        // Draw timer text
        g.setColor(Color.WHITE);
        g.drawString(String.format("Tempo: %02d:%02d:%02d", hour, min, elapsedTime), rectX + 10, rectY + 20);
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void setFrame(int frameNumber) {
        try {
            String imagePath = String.format("../assets/hearts_sprite_%02d.png", frameNumber);
            hudImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hudPanel.repaint();
    }

    public void startGameTimer() {
        gameTimer = new Timer(1000, e -> {
            elapsedTime++;
            if (elapsedTime == 60) {
                min++;
                elapsedTime = 0;
            }
            if (min == 60) {
                hour++;
                min = 0;
            }
            hudPanel.repaint();
        });
        gameTimer.start();
    }

    public JPanel getHudPanel() {
        return hudPanel;
    }
}

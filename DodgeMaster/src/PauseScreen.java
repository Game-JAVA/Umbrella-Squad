import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PauseScreen extends JFrame {

    private BufferedImage pauseImage;

    public PauseScreen() {
        try {
            pauseImage = ImageIO.read(new File("../assets/PauseScreen.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        setTitle("Pause Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(1360, 768));  // Tamanho mínimo da janela
        setLocationRelativeTo(null);

        // Make the layout responsive
        setLayout(new BorderLayout());
        JPanel imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            Image scaledImage = pauseImage.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, panelWidth, panelHeight, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PauseScreen());
    }
}

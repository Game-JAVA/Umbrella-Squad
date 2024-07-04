import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PauseScreen extends JFrame {

    private BufferedImage pauseImage;
    private Gameplay gameplay;

    public PauseScreen(Gameplay gameplay) {
        this.gameplay = gameplay;
        try {
            pauseImage = ImageIO.read(new File("../assets/PauseScreen.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        setTitle("Pause Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas a janela de pausa
        setSize(800, 600);
        setMinimumSize(new Dimension(1360, 768));  // Tamanho mínimo da janela
        setLocationRelativeTo(null);

        // Make the layout responsive
        setLayout(new BorderLayout());
        JPanel imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.CENTER);

        // Adiciona um listener para foco na janela
        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                requestFocusInWindow();
            }
        });

        // Add key listener to close pause screen on 'P' press
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_P) {
                    gameplay.togglePause();
                    gameplay.requestFocus(); // Retorna o foco para o Gameplay antes de fechar
                    dispose();
                }
            }
        });

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
}

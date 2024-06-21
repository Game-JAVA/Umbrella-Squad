import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    // Attributes
    private Image backgroundImage;

    // Constructors
    public ImagePanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    // Methods {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Adapts to the window width and height
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    // }

    // Setter
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        repaint();  // Updates the image
    }
}

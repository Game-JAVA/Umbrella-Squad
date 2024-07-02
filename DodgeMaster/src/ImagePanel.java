import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private final Image backgroundImage;

    public ImagePanel(String imagePath) {backgroundImage = new ImageIcon(imagePath).getImage();}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);        // Adapts to the window width and height:
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
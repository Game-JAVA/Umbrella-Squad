import javax.swing.*;
import java.awt.*;

public class Shield extends Rectangle {
    private boolean active;
    private JPanel shieldPanel;

    public Shield(int x, int y, int diameter) {
        super(x, y, diameter);
        this.active = true;

        shieldPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        shieldPanel.setOpaque(false); // Handle transparency
        shieldPanel.setBounds(x, y, diameter, diameter);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public JPanel getShieldPanel() {
        return shieldPanel;
    }

    public void setVisible(boolean visible) {
        shieldPanel.setVisible(visible);
    }

    public void draw(Graphics g) {
        // Desenhe o escudo aqui
        g.setColor(Color.BLUE);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}

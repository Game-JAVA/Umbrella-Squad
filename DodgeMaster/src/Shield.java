import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Shield extends Rectangle {
    // Attributes
    private int lifeSpan; // In milliseconds
    private JPanel shieldPanel;
    private BufferedImage shieldImage;
    private Timer timer;

    // Constructor
    public Shield(int x, int y, int diameter) {
        super(x, y, diameter, diameter);
        this.lifeSpan = lifeSpan;

        shieldPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        shieldPanel.setOpaque(false);
        shieldPanel.setBounds(x, y, diameter, diameter);

        timer = new Timer(lifeSpan, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {removeShield();}
        });
        timer.setRepeats(false); // Executes only once
        timer.start();
    }

    // Methods:
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(0, 0, getWidth(), getHeight());
    }

    public void removeShield() {
        // Removes the shield if it's not picked up yet
        if (timer != null)
            timer.stop();
        // Removes the shield from the plane
        if (shieldPanel.getParent() != null) {
            shieldPanel.getParent().remove(shieldPanel);
            shieldPanel.getParent().revalidate();
            shieldPanel.getParent().repaint();
        }
    }

    public void spawnGen(int screenWidth, int screenHeight) {
        Random r = new Random();

        int maxX = screenWidth - ((screenWidth*6)/100) - getWidth();
        int minX = (screenWidth*4)/100;
        int maxY = screenHeight - ((screenHeight*9)/100) - getHeight();
        int minY = (screenHeight*2)/100;

        setX(r.nextInt(maxX-minX+1)+minX);
        setY(r.nextInt(maxY-minY+1)-minY);
    }

    // Getters and Setters:
    public JPanel getShieldPanel() {return shieldPanel;}

    public void collectShield() {removeShield();}

    // toString
    @Override
    public String toString() {
        return super.toString() + " Shield{" +
                "lifeSpan=" + lifeSpan +
                ", shieldPanel=" + shieldPanel +
                ", timer=" + timer +
                '}';
    }
}

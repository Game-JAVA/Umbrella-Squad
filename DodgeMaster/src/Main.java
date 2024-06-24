import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dodge Master");
        Player david = new Player(0,0,0,0,100,100,3,10,"../assets/david_sprite_01.png");

        // Setting the game window {
        // Creating the main frame aka the game window
        frame.setMinimumSize(new Dimension(1360, 768)); // Minimum window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // Terminate window process when closed
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);               // Starts as windowed full-screen
        frame.setContentPane(new ImagePanel("../assets/bg_city.png"));  // Setting the background
        // Adding the player to the scene
        frame.setLayout(null);
        frame.add(david.getPlayerPanel());
        // Makes the frame visible
        frame.setVisible(true);
        // }


        // Game loop screen updater {
        // 16 milliseconds to approach 60 fps
        new Timer(16, e -> {
            david.move(frame.getWidth(), frame.getHeight()); // Updates the player position
            david.getPlayerPanel().repaint(); // Redraw the player in the frame
        }).start();
        // }

        // Player interaction {
        // Adding key listeners
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {david.keyPressed(e);}
            @Override
            public void keyReleased(KeyEvent e) {david.keyRelease(e);}
        });

        // Focus the JFrame to be able to capture the keyboard
        frame.requestFocus();
        // }
    }
}
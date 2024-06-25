import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gameplay extends JFrame {

    public Gameplay(String nivel) {
        // Creating the main JFrame (window) for the game
        JFrame frame = new JFrame("Dodge Master");

        // Creating a player instance with initial parameters
        Player david = new Player(0, 0, 0, 0, 100, 100, 3, 10, "../assets/david_sprite_01.png");

        // Setting up the game window
        frame.setMinimumSize(new Dimension(1360, 768)); // Minimum window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Start in fullscreen
        frame.setContentPane(new ImagePanel("../assets/bg_city.png")); // Setting background
        frame.setLayout(null); // Using null layout for absolute positioning

        // Adding the player's panel to the frame
        frame.add(david.getPlayerPanel());

        // Making the frame visible
        frame.setVisible(true);

        // Game loop to update game state
        new Timer(16, e -> {
            david.move(frame.getWidth(), frame.getHeight()); // Update player position
            david.getPlayerPanel().repaint(); // Redraw player on the frame
        }).start();

        // Player interaction via keyboard input
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                david.keyPressed(e); // Handle key press events in Player class
            }

            @Override
            public void keyReleased(KeyEvent e) {
                david.keyRelease(e); // Handle key release events in Player class
            }
        });

        // Focus the JFrame to capture keyboard input
        frame.requestFocus();
    }
}

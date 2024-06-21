import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dodge Master");
        Player david = new Player((frame.getWidth()/2),(frame.getHeight()/2),0,0,500,500,3,10,"../assets/david_sprite_01.png");

        // Setting the game window {
        // Creating the main frame aka the game window
        frame.setMinimumSize(new Dimension(1360, 768)); // Minimum window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // Terminate window process when closed
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);               // Starts as windowed full-screen
        // Setting the background
        frame.setContentPane(new ImagePanel("../assets/bg_city.png"));
        // Adding the player to the scene
        frame.add(david.getPlayerPanel());
        frame.setVisible(true); // Displays the window
        // }

        // Game loop screen updater {
        // 16 milliseconds to approach 60 fps
        new Timer(16, e -> {
            david.move(); // Atualiza a posição do jogador
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
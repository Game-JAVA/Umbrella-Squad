import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dodge Master");
        frame.setMinimumSize(new Dimension(1360, 768)); // Minimum window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        // Terminate window process when closed
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);               // Starts as windowed full-screen
        frame.setContentPane(new ImagePanel("../assets/bg_city.png"));
        frame.setVisible(true);                                      // Displays the window
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JFrame {
    // Attributes
    private JComboBox<String> difficultySelector;
    private String difficultySelected;

    // Constructor
    public HomeScreen() {
        // Set up the main frame
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Create and configure the background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("../assets/bg_homescreen.png"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {e.printStackTrace();}
            }
        };

        backgroundPanel.setLayout(new BorderLayout());

        // Create and configure the controls panel
        JPanel controlsPanel = new JPanel();
        controlsPanel.setOpaque(false);
        controlsPanel.setLayout(new GridBagLayout());

        // Load and scale the button image
        BufferedImage buttonImage = null;
        try { buttonImage = ImageIO.read(new File("../assets/startButton.png"));
        } catch (IOException e) {e.printStackTrace();}

        if (buttonImage != null) {
            Image scaledImage = buttonImage.getScaledInstance(250, 70, Image.SCALE_SMOOTH);
            ImageIcon buttonImageIcon = new ImageIcon(scaledImage);

            // Start Button
            JButton startButton = new JButton(buttonImageIcon);
            startButton.setToolTipText("Clique para iniciar o jogo");
            startButton.setOpaque(false); // Make button transparent
            startButton.setContentAreaFilled(false); // Remove button's filled area
            startButton.setBorderPainted(false); // Remove button border
            startButton.addActionListener(_ -> startGameplay());

            // Difficulty Selector
            difficultySelector = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
            difficultySelector.setPreferredSize(new Dimension(150, 30));
            difficultySelector.setFont(new Font("Arial", Font.BOLD, 22));
            difficultySelector.setBackground(Color.WHITE);
            difficultySelector.setForeground(Color.BLACK);

            // Configure GridBagConstraints
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.insets = new Insets(10, 10, 10, 10); // Padding

            // Add components to the controls panel
            gbc.gridy = 0;
            JLabel label = new JLabel("Selecione o nível:");
            label.setForeground(Color.YELLOW);
            label.setFont(new Font("Arial", Font.BOLD, 40));
            controlsPanel.add(label, gbc);

            gbc.gridy = 1;
            controlsPanel.add(difficultySelector, gbc);

            gbc.gridy = 2;
            controlsPanel.add(startButton, gbc);

            // Add the controls panel to the background panel
            backgroundPanel.add(controlsPanel, BorderLayout.CENTER);

            // Add the background panel to the JFrame
            add(backgroundPanel);
        } else {
            System.err.println("Erro ao carregar a imagem do botão.");
        }
    }

    // Start gameplay with the selected difficulty
    private void startGameplay() {
        difficultySelected = (String) difficultySelector.getSelectedItem();
        Gameplay gameplay = new Gameplay(difficultySelected);
        gameplay.setVisible(true);
        dispose(); // Close the home screen
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeScreen frame = new HomeScreen();
            frame.setVisible(true);
        });
    }
}
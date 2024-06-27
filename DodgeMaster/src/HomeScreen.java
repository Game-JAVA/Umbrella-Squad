import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JFrame {

    private JComboBox<String> nivelComboBox;
    private String nivelSelecionado = "Fácil";

    public HomeScreen() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
        setUndecorated(true); // Remove as decorações padrão do sistema operacional (bordas)
        setLayout(new BorderLayout());

        // Cria um JPanel para atuar como o painel de fundo com imagem
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha a imagem de fundo
                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("../assets/background.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        // Define o layout do painel de fundo
        backgroundPanel.setLayout(new BorderLayout());

        // Cria o painel para os controles
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Deixa o painel transparente para que o fundo com imagem seja visível
        panel.setLayout(new GridBagLayout());

        // Cria o botão iniciar com a imagem
        ImageIcon image = new ImageIcon("src/iconButton.jpg");
        JButton iniciarButton = new JButton("Iniciar", image);
        iniciarButton.setBackground(Color.BLUE);
        iniciarButton.setForeground(Color.RED);
        iniciarButton.setFont(new Font("Arial", Font.BOLD, 14));
        iniciarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iniciarButton.setToolTipText("Clique para iniciar o jogo");

        iniciarButton.addActionListener(e -> abrirTelaJogo());

        // Cria o JComboBox
        nivelComboBox = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
        nivelComboBox.addActionListener(e -> nivelSelecionado = (String) nivelComboBox.getSelectedItem());

        // Adiciona componentes ao painel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(40, 40, 40, 40);
        panel.add(new JLabel("Selecione o nível:"), gbc);

        gbc.gridy = 1;
        panel.add(nivelComboBox, gbc);

        gbc.gridy = 2;
        panel.add(iniciarButton, gbc);

        // Adiciona o painel de controles ao painel de fundo
        backgroundPanel.add(panel, BorderLayout.CENTER);

        // Adiciona o painel de fundo ao JFrame
        add(backgroundPanel);

    }

    private void abrirTelaJogo() {
        Gameplay gameplay = new Gameplay(nivelSelecionado);
        gameplay.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeScreen frame = new HomeScreen();
            frame.setVisible(true);
        });
    }
}

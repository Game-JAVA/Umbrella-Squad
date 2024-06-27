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
        setLayout(new BorderLayout());

        // Cria um JPanel para atuar como o painel de fundo com imagem
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha a imagem de fundo
                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("../assets/city.png"));
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
        ImageIcon image = new ImageIcon("../assets/botao.png");
        JButton iniciarButton = new JButton(image);
        iniciarButton.setSize(new Dimension(4, 5));
        iniciarButton.setToolTipText("Clique para iniciar o jogo");

        iniciarButton.addActionListener(e -> abrirTelaJogo());

        // Cria o JComboBox
        nivelComboBox = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
        nivelComboBox.setPreferredSize(new Dimension(150, 30)); // Ajuste o tamanho preferido conforme necessário
        nivelComboBox.setFont(new Font("segoe script", Font.BOLD, 15));
        nivelComboBox.setBackground(Color.WHITE); // Cor de fundo
        nivelComboBox.setForeground(Color.BLUE); // Cor do texto
        nivelComboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Borda preta

        // Adiciona componentes ao painel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(120, 40, 0, 40);
        JLabel label = new JLabel("Selecione o nível:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("segoe script", Font.BOLD, 20));
        panel.add(label);

        // Adiciona o label "Selecione o nível:"
        gbc.gridy = 1;
        panel.add(label, gbc);

        // Adiciona o JComboBox
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 40, 10, 40);
        panel.add(nivelComboBox, gbc);

        // Adiciona o botão "Iniciar"
        gbc.gridy = 3;
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

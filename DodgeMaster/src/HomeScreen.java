import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class HomeScreen extends JFrame {

    private JComboBox<String> nivelComboBox;
    private String nivelSelecionado = "Fácil";
    private Clip clip; // Variável para armazenar o áudio

    public HomeScreen() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
        setLayout(new BorderLayout());

        // song play
        tocarMusica("../assets/musica.wav");

        // Cria um JPanel para atuar como o painel de fundo com GIF
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Carrega o GIF como um ImageIcon
                ImageIcon gifIcon = new ImageIcon("../assets/dodge.gif");
                Image gifImage = gifIcon.getImage();
                g.drawImage(gifImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Define o layout do painel de fundo
        backgroundPanel.setLayout(new BorderLayout());

        // Cria o painel para os controles
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Deixa o painel transparente para que o fundo com GIF seja visível
        panel.setLayout(new GridBagLayout());

        // Carrega e redimensiona a imagem do botão
        BufferedImage buttonImage = null;
        try {
            buttonImage = ImageIO.read(new File("../assets/botao.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (buttonImage != null) {
            Image scaledImage = buttonImage.getScaledInstance(250, 70, Image.SCALE_SMOOTH); // Redimensiona a imagem
            ImageIcon buttonImageIcon = new ImageIcon(scaledImage);

            // Cria o botão iniciar com a imagem redimensionada
            JButton iniciarButton = new JButton(buttonImageIcon);
            iniciarButton.setToolTipText("Clique para iniciar o jogo");
            iniciarButton.setOpaque(false); // Deixa o botão transparente
            iniciarButton.setContentAreaFilled(false); // Remove a área de conteúdo preenchida
            iniciarButton.setBorderPainted(false); // Remove a borda do botão

            iniciarButton.addActionListener(e -> abrirTelaJogo());

            // Cria o JComboBox
            nivelComboBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});
            nivelComboBox.setPreferredSize(new Dimension(150, 30)); // Ajuste o tamanho preferido conforme necessário
            nivelComboBox.setFont(new Font("Arial", Font.BOLD, 22));
            nivelComboBox.setBackground(Color.WHITE); // Cor de fundo
            nivelComboBox.setForeground(Color.BLUE); // Cor do texto
            nivelComboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Borda preta

            // Adiciona componentes ao painel
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(140, 40, 20, 40);
            JLabel label = new JLabel("Difficult Selector:");
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 40));
            panel.add(label, gbc);

            // Adiciona o JComboBox
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 40, 30, 40);
            panel.add(nivelComboBox, gbc);

            // Adiciona o botão "Iniciar"
            gbc.gridy = 2;
            panel.add(iniciarButton, gbc);

            // Adiciona o painel de controles ao painel de fundo
            backgroundPanel.add(panel, BorderLayout.CENTER);

            // Adiciona o painel de fundo ao JFrame
            add(backgroundPanel);
        } else {
            System.err.println("Erro ao carregar a imagem do botão.");
        }
    }

    private void tocarMusica(String caminhoArquivo) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(caminhoArquivo).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Faz a música tocar em loop
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirTelaJogo() {
        nivelSelecionado = (String) nivelComboBox.getSelectedItem();
        Gameplay gameplay = new Gameplay(nivelSelecionado);
        gameplay.setVisible(true);
        clip.stop(); // Para a música quando a tela do jogo é aberta
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeScreen frame = new HomeScreen();
            frame.setVisible(true);
        });
    }
}
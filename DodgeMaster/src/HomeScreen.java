import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {

    private JComboBox<String> nivelComboBox;
    private String nivelSelecionado = "Fácil";
    private JPanel panel;

    public HomeScreen() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JButton iniciarButton = new JButton("Iniciar", new ImageIcon("iconButton.jpg"));
        iniciarButton.setBackground(Color.BLUE);
        iniciarButton.setForeground(Color.RED);
        iniciarButton.setFont(new Font("Arial", Font.BOLD, 14));
        iniciarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iniciarButton.setToolTipText("Clique para iniciar o jogo");

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaJogo();
            }
        });

        nivelComboBox = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
        nivelComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nivelSelecionado = (String) nivelComboBox.getSelectedItem();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(40, 40, 40, 40);
        panel.add(new JLabel("Selecione o nível:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nivelComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(iniciarButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void abrirTelaJogo() {
        Gameplay gameplay = new Gameplay(nivelSelecionado);
        gameplay.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HomeScreen frame = new HomeScreen();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;

public class TelaJogo extends JFrame {

    public TelaJogo(String nivel) {
        setTitle("Jogo - Nível: " + nivel);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel mensagemLabel = new JLabel("Bem-vindo ao jogo! Nível: " + nivel, SwingConstants.CENTER);
        panel.add(mensagemLabel, BorderLayout.CENTER);

        add(panel);
    }
}

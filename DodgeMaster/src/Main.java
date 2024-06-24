import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Main extends javax.swing.JFrame implements Runnable{
    // Attributes {
        // Frame size
    private int width = 1360;
    private int height = 768;
        // Background
    private ImagePanel backgroundPanel;
        // Keys (When the keyboard is pressed, the respective key is set true)
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    // }

    // Constructor
    public Main() {
        // Method to fetch initial setup
        initComponents();

        // Frame config
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Starts at windowed maximized
        setLayout(null); // Absolute Layout
        setContentPane(new ImagePanel("../assets/bg_city.png"));

        // Keyboard listener
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {formKeyPressed(evt);}
            public void keyReleased(java.awt.event.KeyEvent evt) {formKeyReleased(evt);}
        });

        setVisible(true);
        // Buffering
        createBufferStrategy(2);
        Thread t = new Thread(this);
        t.start();
    }

    private void initComponents() {
        setMinimumSize(new Dimension(width, height));
    }

    @SuppressWarnings("unchecked")
    // Verifying key press
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {left = true;}
        /* else */ if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {right = true;}
        /* else */ if (evt.getKeyCode() == KeyEvent.VK_UP) {up = true;}
        /* else */ if (evt.getKeyCode() == KeyEvent.VK_DOWN) {down = true;}
    }//GEN-LAST:event_formKeyPressed

    // Verifying key release
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {left = false;}
        /* else */ if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {right = false;}
        /* else */ if (evt.getKeyCode() == KeyEvent.VK_UP) {up = false;}
        /* else */ if (evt.getKeyCode() == KeyEvent.VK_DOWN) {down = false;}
    }//GEN-LAST:event_formKeyReleased

    // Initiate the window
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    public void run() {
        Graphics g = getBufferStrategy().getDrawGraphics();
        // Objects:



        //
        while(true) {
            // Updates g
            g = getBufferStrategy().getDrawGraphics();
            // Clear the screen
            g.clearRect(0, 0, getWidth(), getHeight());
            // Runtime:


            // Appends the screen
            getBufferStrategy().show();

            // Buffer in 16 milliseconds to approach the 60 fps
            try {Thread.sleep(16);} catch (InterruptedException ex) {ex.printStackTrace();}
        }
    }
}
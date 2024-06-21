import java.awt.event.KeyEvent;

public class Player extends Rectangle {
    //Atributos
    private int health = 100 ;

    private int pX,pY;

    //Construtor

    public Player() {
        this.health = health;
    }


    //Metodos
    public void move() {
        int newX = getX() + pX;
        int newY = getY() + pY;
    }

    //Reconhecer quando a tecla está pressionada
    public void keyPressed (KeyEvent tecla){
        int code = tecla.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            pY = -2;
        }
        if (code == KeyEvent.VK_DOWN) {
            pY = 2;
        }
        if (code == KeyEvent.VK_RIGHT) {
            pX = 2;
        }
        if (code == KeyEvent.VK_LEFT) {
            pX = -2;
        }
    }

    //Reconhecer quando a tecla não está pressionada
    public void keyRelease (KeyEvent tecla){
        int code = tecla.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            pY = 0;
        }
        if (code == KeyEvent.VK_DOWN) {
            pY = 0;
        }
        if (code == KeyEvent.VK_RIGHT) {
            pX = 0;
        }
        if (code == KeyEvent.VK_LEFT) {
            pX = 0;
        }
    }

}

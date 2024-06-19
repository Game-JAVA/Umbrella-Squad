import java.util.Random;

public abstract class Shape {
    //Atributos

    private int posX;

    private int posY;

    private int speedX;

    private int speedY;

    Random r = new Random();

    //Construtor

    public Shape() {
        this.posX = posX;   // Por enquanto nao será implementado por precisar do tamanhdo da tela.
        this.posY = posY;   // Por enquanto nao será implementado por precisar do tamanhdo da tela.
        this.speedX = 5;
        this.speedY = 5;
    }


    //Métodos

    public  void move(int screenHeightX,int screenWidth){
        this.posX += speedX;
        this.posY += speedY;
    }
    //Getters and Seters

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }


    //ToString


    @Override
    public String toString() {
        return "Shape{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                '}';
    }
}

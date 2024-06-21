public class Player extends Rectangle {
    //Atributos
    private int health = 100 ;

    //Construtor

    public Player() {
        this.health = health;
    }


    //Metodos
    public void move(int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);

        // Collision Treatment
        if (super.getX() < 0 || super.getX() + super.width > screenWidth)
            super.setSpeedX(super.getSpeedX() * -1);
        if (super.getY() < 0 || super.getY() + super.height > screenHeight)
            super.setSpeedY(super.getSpeedY() * -1);
    }

}

public class Bullet extends Rectangle {
    // Attributes
    private int damage;

    // Constructor
    public Bullet(int x, int y, int width, int height, int damage) {
        super(x, y, width, height);
        this.damage = damage;
    }

    // Methods
    @Override
    public void move(int screenWidth, int screenHeight) {super.move(screenWidth, screenHeight);}

    // Getters and Setters
    public int getDamage() {return damage;}
    public void setDamage(int damage) {this.damage = damage;}

    // toString
    @Override
    public String toString() {
        return super.toString() + " Bullet{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", speedX=" + getSpeedX() +
                ", speedY=" + getSpeedY() +
                ", damage=" + damage +
                '}';
    }
}

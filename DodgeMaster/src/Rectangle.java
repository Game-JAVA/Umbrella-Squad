public class Rectangle extends Shape {
    //Atributos
    private int width;
    private int height;

    //Construtor

    public Rectangle () {
        this.width = 10;
        this.height = 10; //tamanho nao definido ainda
    }



    //Métodos

    @Override
    public void move (int screenWidth, int screenHeight) {
        super.move(screenWidth, screenHeight);
        // Collision treatment

        if(super.getPosX() < 0 || super.getPosX() + this.width > screenWidth) {
            super.setSpeedX(super.getSpeedX() * -1);
        }

        if(super.getPosY() < 0 || super.getPosY() + this.height > screenHeight) {
            super.setSpeedY(super.getSpeedY() * -1);
        }

    }


    //Getter and Setter

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    //ToString


    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}'+
                super.toString();
    }
}

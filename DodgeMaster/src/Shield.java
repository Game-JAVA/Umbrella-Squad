import java.awt.*;

public class Shield extends Rectangle {
    //Atributos
    private int diameter;

    //Construtor
    public Shield(int diameter) {
        super(diameter, diameter);
        this.diameter = diameter;
    }

    public Shield(){
        super();
        super.setHeight(super.getWidth());
        this.diameter =super.getWidth();
    }

    //Metodos


    //get set

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }


    //toString


    @Override
    public String toString() {
        return "Shield{" +
                "diameter=" + diameter +
                '}';
    }
}

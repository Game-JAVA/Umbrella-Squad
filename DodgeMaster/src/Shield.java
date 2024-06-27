public class Shield extends Rectangle {
    // Attributes
    private int diameter;
    private int durability;

    // Constructor
    public Shield(int x, int y, int diameter, int durability) {
        super(x, y, diameter);
        this.diameter = diameter;
        this.durability = durability;
    }

    // Methods
    // Getters and Setters
    public int getDiameter() {return diameter;}
    public void setDiameter(int diameter) {this.diameter = diameter;}
    public int getDurability() {return durability;}
    public void setDurability(int durability) {this.durability = durability;}

    // toString
    @Override
    public String toString() {
        return super.toString() + "Shield{" +
                "diameter=" + diameter +
                ", durability=" + durability +
                '}';
    }
}

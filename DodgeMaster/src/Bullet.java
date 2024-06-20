// Classe Bullet que herda da classe Rectangle
public class Bullet extends Rectangle {

    // Variável para armazenar o dano da bala que seria de valor 10
    private int damage = 10;

    // Construtor que define o dano inicial da bala
    public Bullet(int damage) {
        // Inicializa o dano com o valor recebido
        this.damage = damage;
    }

    // Método para obter o dano atual da bala
    public int getDamage() {

        // Retorna o dano atual
        return damage;
    }

    // Método para configurar um novo valor de dano para a bala
    public void setDamage(int damage) {

        // Define um novo valor de dano
        this.damage = damage;
    }

    // Método principal para teste da classe Bullet
    public static void main(String[] args) {
        // Cria uma bala com dano inicial de 10
        Bullet bullet = new Bullet(10);
        
    }
}

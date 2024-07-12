# DodgeMaster

## Descrição
DodgeMaster é um jogo indie bullet hell onde o objetivo é desviar dos obstáculos e sobreviver o máximo possível. O jogo oferece uma experiência infinita com três níveis de dificuldade. Todo o desenvolvimento foi feito utilizando as bibliotecas Java AWT e Swing durante nosso estágio.

## Instruções

1. **Tela de Boas-Vindas:**
   - Botão para selecionar o nível de dificuldade.
   - Botão "Start" para iniciar o jogo.
   
   ![home-screen](https://github.com/rafael-rodrigues01/programming-language/assets/106329803/f77157f0-47a0-4c5b-bc71-ec6130490a35)

2. **Jogabilidade:**
   - O personagem principal deve desviar dos obstáculos que aparecem na tela.
   - A pontuação é baseada no tempo de sobrevivência e nas moedas coletadas.
   - A dificuldade aumenta conforme o jogo avança, com os obstáculos se movendo mais rápido.
   - Um HUD simples exibe a saúde e a pontuação do jogador.
   
   ![gameplay](https://github.com/rafael-rodrigues01/programming-language/assets/106329803/24ee7c6c-4023-472d-8365-30fff4df04ab)

## Controles
- Teclas WASD/setas para mover o personagem.
- Tecla 'P' para pausar o jogo.

## Link do Diagrama UML
[Diagrama UML](https://app.diagrams.net/#G14IMBfOikrQaJrdQjvGuJhNwxqZ268sJ8#%7B%22pageId%22%3A%22xYIkqrIyDs8MGjE_6Ou5%22%7D)

## Mermaid

``` mermaid
classDiagram

     class Shape {
        - x: int
        - y: int
        - speedX: int
        - speedY: int
        + move() int
        + draw() void
    }

    class Rectangle {
        - width: int
        - height: int
        - image: BufferedImage
         public BufferedImage loadImage()
         public JPanel createPanel()
        + move() void Override
        + draw() void
    }

    class Player {
        - health: int
        - speedIndex: int
        - playerPanel: JPanel
        - playerImage: Image
    private final Stack<Integer> xKeys = new Stack<>();   
    private final Stack<Integer> yKeys = new Stack<>(); 
    private boolean isMoving = false;
    private boolean isFacingLeft = false;
    private int frameUpdate = 0;
    private int frameIndex = 0;
    private boolean haveShield;


        + move() void Override
        + draw() void
      public void updateSize()
      public void keyPressed()
        + keyRelease() void
      private void updateSpeed()
    }

    class Shield {
        - shieldPanel: JPanel
        public void spawnGen()
        public boolean hasHit()
        + getShieldPanel() JPanel
    }

  class Bullet {
          private final JPanel bulletPanel;
          private final BufferedImage bulletImage;
          private int speedIndex;
          private int spawnSide;
          private boolean isVertical;

         public void move()
         public boolean isOutOfBounds()
         public void spawnGen()
         public boolean hasHit()
         public void draw()
    }

   class Hud {
      private JPanel hudPanel;
      private BufferedImage hudImage;
       private int score;
       private int elapsedTime;
       private int min;
       private int hour;
       private Timer gameTimer;

      public void drawHud()
      private void drawScore()
      private void drawTimer()
      public void addScore()
      public void setFrame()
      public void startGameTimer()
      public JPanel getHudPanel()
   }

    class HomeScreen {
        - nivelComboBox: String
        - nivelSelecionado: String
         - clip: Clip
         private void tocarMusica()
        - abrirTelaJogo() void
        + main() void
    }

    class Gameplay {
         - currentTime: double
         - isPaused: boolean
         - isGameOver: boolean
         - scoreTimer: Timer
         - config DifficultySettings.DifficultyConfig   
         -backgroundImage: Image
         - pauseImage: Image
         - gameOverImage: Image
         - backgroundPanel: JPanel
         - player: Player
         - hud: Hud
         - bullets: List<Bullet>
         - shields: List<Shield>
         - initComponents() void
         - run() void
         private void spawnBullet()
         private void handleBullets()
         private void spawnShield()
         private void handleShields()
         private void updateHUD()
         private void handleBulletHit()
         private int interpolate()
         public void togglePause()
    }

   class DifficultySettings {
      - eazyConfig: DiffcultyConfig
      - midConfig: DifficultyConfig
      - hardConfig: DifficultyConfig
      - setDifficulty()
   }

   class DifficultyConfig {
      - shieldGenGap
      - playerSpeed
      - bulletLateGenGap
      - bulletMidGenGap
      - bulletInitGenGap
      - bulletLateSpeed
      - bulletMidSpeed
      - bulletInitSpeed
      - Getters
   }

    Shape --|> Rectangle
    Rectangle --|> Player
    Rectangle --|> Shield
    Rectangle --|> Bullet
    Rectangle --|> Hud
    DifficultySettings --|> DifficultyConfig 

```

## Requisitos
- IDE Java ou compilador (recomendado: IntelliJ).

## Instalação
Para jogar, basta baixar o arquivo zip ou clonar o repositório:

```bash
git clone https://github.com/Game-JAVA/Umbrella-Squad.git

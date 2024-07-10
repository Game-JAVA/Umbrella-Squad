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
        + move() void Override
        + draw() void
        + getBounds() Rectangle
        + intersects() bool
    }

    class Player {
        - health: int
        - speedIndex: int
        - playerPanel: JPanel
        - playerImage: Image
        + move() void Override
        + draw() void
        + keyRelease() void
        + keyPressed() void
        - updateSpeed() void
    }

    class Shield {
        - active: bool
        - shieldPanel: JPanel
        + isActive() bool
        + setActive() void
        + getShieldPanel() JPanel
        + setVisible() bool
        + draw() void
    }

  class Bullet {
        - damage: int
        + move() void Override
    }

    class HomeScreen {
        - nivelComboBox: String
        - nivelSelecionado: String
        - abrirTelaJogo() void
        + main() void
    }

    class PauseScreen {
        - pauseImage: BufferedImage
        - gameplay: Gameplay
    }

    class Gameplay {
        - width: int
        - height: int
        - player: Player
        + run() void
    }

  class JPanel {
    }

    class ImagePanel {
        - backgroundImage: Image
        - gameplay: Gameplay
    }

    Shape --|> Rectangle
    Rectangle --|> Player
    Rectangle --|> Shield
    Rectangle --|> Bullet

    JPanel <|-- ImagePanel

```

## Requisitos
- IDE Java ou compilador (recomendado: IntelliJ).

## Instalação
Para jogar, basta baixar o arquivo zip ou clonar o repositório:

```bash
git clone https://github.com/Game-JAVA/Umbrella-Squad.git

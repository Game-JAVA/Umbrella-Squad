public class DifficultySettings {
    // Difficulty values matrix
    private static final DifficultyConfig EASY_CONFIG = new DifficultyConfig(10000, 5, 5000, 3000, 1000, 5, 7, 10);
    private static final DifficultyConfig MEDIUM_CONFIG = new DifficultyConfig(15000, 4, 4000, 2500, 800, 7, 9, 12);
    private static final DifficultyConfig HARD_CONFIG = new DifficultyConfig(20000, 3, 3000, 2000, 600, 10, 12, 15);

    private DifficultyConfig currentConfig;

    public DifficultySettings(String difficulty) {setDifficulty(difficulty);}

    // Intakes the difficulty of the start screen
    private void setDifficulty(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy":
            case "fácil":
                currentConfig = EASY_CONFIG;
                break;
            case "medium":
            case "médio":
                currentConfig = MEDIUM_CONFIG;
                break;
            case "hard":
            case "difícil":
                currentConfig = HARD_CONFIG;
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        }
    }

    // Returns the current difficulty
    public DifficultyConfig getCurrentConfig() {return currentConfig;}

    // Inter class to handle values
    public static class DifficultyConfig {
        private final int shieldGenGap; // Shield spawn try time gap
        private final int playerSpeed;  // :)
        private final int bulletInitGenPeriod;  // Initial gap between bullets spawn
        private final int bulletMidGenPeriod;   // Gap at 60 seconds
        private final int bulletLateGenPeriod;  // Gap at 180 seconds
        private final int bulletInitSpeed;  // Initial bullet speed
        private final int bulletMidSpeed;   // Bullet speed at 60 seconds
        private final int bulletLateSpeed;  // speed at more than 180 seconds

        public DifficultyConfig(int shieldGenGap, int playerSpeed, int bulletInitGenPeriod,
                                int bulletMidGenPeriod, int bulletLateGenPeriod,
                                int bulletInitSpeed, int bulletMidSpeed, int bulletLateSpeed) {
            this.shieldGenGap = shieldGenGap;
            this.playerSpeed = playerSpeed;
            this.bulletInitGenPeriod = bulletInitGenPeriod;
            this.bulletMidGenPeriod = bulletMidGenPeriod;
            this.bulletLateGenPeriod = bulletLateGenPeriod;
            this.bulletInitSpeed = bulletInitSpeed;
            this.bulletMidSpeed = bulletMidSpeed;
            this.bulletLateSpeed = bulletLateSpeed;
        }

        public int getShieldGenGap() {return shieldGenGap;}
        public int getPlayerSpeed() {return playerSpeed;}
        public int getBulletInitGenPeriod() {return bulletInitGenPeriod;}
        public int getBulletMidGenPeriod() {return bulletMidGenPeriod;}
        public int getBulletLateGenPeriod() {return bulletLateGenPeriod;}
        public int getBulletInitSpeed() {return bulletInitSpeed;}
        public int getBulletMidSpeed() {return bulletMidSpeed;}
        public int getBulletLateSpeed() {return bulletLateSpeed;}
    }
}
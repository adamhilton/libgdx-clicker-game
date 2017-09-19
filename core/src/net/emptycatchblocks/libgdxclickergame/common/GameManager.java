package net.emptycatchblocks.libgdxclickergame.common;

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();

    private float score = 0f;
    private float clickModifier = 1f;

    private GameManager() {}

    public float getScore() {
        return score;
    }

    public void updateScore(float amount) {
        score += amount;
    }

    public void reset() {
        score = 0;
    }

    public float getClickModifier() {
        return clickModifier;
    }
}

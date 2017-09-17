package net.emptycatchblocks.libgdxclickergame.system.input;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

import net.emptycatchblocks.libgdxclickergame.ClickerGame;
import net.emptycatchblocks.libgdxclickergame.common.GameManager;
import net.emptycatchblocks.libgdxclickergame.config.GameConfig;

public class InputProcessingSystem extends EntitySystem implements InputHandler {

    private final ClickerGame game;

    public InputProcessingSystem(ClickerGame game) {
        this.game = game;
        this.game.getRawInputHandler().register(this);
    }

    @Override
    public void click() {
        GameManager.INSTANCE.updateScore(GameConfig.CLICK_VALUE * GameManager.INSTANCE.getClickModifier());
    }

    @Override
    public void removedFromEngine(Engine engine) {
        game.getRawInputHandler().unregister(this);
    }
}

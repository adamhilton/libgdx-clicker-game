package net.emptycatchblocks.libgdxclickergame.common.input;

import com.badlogic.gdx.InputAdapter;

import net.emptycatchblocks.libgdxclickergame.system.InputProcessingSystem;

import java.util.ArrayList;
import java.util.List;

public class RawInputHandler extends InputAdapter {
    private List<InputHandler> listeners = new ArrayList<InputHandler>();

    public void register(InputHandler listener) {
        listeners.add(listener);
    }

    public void unregister(InputProcessingSystem listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (InputHandler listener : listeners) {
            listener.click();
        }
        return true;
    }
}

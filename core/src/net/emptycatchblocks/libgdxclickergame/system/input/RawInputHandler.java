package net.emptycatchblocks.libgdxclickergame.system.input;

import com.badlogic.gdx.InputAdapter;

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

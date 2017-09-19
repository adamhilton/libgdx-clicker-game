package net.emptycatchblocks.libgdxclickergame.component;

import com.badlogic.ashley.core.Component;

import net.emptycatchblocks.libgdxclickergame.common.AnimationState;

public class StateComponent implements Component {
    private AnimationState state = AnimationState.DEFAULT;
    public float time = 0.0f;
    public boolean isLooping = false;

    public void set(AnimationState state){
        this.state = state;
        time = 0.0f;
    }

    public AnimationState get(){
        return state;
    }
}
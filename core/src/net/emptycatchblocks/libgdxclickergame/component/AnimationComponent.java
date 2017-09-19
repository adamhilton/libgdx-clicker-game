package net.emptycatchblocks.libgdxclickergame.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.ArrayMap;

import net.emptycatchblocks.libgdxclickergame.common.AnimationState;

public class AnimationComponent implements Component {
    public ArrayMap<AnimationState, Animation> animations = new ArrayMap<AnimationState, Animation>();
}
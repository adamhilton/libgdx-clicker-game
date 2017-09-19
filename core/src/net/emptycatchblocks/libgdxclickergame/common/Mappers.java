package net.emptycatchblocks.libgdxclickergame.common;

import com.badlogic.ashley.core.ComponentMapper;

import net.emptycatchblocks.libgdxclickergame.component.AnimationComponent;
import net.emptycatchblocks.libgdxclickergame.component.ColorComponent;
import net.emptycatchblocks.libgdxclickergame.component.DimensionComponent;
import net.emptycatchblocks.libgdxclickergame.component.PositionComponent;
import net.emptycatchblocks.libgdxclickergame.component.StateComponent;
import net.emptycatchblocks.libgdxclickergame.component.TextureComponent;

public class Mappers {

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<ColorComponent> COLOR =
            ComponentMapper.getFor(ColorComponent.class);

    public static final ComponentMapper<AnimationComponent> ANIMATION =
            ComponentMapper.getFor(AnimationComponent.class);

    public static final ComponentMapper<StateComponent> STATE =
            ComponentMapper.getFor(StateComponent.class);

    private Mappers() {}

}

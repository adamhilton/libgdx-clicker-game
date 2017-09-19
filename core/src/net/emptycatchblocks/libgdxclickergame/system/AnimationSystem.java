package net.emptycatchblocks.libgdxclickergame.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.emptycatchblocks.libgdxclickergame.common.Mappers;
import net.emptycatchblocks.libgdxclickergame.component.AnimationComponent;
import net.emptycatchblocks.libgdxclickergame.component.StateComponent;
import net.emptycatchblocks.libgdxclickergame.component.TextureComponent;

public class AnimationSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            TextureComponent.class,
            AnimationComponent.class,
            StateComponent.class
    ).get();

    public AnimationSystem(){
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        AnimationComponent animationComponent = Mappers.ANIMATION.get(entity);
        StateComponent stateComponent = Mappers.STATE.get(entity);

        if(animationComponent.animations.containsKey(stateComponent.get())){
            TextureComponent textureComponent = Mappers.TEXTURE.get(entity);
            textureComponent.region = (TextureRegion) animationComponent.animations.get(stateComponent.get()).getKeyFrame(stateComponent.time, stateComponent.isLooping);
        }

        stateComponent.time += deltaTime;
    }
}
package net.emptycatchblocks.libgdxclickergame.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.emptycatchblocks.libgdxclickergame.common.Mappers;
import net.emptycatchblocks.libgdxclickergame.component.DimensionComponent;
import net.emptycatchblocks.libgdxclickergame.component.PositionComponent;
import net.emptycatchblocks.libgdxclickergame.component.TextureComponent;

public class TextureRenderSystem extends EntitySystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            TextureComponent.class
    ).get();

    private final Viewport viewport;
    private final Batch batch;

    private Array<Entity> renderQueue = new Array<Entity>();

    public TextureRenderSystem(Viewport viewport, Batch batch) {
        this.viewport = viewport;
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        draw();

        batch.end();

        renderQueue.clear();
    }

    private void draw() {
        for (Entity entity : renderQueue) {
            PositionComponent position = Mappers.POSITION.get(entity);
            DimensionComponent dimension = Mappers.DIMENSION.get(entity);
            TextureComponent texture = Mappers.TEXTURE.get(entity);

            batch.draw(
                    texture.region,
                    position.x,
                    position.y,
                    dimension.width,
                    dimension.height
            );
        }
    }
}

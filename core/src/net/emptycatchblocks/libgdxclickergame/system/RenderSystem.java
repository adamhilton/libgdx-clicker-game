package net.emptycatchblocks.libgdxclickergame.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.emptycatchblocks.libgdxclickergame.common.Mappers;
import net.emptycatchblocks.libgdxclickergame.component.ColorComponent;
import net.emptycatchblocks.libgdxclickergame.component.DimensionComponent;
import net.emptycatchblocks.libgdxclickergame.component.PositionComponent;


public class RenderSystem extends EntitySystem {

    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            ColorComponent.class
    ).get();

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    private Array<Entity> renderQueue = new Array<Entity>();

    public RenderSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());

        viewport.apply();

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        draw();

        renderer.end();

        renderQueue.clear();
    }

    private void draw() {
        for (Entity entity : renderQueue) {
            PositionComponent position = Mappers.POSITION.get(entity);
            DimensionComponent dimension = Mappers.DIMENSION.get(entity);
            ColorComponent colorComponent = Mappers.COLOR.get(entity);

            renderer.setColor(colorComponent.color);

            renderer.rect(
                    position.x,
                    position.y,
                    dimension.width,
                    dimension.height
            );
        }
    }
}

package net.emptycatchblocks.libgdxclickergame;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.emptycatchblocks.libgdxclickergame.component.ColorComponent;
import net.emptycatchblocks.libgdxclickergame.component.DimensionComponent;
import net.emptycatchblocks.libgdxclickergame.component.PositionComponent;
import net.emptycatchblocks.libgdxclickergame.config.GameConfig;
import net.emptycatchblocks.libgdxclickergame.system.RenderSystem;
import net.emptycatchblocks.libgdxclickergame.util.GdxUtils;

public class GameScreen implements Screen {

    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    private final ClickerGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;

    public GameScreen(ClickerGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();


        Entity stageEntity = createBox(0, 0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, Color.RED);
        engine.addEntity(stageEntity);

        Entity boxEntity = createBox(2, 2, 1, 1, Color.BLUE);
        engine.addEntity(boxEntity);


        engine.addSystem(new RenderSystem(viewport, renderer));
    }

    private Entity createBox(float x, float y, float width, float height, Color color) {
        PositionComponent positionComponent = new PositionComponent();
        positionComponent.x = x;
        positionComponent.y = y;

        DimensionComponent dimensionComponent = new DimensionComponent();
        dimensionComponent.width = width;
        dimensionComponent.height = height;

        ColorComponent colorComponent = new ColorComponent();
        colorComponent.color = color;

        Entity entity = engine.createEntity();
        entity.add(positionComponent);
        entity.add(dimensionComponent);
        entity.add(colorComponent);
        return entity;
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}

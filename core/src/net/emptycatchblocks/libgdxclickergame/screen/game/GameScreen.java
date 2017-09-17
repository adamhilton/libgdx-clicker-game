package net.emptycatchblocks.libgdxclickergame.screen.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.emptycatchblocks.libgdxclickergame.ClickerGame;
import net.emptycatchblocks.libgdxclickergame.assets.AssetDescriptors;
import net.emptycatchblocks.libgdxclickergame.component.ColorComponent;
import net.emptycatchblocks.libgdxclickergame.component.DimensionComponent;
import net.emptycatchblocks.libgdxclickergame.component.PositionComponent;
import net.emptycatchblocks.libgdxclickergame.config.GameConfig;
import net.emptycatchblocks.libgdxclickergame.system.HudRenderSystem;
import net.emptycatchblocks.libgdxclickergame.system.RenderSystem;
import net.emptycatchblocks.libgdxclickergame.system.input.InputProcessingSystem;
import net.emptycatchblocks.libgdxclickergame.system.input.RawInputHandler;
import net.emptycatchblocks.libgdxclickergame.util.GdxUtils;

public class GameScreen implements Screen {

    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    private final ClickerGame game;
    private final AssetManager assetManager;
    private final RawInputHandler rawInputHandler;

    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;

    public GameScreen(ClickerGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
        rawInputHandler = game.getRawInputHandler();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();

        BitmapFont font = assetManager.get(AssetDescriptors.FONT);

        Entity stageEntity = createBox(0, 0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, Color.RED);
        engine.addEntity(stageEntity);

        Entity boxEntity = createBox(2, 2, 1, 1, Color.BLUE);
        engine.addEntity(boxEntity);

        engine.addSystem(new InputProcessingSystem(game));
        engine.addSystem(new RenderSystem(viewport, renderer));
        engine.addSystem(new HudRenderSystem(hudViewport, game.getBatch(), font));
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
        hudViewport.update(width, height, true);
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

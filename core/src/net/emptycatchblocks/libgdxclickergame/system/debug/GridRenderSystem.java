package net.emptycatchblocks.libgdxclickergame.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GridRenderSystem extends EntitySystem {

    private static final Logger log = new Logger(GridRenderSystem.class.getName(), Logger.DEBUG);
    private static final int DEFAULT_CELL_SIZE = 1;

    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public GridRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        drawGrid(viewport, renderer);
    }

    private void drawGrid(Viewport viewport, ShapeRenderer renderer) {
        drawGrid(viewport, renderer, DEFAULT_CELL_SIZE);
    }

    private void drawGrid(Viewport viewport, ShapeRenderer renderer, int cellSize) {
        if(viewport == null) {
            throw new IllegalArgumentException("viewport parameter is required.");
        }

        if(renderer == null) {
            throw new IllegalArgumentException("renderer parameter is required.");
        }

        if(cellSize < DEFAULT_CELL_SIZE) {
            cellSize = DEFAULT_CELL_SIZE;
        }

        Color oldColor = new Color(renderer.getColor());

        int worldWidth = (int) viewport.getWorldWidth();
        int worldHeight = (int) viewport.getWorldHeight();
        int quadrupleWorldWidth = worldWidth * 4;
        int quadrupleWorldHeight = worldHeight * 4;

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        drawVerticalLines(renderer, cellSize, quadrupleWorldWidth, quadrupleWorldHeight);

        drawHorizontalLines(renderer, cellSize, quadrupleWorldHeight);

        drawXYAxisLines(renderer, quadrupleWorldWidth, quadrupleWorldHeight);

        drawWorldBounds(renderer, worldWidth, worldHeight);

        renderer.end();

        renderer.setColor(oldColor);
    }

    private void drawVerticalLines(ShapeRenderer renderer, int cellSize, int quadrupleWorldWidth, int quadrupleWorldHeight) {
        for(int x = -quadrupleWorldWidth; x < quadrupleWorldHeight; x += cellSize) {
            renderer.line(x, -quadrupleWorldHeight, x, quadrupleWorldHeight);
        }
    }

    private void drawHorizontalLines(ShapeRenderer renderer, int cellSize, int quadrupleWorldHeight) {
        for(int y = -quadrupleWorldHeight; y < quadrupleWorldHeight; y += cellSize) {
            renderer.line(-quadrupleWorldHeight, y, quadrupleWorldHeight, y);
        }
    }

    private void drawXYAxisLines(ShapeRenderer renderer, int quadrupleWorldWidth, int quadrupleWorldHeight) {
        renderer.setColor(Color.RED);
        renderer.line(0, -quadrupleWorldHeight, 0, quadrupleWorldHeight);
        renderer.line(-quadrupleWorldWidth, 0, quadrupleWorldWidth, 0);
    }

    private void drawWorldBounds(ShapeRenderer renderer, int worldWidth, int worldHeight) {
        renderer.setColor(Color.GREEN);
        renderer.line(0, worldHeight, worldWidth, worldHeight);
        renderer.line(worldWidth, 0, worldWidth, worldHeight);
    }
}
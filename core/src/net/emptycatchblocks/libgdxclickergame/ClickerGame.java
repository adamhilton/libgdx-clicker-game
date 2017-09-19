package net.emptycatchblocks.libgdxclickergame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

import net.emptycatchblocks.libgdxclickergame.screen.loading.LoadingScreen;
import net.emptycatchblocks.libgdxclickergame.common.input.RawInputHandler;

public class ClickerGame extends Game {
	private AssetManager assetManager;
	private SpriteBatch batch;
	private RawInputHandler rawInputHandler;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);

		batch = new SpriteBatch();

		rawInputHandler = new RawInputHandler();
		Gdx.input.setInputProcessor(rawInputHandler);

		setScreen(new LoadingScreen(this));
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		batch.dispose();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public RawInputHandler getRawInputHandler() {
		return rawInputHandler;
	}
}

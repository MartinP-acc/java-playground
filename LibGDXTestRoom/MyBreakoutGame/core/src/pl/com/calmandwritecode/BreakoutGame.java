package pl.com.calmandwritecode;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import pl.com.calmandwritecode.menu.MenuScreen;
import pl.com.calmandwritecode.scoreboard.ScoreBoard;
import pl.com.calmandwritecode.scoreboard.ScoreBoardManager;

public class BreakoutGame extends Game {

	public static float CENTER_X;
	public static float CENTER_Y;
	public static float W_WIDTH;
	public static float W_HEIGHT;

	public GameAssets gameAssets;
	public LevelManager levelManager;
	public PlayerData player;
	public ScoreBoard scoreBoard;
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	private BitmapFont bitmapFont;
	public boolean isScreenSet;

	@Override
	public void create () {
		levelManager = new LevelManager();
		scoreBoard = new ScoreBoardManager().loadScoreBoard();
		batch = new SpriteBatch();
		gameAssets = GameAssets.getInstance();
		gameAssets.loadAssets();
		isScreenSet = false;
		shapeRenderer = new ShapeRenderer();
		bitmapFont = new BitmapFont();

		W_WIDTH = (float) Gdx.graphics.getWidth();
		W_HEIGHT = (float) Gdx.graphics.getHeight();
		CENTER_X = W_WIDTH / 2;
		CENTER_Y = W_HEIGHT / 2;
	}

	@Override
	public void render () {
		super.render();

		if (!isScreenSet){
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			shapeRenderer.setAutoShapeType(true);
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.rect(CENTER_X-200,CENTER_Y-20,400,40);
			shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.rect(CENTER_X-195,CENTER_Y-15,390*gameAssets.loadProgress(),30);
			shapeRenderer.end();

			batch.begin();
			String formattedProgress = String.format("%.0f",(gameAssets.loadProgress()*100))+" %";
			bitmapFont.draw(batch,formattedProgress,CENTER_X,CENTER_Y+100, 50, Align.center, false);
			batch.end();

			if (gameAssets.isUpdated()){
				setScreen(new MenuScreen(this));
				isScreenSet = true;
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		gameAssets.dispose();
	}
}

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

		CENTER_X = (float) Gdx.graphics.getWidth()/2;
		CENTER_Y = (float) Gdx.graphics.getHeight()/2;
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
			bitmapFont.draw(batch,gameAssets.loadProgress()*100+" %",CENTER_X,CENTER_Y+100, 50, Align.center, false);
			batch.end();

			if (gameAssets.isUpdated()){
				setScreen(new MenuScreen(this));
				isScreenSet = true;
				System.out.println(gameAssets.loadProgress());
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

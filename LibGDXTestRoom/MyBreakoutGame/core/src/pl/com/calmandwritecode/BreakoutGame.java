package pl.com.calmandwritecode;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.com.calmandwritecode.menu.MenuScreen;
import pl.com.calmandwritecode.scoreboard.ScoreBoard;
import pl.com.calmandwritecode.scoreboard.ScoreBoardManager;

public class BreakoutGame extends Game {

	public LevelManager levelManager;
	public PlayerData player;
	public ScoreBoard scoreBoard;
	public SpriteBatch batch;

	@Override
	public void create () {
		levelManager = new LevelManager();
		setScreen(new MenuScreen(this));
		scoreBoard = new ScoreBoardManager().loadScoreBoard();
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

package pl.com.calmandwritecode;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import pl.com.calmandwritecode.menu.MenuScreen;
import pl.com.calmandwritecode.scoreboard.ScoreBoard;
import pl.com.calmandwritecode.scoreboard.ScoreBoardManager;

public class BreakoutGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public BitmapFont normFont;
	public LevelManager levelManager;
	public PlayerData player;
	public Skin skin;
	public ScoreBoard scoreBoard;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/bmfont120bold.fnt"));
		normFont = new BitmapFont();
		levelManager = new LevelManager();
		skin = new Skin(Gdx.files.internal("skins.json"));
		setScreen(new MenuScreen(this));
		scoreBoard = new ScoreBoardManager().loadScoreBoard();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}

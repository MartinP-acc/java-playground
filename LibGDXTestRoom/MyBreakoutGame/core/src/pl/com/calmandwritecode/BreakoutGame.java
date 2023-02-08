package pl.com.calmandwritecode;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import pl.com.calmandwritecode.menu.MenuScreen;

public class BreakoutGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public BitmapFont normFont;
	public Array<Level> levels;
	public PlayerData player;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/bmfont120bold.fnt"));
		normFont = new BitmapFont();
		setScreen(new MenuScreen(this));
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

package pl.com.calmandwritecode;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class WelcomeScreen implements Screen {

    BitmapFont font;
    SpriteBatch batch;
    OrthographicCamera camera;
    BreakoutGame game;

    private final float WIDTH;
    private final float HEIGHT;

    public WelcomeScreen(BreakoutGame game) {
        this.WIDTH = Gdx.graphics.getWidth();
        this.HEIGHT = Gdx.graphics.getHeight();
        this.font = game.font;
        this.batch = game.batch;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH, HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch,"Welcome to Breakot Test Game\nClick anywhere to start game",WIDTH/3,HEIGHT/2);
        batch.end();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            game.setScreen(new LevelScreen(game));
            this.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

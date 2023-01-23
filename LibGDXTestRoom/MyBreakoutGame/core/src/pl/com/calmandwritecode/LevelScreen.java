package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {

    private final BreakoutGame game;
    private final OrthographicCamera camera;

    private final Ball ball;
    private final Paddle paddle;
    private Array<Brick> bricks;
    private final LevelBuilder builder;
    private final TextureAtlas atlas;


    public LevelScreen(BreakoutGame game) {
        this.game = game;
        float WIDTH = Gdx.graphics.getWidth();
        float HEIGHT = Gdx.graphics.getHeight();

        atlas = new TextureAtlas("breakout-tx.atlas");

        ball = new Ball(WIDTH, HEIGHT,atlas);
        paddle = new Paddle(WIDTH,atlas);
        bricks = new Array<>();
        builder = new LevelBuilder(atlas);
        bricks = builder.buildFromString(LevelBuilder.LEVEL4);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        ball.draw(game.batch);
        paddle.draw(game.batch);
        drawBricks(game.batch);
        findBrickCollision();
        game.batch.end();
        paddle.collision(ball);
        paddle.update();
        ball.update();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            ball.accelerateBall();
        }

        if (ball.y<=0 || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            backToMenu();
        }
    }

    private void backToMenu(){
        game.setScreen(new WelcomeScreen(game));
        dispose();
    }

    private void drawBricks(SpriteBatch batch) {
        for (Brick brick : bricks){
            brick.draw(batch);
        }
    }

    private void findBrickCollision() {
        float distance = Float.MAX_VALUE;
        float currentDistance;
        Brick closest = null;
        for (Brick brick : bricks){
            if (Intersector.overlaps(ball,brick)){
                currentDistance = ball.position.dst(brick.center);
                if (currentDistance<distance) {
                    distance = currentDistance;
                    closest = brick;
                }
            }
        }
        if (closest != null){
            closest.collision(ball);
            if (closest.destroyed)
                bricks.removeIndex(bricks.indexOf(closest,true));
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
        atlas.dispose();
    }
}
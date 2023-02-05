package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {

    private final BreakoutGame game;
    private final OrthographicCamera camera;

    private final Ball ball;
    private final Paddle paddle;
    private Array<Brick> bricks;
    private final TextureAtlas atlas;
    private final LifeCounter lifeCounter;

    public LevelScreen(BreakoutGame game) {
        this.game = game;
        float WIDTH = Gdx.graphics.getWidth();
        float HEIGHT = Gdx.graphics.getHeight();

        atlas = new TextureAtlas("breakout-tx.atlas");

        ball = new Ball(WIDTH, HEIGHT,atlas);
        paddle = new Paddle(WIDTH,atlas);
        paddle.setReadyToThrow();

        Json json = new Json();
        Level level = json.fromJson(Level.class,Gdx.files.internal("levels/level2.json"));
        LevelBuilder builder = new LevelBuilder(atlas);
        bricks = new Array<>();
        bricks = builder.buildFromString(level.getBrickMap());
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        lifeCounter = new LifeCounter(atlas);



        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        paddle.draw(game.batch);
        drawBricks(game.batch);
        ball.draw(game.batch);
        lifeCounter.draw(game.batch);
        findBrickCollision();
        game.batch.end();
        paddle.collision(ball);
        paddle.update();
        ball.update();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            ball.accelerateBall();
        }

        if (ball.y < 0){
            lifeCounter.ballOut();
            ball.stop();
            paddle.setReadyToThrow();
        }

        if (paddle.isReadyToThrow()){
            ball.stickTo(paddle);
        }

        if (lifeCounter.isGameOver() || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
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
        boolean gameOn = false;
        Brick closest = null;
        for (Brick brick : bricks){
            if (brick.destryable) gameOn = brick.destryable;
            Vector2 intersection = brick.ballIntersect(ball);
            if (intersection != null){
                currentDistance = ball.position.dst(intersection);
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
            findBrickCollision();
        }

        if (!gameOn) backToMenu();
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
        ball.dispose();
    }
}

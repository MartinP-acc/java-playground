package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;

public class LevelScreen implements Screen {

    private final BreakoutGame game;
    private final OrthographicCamera camera;

    private final Ball ball;
    private final Paddle paddle;
    private Array<Brick> bricks;
    private final TextureAtlas atlas;
    private final LifeCounter lifeCounter;

    private long lastCheckoutTime;
    private long score;
    private final BitmapFont font;
    private int levelNr;

    public LevelScreen(BreakoutGame game, int levelNr) {
        System.out.println("starting :" + levelNr);
        System.out.println("levels in array :" + game.levels.size);
        this.levelNr = levelNr;
        this.game = game;
        float WIDTH = Gdx.graphics.getWidth();
        float HEIGHT = Gdx.graphics.getHeight();

        atlas = new TextureAtlas("breakout-tx.atlas");
        ball = new Ball(WIDTH, HEIGHT,atlas);
        paddle = new Paddle(WIDTH,atlas);
        paddle.setReadyToThrow();

        Level level = game.levels.get(levelNr);
        LevelBuilder builder = new LevelBuilder(atlas);
        bricks = new Array<>();
        bricks = builder.buildFromString(level.getBrickMap());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        lifeCounter = new LifeCounter(atlas);

        score = 0;
        font = new BitmapFont();

        lastCheckoutTime = TimeUtils.millis();
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
        font.draw(game.batch, "Score : "+score,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-10);
        game.batch.end();
        paddle.collision(ball);
        paddle.update();
        ball.update();

        if (TimeUtils.timeSinceMillis(lastCheckoutTime)>20000){
            ball.accelerateBall();
            System.out.println(ball.xSpeed+" "+ball.ySpeed);
            lastCheckoutTime = TimeUtils.millis();
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
            if (closest.destroyed) {
                score += closest.getPointsWorth();
                bricks.removeIndex(bricks.indexOf(closest, true));
            }
            findBrickCollision();
        }

        if (!gameOn) {
            levelNr++;
            if (game.levels.size>levelNr){
                game.setScreen(new LevelScreen(game,levelNr));
                System.out.println("level finished - next level :" + levelNr);
            }else{
                game.setScreen(new WelcomeScreen(game));
            }
            dispose();
        };
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
        lifeCounter.dispose();
    }
}

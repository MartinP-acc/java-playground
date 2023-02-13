package pl.com.calmandwritecode.game;

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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import pl.com.calmandwritecode.BreakoutGame;
import pl.com.calmandwritecode.GameAssets;
import pl.com.calmandwritecode.Level;
import pl.com.calmandwritecode.menu.MenuScreen;
import pl.com.calmandwritecode.scoreboard.NewScoreScreen;
import pl.com.calmandwritecode.scoreboard.ScoreBoardScreen;

public class LevelScreen implements Screen {

    private final BreakoutGame game;
    private final OrthographicCamera camera;

    private final Ball ball;
    private final SpriteBatch batch;
    private final Paddle paddle;
    private final BitmapFont defaultFont;
    private Array<Brick> bricks;
    private final TextureAtlas atlas;
    private final LifeCounter lifeCounter;
    private long lastCheckoutTime;

    public LevelScreen(BreakoutGame game) {
        this.game = game;
        float WIDTH = Gdx.graphics.getWidth();
        float HEIGHT = Gdx.graphics.getHeight();

        defaultFont = new BitmapFont();
        batch = game.batch;

        atlas = game.gameAssets.get(GameAssets.ATLAS_FILE);
        ball = new Ball(WIDTH, HEIGHT,atlas);
        paddle = new Paddle(WIDTH,atlas);

        bricks = new Array<>();

        camera = new OrthographicCamera();

        lifeCounter = new LifeCounter(atlas, game);
    }

    @Override
    public void show() {
        paddle.setReadyToThrow();

        Level level = game.levelManager.getLevel(game.player.getCurrentLevel());
        LevelBuilder builder = new LevelBuilder(atlas);

        bricks = builder.buildFromString(level.getBrickMap());

        float WIDTH = Gdx.graphics.getWidth();
        float HEIGHT = Gdx.graphics.getHeight();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        Gdx.input.setCursorCatched(true);
        lastCheckoutTime = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        paddle.draw(batch);
        drawBricks(batch);
        ball.draw(batch);
        lifeCounter.draw(batch,defaultFont);
        defaultFont.draw(batch, "Score : "+game.player.getScore(),Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()-10);
        batch.end();
        findBrickCollision();
        paddle.collision(ball);
        paddle.update();
        ball.update();

        if (TimeUtils.timeSinceMillis(lastCheckoutTime)>20000){
            ball.accelerateBall();
            lastCheckoutTime = TimeUtils.millis();
        }
        if (paddle.isReadyToThrow()){
            ball.stickTo(paddle);
        }

        if (ball.y < 0){
            lifeCounter.ballOut();
            ball.stop();
            if (!lifeCounter.noMoreLives()) paddle.setReadyToThrow();

        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            backToMenu();
        }

        if (lifeCounter.noMoreLives()){
            gameOver();
        }
    }

    private void gameOver() {
        Gdx.input.setCursorCatched(false);
        if (game.scoreBoard.worthToSave(game.player.getScore())){
            game.setScreen(new NewScoreScreen(game));
        }else
            game.setScreen(new ScoreBoardScreen(game));
        dispose();
    }

    private void backToMenu(){
        game.setScreen(new MenuScreen(game));
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
            if (brick.destroyable) gameOn = brick.destroyable;
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
                game.player.addToScore(closest.getPointsWorth());
                bricks.removeIndex(bricks.indexOf(closest, true));
            }
        }

        if (!gameOn) {
            game.player.nextLevel();
            if (game.levelManager.isLevelOnList(game.player.getCurrentLevel())){
                game.setScreen(new LevelScreen(game));
                dispose();
            }else{
                gameOver();
            }
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
        defaultFont.dispose();
    }
}

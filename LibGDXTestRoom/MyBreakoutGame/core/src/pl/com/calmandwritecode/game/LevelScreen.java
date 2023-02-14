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

    public enum GameStates{
        LVL_INTRO,
        SERVE,
        PLAY,
        PAUSE,
        RESUME,
    }

    private GameStates gameState;
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
    private LevelBuilder lvlBuilder;




    public LevelScreen(BreakoutGame game) {
        this.game = game;

        defaultFont = new BitmapFont();
        batch = game.batch;
        atlas = game.gameAssets.get(GameAssets.ATLAS_FILE);
        ball = new Ball(BreakoutGame.W_WIDTH, BreakoutGame.W_HEIGHT,atlas);
        paddle = new Paddle(BreakoutGame.W_WIDTH,atlas);
        bricks = new Array<>();
        camera = new OrthographicCamera();
        lifeCounter = new LifeCounter(atlas, game);
    }

    @Override
    public void show() {
        paddle.setReadyToThrow();

        Level level = game.levelManager.getLevel(game.player.getCurrentLevel());
        lvlBuilder = new LevelBuilder(atlas);

        bricks = lvlBuilder.buildFromString(level.getBrickMap());

        camera.setToOrtho(false, BreakoutGame.W_WIDTH, BreakoutGame.W_HEIGHT);

        Gdx.input.setCursorCatched(true);
        lastCheckoutTime = TimeUtils.millis();
        gameState = GameStates.LVL_INTRO;
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
        defaultFont.draw(batch, "Score : "+game.player.getScore(),BreakoutGame.CENTER_X,BreakoutGame.W_HEIGHT-10);
        batch.end();

        if (gameState.equals(GameStates.LVL_INTRO)){
            //TODO level title animation: move to center and fade in
            gameState = GameStates.SERVE;
        }

        if (gameState.equals(GameStates.SERVE)){
            paddle.update();
            ball.stickTo(paddle);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                ball.serve();
                gameState = GameStates.PLAY;
            }
        }

        if (gameState.equals(GameStates.PLAY)){

           findBrickCollision();
           paddle.collision(ball);

           ball.update();
           paddle.update();

           if (TimeUtils.timeSinceMillis(lastCheckoutTime)>10000){
               ball.accelerateBall();
               lastCheckoutTime = TimeUtils.millis();
           }

           if (ball.y < 0){
               lifeCounter.ballOut();
               if (lifeCounter.noMoreLives()) gameOver();
               else gameState = GameStates.SERVE;
           }

           if (pausePressed()) gameState = GameStates.PAUSE;
        }

        if (gameState.equals(GameStates.PAUSE)) pause();
        if (gameState.equals(GameStates.RESUME)) resume();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
           backToMenu();
        }
    }

    private boolean pausePressed() {
        return Gdx.input.isKeyPressed(Input.Keys.P) ||
                Gdx.input.isKeyPressed(Input.Keys.PAUSE);
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
        boolean lvlNotFinished = false;
        Brick closest = null;
        for (Brick brick : bricks){
            if (brick.destroyable) lvlNotFinished = brick.destroyable;
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

        if (!lvlNotFinished) {
            game.player.nextLevel();
            if (game.levelManager.isLevelOnList(game.player.getCurrentLevel())){
                Level level = game.levelManager.getLevel(game.player.getCurrentLevel());
                bricks = lvlBuilder.buildFromString(level.getBrickMap());
                gameState = GameStates.LVL_INTRO;
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
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            gameState = GameStates.RESUME;
    }

    @Override
    public void resume() {
        gameState = GameStates.PLAY;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        defaultFont.dispose();
    }
}

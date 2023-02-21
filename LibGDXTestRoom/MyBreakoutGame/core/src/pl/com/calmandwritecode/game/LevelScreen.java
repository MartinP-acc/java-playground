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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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

    private final Array<PowerUp> powerUps;
    private final Array<Shot> laserShots;
    private final BallsService ballsService;
    private final BreakoutGame game;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Paddle paddle;
    private final BitmapFont defaultFont;
    private final TextureAtlas atlas;
    private final LifeCounter lifeCounter;
    private final Stage stage;
    private long lastCheckoutTime;
    private int bonusRange;
    private Array<Brick> bricks;
    private LevelBuilder lvlBuilder;
    private GameStates gameState;

    public LevelScreen(BreakoutGame game) {
        this.game = game;
        defaultFont = new BitmapFont();
        batch = game.batch;
        atlas = game.gameAssets.get(GameAssets.ATLAS_FILE);
        ballsService = new BallsService(atlas);
        paddle = new Paddle(atlas);
        bricks = new Array<>();
        powerUps = new Array<>();
        laserShots = new Array<>();
        camera = new OrthographicCamera();
        lifeCounter = new LifeCounter(atlas, game);
        stage = new Stage();
    }

    @Override
    public void show() {
        lvlBuilder = new LevelBuilder(atlas);
        camera.setToOrtho(false, BreakoutGame.W_WIDTH, BreakoutGame.W_HEIGHT);
        Gdx.input.setCursorCatched(true);

        initLevel();
    }

    public void initLevel(){
        Level level = game.levelManager.getLevel(game.player.getCurrentLevel());
        bonusRange = (int) level.getPowerUpChance();
        bricks = lvlBuilder.buildFromString(level.getBrickMap());
        gameState = GameStates.LVL_INTRO;
        lastCheckoutTime = TimeUtils.millis();
    }

    private boolean actIntro(){
        if (stage.getActors().isEmpty()){
            Skin skin = new Skin(Gdx.files.internal("skins.json"));
            Label levelIntroLabel = new Label("LEVEL "+(game.player.getCurrentLevel()+1),skin,"scoreboard");
            levelIntroLabel.setPosition(-levelIntroLabel.getWidth(),BreakoutGame.CENTER_Y);

            MoveToAction moveToCenter1 = new MoveToAction();
            moveToCenter1.setPosition(BreakoutGame.CENTER_X,BreakoutGame.CENTER_Y);
            moveToCenter1.setDuration(0.6f);

            MoveToAction moveToCenter2 = new MoveToAction();
            moveToCenter2.setPosition(BreakoutGame.CENTER_X-levelIntroLabel.getWidth(),BreakoutGame.CENTER_Y);
            moveToCenter2.setDuration(0.2f);

            MoveToAction moveToCenter3 = new MoveToAction();
            moveToCenter3.setPosition(BreakoutGame.CENTER_X-levelIntroLabel.getWidth()/2,BreakoutGame.CENTER_Y);
            moveToCenter3.setDuration(0.1f);

            AlphaAction fadeIn = new AlphaAction();
            fadeIn.setAlpha(0);
            fadeIn.setDuration(2);

            SequenceAction moveCenterAndFadeIn = new SequenceAction();
            moveCenterAndFadeIn.addAction(moveToCenter1);
            moveCenterAndFadeIn.addAction(moveToCenter2);
            moveCenterAndFadeIn.addAction(moveToCenter3);
            moveCenterAndFadeIn.addAction(fadeIn);

            levelIntroLabel.addAction(moveCenterAndFadeIn);
            stage.addActor(levelIntroLabel);
        }else{
            if (stage.getActors().get(0).getActions().isEmpty()){
                stage.clear();
                return true;
            }
        }
        return false;
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
        drawPowerUps(batch);
        drawShots();
        ballsService.drawBalls(batch);
        lifeCounter.draw(batch,defaultFont);
        defaultFont.draw(batch, "Score : "+game.player.getScore(),BreakoutGame.CENTER_X,BreakoutGame.W_HEIGHT-10);
        batch.end();

        if (gameState.equals(GameStates.LVL_INTRO)){
            paddle.update();
            updateShots();
            ballsService.stickTo(paddle);
            if (actIntro()) {
                gameState = GameStates.SERVE;
            }
        }

        if (gameState.equals(GameStates.SERVE)){
            paddle.update();
            ballsService.stickTo(paddle);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                ballsService.serveAll();
                gameState = GameStates.PLAY;
            }
        }

        if (gameState.equals(GameStates.PLAY)){

           paddle.update();
           ballsService.updateAll(paddle);
           updatePowerUps();

           findBrickCollision();
           ballsService.collisionPaddle(paddle);

           if (TimeUtils.timeSinceMillis(lastCheckoutTime)>10000){
               ballsService.accelerateAll();
               lastCheckoutTime = TimeUtils.millis();
           }

           if (ballsService.allBallsOutScreen()){
               serveNewBall();

           }

           if (paddle.lasersActive() && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
               shoot();
           }

           if (paddle.isMagnetic()) ballsService.releaseBalls();

           if (!laserShots.isEmpty()){
               updateShots();
           }

           if (pausePressed()){
               lastCheckoutTime = TimeUtils.millis();
               gameState = GameStates.PAUSE;
           }
        }

        if (gameState.equals(GameStates.PAUSE)) pause();
        if (gameState.equals(GameStates.RESUME)) resume();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
           backToMenu();
        }
        stage.act(delta);
        stage.draw();

    }

    public void serveNewBall(){
        lifeCounter.ballOut();
        ballsService.setOneBall();
        paddle.reset();
        if (lifeCounter.noMoreLives()) gameOver();
        else gameState = GameStates.SERVE;
    }

    private void drawShots() {
        for (Shot shot : laserShots) shot.draw(batch);
    }

    private void updateShots() {
        for (int i=0; i<laserShots.size; i++){
            Shot shot = laserShots.get(i);
            shot.update();
            if (shot.isOutOfScreen()) laserShots.removeIndex(i);
        }
    }

    private void shoot() {
        if (laserShots.size < 5){
            laserShots.add(new Shot(paddle.getLaser1X(),atlas));
            laserShots.add(new Shot(paddle.getLaser2X(),atlas));
            paddle.removeOneShot();
        }
    }

    private void updatePowerUps() {
        for (int i=0; i<powerUps.size; i++){
            PowerUp power = powerUps.get(i);
            power.update();
            if (power.y < 0-power.height)
                powerUps.removeIndex(i);
            else if (power.overlaps(paddle)) {
                addPowerUpBonus(power.getPowerType());
                game.player.addToScore(power.getWorthPoint());
                powerUps.removeIndex(i);
            }
        }
    }

    private void addPowerUpBonus(int powerType) {
        if (powerType == PowerUp.ENLARGE_PADDLE){
            paddle.enlarge();
        } else if (powerType == PowerUp.SHRINK_PADDLE){
            paddle.shrink();
        } else if (powerType == PowerUp.LOSE_BALL){
            serveNewBall();
        } else if (powerType == PowerUp.EXTRA_BALL){
            lifeCounter.extraLife();
        } else if (powerType == PowerUp.SPEED_UP){
            ballsService.accelerateAll();
        } else if (powerType == PowerUp.SHRINK_BALL){
            ballsService.shrinkAll();
        } else if (powerType == PowerUp.SLOW_DOWN){
            ballsService.slowDownAll();
        } else if (powerType == PowerUp.POWER_BALL){
            ballsService.setPowerAll();
        } else if (powerType == PowerUp.LASERS){
            paddle.loadLasers();
        } else if (powerType == PowerUp.CLONE_BALL){
            ballsService.cloneAll();
        } else if (powerType == PowerUp.STICK_PADDLE){
            paddle.setPaddleStick();
        }
    }

    private void drawPowerUps(SpriteBatch batch){
        for(PowerUp powerUp : powerUps){
            powerUp.draw(batch);
        }
    }

    private boolean pausePressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.P) ||
                Gdx.input.isKeyJustPressed(Input.Keys.PAUSE);
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
        boolean lvlNotFinished = false;
        for (Brick brick : bricks){
            if (brick.destroyable) lvlNotFinished = brick.destroyable;

            ballsService.collisionBrick(brick);

            brick.collision(laserShots, ballsService.isPowerOn());

            if (brick.destroyed) {
                game.player.addToScore(brick.getPointsWorth());
                int luck = MathUtils.random(0,100);
                if (luck <= bonusRange) throwBonus(brick.x,brick.y);
                bricks.removeIndex(bricks.indexOf(brick, true));
            }
        }

        if (!lvlNotFinished) {
            game.player.nextLevel();
            if (game.levelManager.isLevelOnList(game.player.getCurrentLevel())){

                laserShots.clear();
                powerUps.clear();
                paddle.reset();
                ballsService.setOneBall();

                initLevel();
            }else{
                gameOver();
            }
        }
    }

    private void throwBonus(float x, float y) {
        powerUps.add(new PowerUp(x,y,atlas));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        Skin skin = new Skin(Gdx.files.internal("skins.json"));
        Label pauseLabel = new Label("PRESS 'P' TO RESUME",skin,"scoreboard");
        pauseLabel.setPosition(BreakoutGame.CENTER_X-pauseLabel.getWidth()/2,BreakoutGame.CENTER_Y);
        stage.addActor(pauseLabel);
        if (TimeUtils.timeSinceMillis(lastCheckoutTime)>500 && Gdx.input.isKeyJustPressed(Input.Keys.P))
            gameState = GameStates.RESUME;
    }

    @Override
    public void resume() {
        stage.clear();
        lastCheckoutTime = TimeUtils.millis();
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

package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class DropScreen implements Screen {

    private final TeardropGame game;
    private Texture dropImg;
    private Texture bucketImg;
    private Sound dropSound;
    private Music rainMusic;
    private OrthographicCamera camera;
    private Rectangle bucket;
    private Array<Rectangle> raindrops;
    private long lastDropTime;
    private int score;

    public DropScreen(TeardropGame game) {
        this.game = game;

        dropImg = new Texture(Gdx.files.internal("drop.png"));
        bucketImg = new Texture(Gdx.files.internal("bucket.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        bucket = new Rectangle();
        bucket.setSize(64,64);
        bucket.setPosition(400-32,20);

        score = 0;

        raindrops = new Array<>();
        spawnRaindrops();

    }

    private void spawnRaindrops() {
        Rectangle raindrop = new Rectangle();
        raindrop.setX(MathUtils.random(0,800-64));
        raindrop.setY(480);
        raindrop.setSize(64,64);
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0.5f,0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(bucketImg,bucket.x,bucket.y);
        for (Rectangle raindrop : raindrops){
            game.batch.draw(dropImg,raindrop.x,raindrop.y);
        }
        game.font.draw(game.batch, "Score : "+score,5,475);
        game.batch.end();

        if (Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);
            bucket.setX(touchPos.x-32);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800-64) bucket.x = 800-64;

        if (TimeUtils.nanoTime()-lastDropTime>1000000000) spawnRaindrops();

        for (Iterator<Rectangle> raindropIter = raindrops.iterator(); raindropIter.hasNext();){
            Rectangle raindrop = raindropIter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y < -64) raindropIter.remove();
            if (raindrop.overlaps(bucket)){
                dropSound.play();
                score++;
                raindropIter.remove();
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
        dropImg.dispose();
        bucketImg.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }
}

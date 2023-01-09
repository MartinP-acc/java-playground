package pl.com.calmandwritecode;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class TeardropG extends ApplicationAdapter {
	private Texture dropImg;
	private Texture bucketImg;
	private Sound dropSound;
	private Music rainMusic;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Rectangle bucket;
	private Array<Rectangle> raindrops;
	private long lastDropTime;

	
	@Override
	public void create () {

		dropImg = new Texture(Gdx.files.internal("drop.png"));
		bucketImg = new Texture(Gdx.files.internal("bucket.png"));

		dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		rainMusic.setLooping(true);
		rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.setSize(64,64);
		bucket.setPosition(400-32,20);

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
	public void render () {
		ScreenUtils.clear(0,0,0.5f,0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImg,bucket.x,bucket.y);
		for (Rectangle raindrop : raindrops){
			batch.draw(dropImg,raindrop.x,raindrop.y);
		}
		batch.end();

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
				raindropIter.remove();
			}
		}
	}
	
	@Override
	public void dispose () {
		dropSound.dispose();
		rainMusic.dispose();
		dropImg.dispose();
		bucketImg.dispose();
		batch.dispose();
	}
}

package pl.com.calmandwritecode;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class TannGame extends ApplicationAdapter {

	ShapeRenderer shape;
	Array<Ball> balls;
	int x = 50;
	int y = 50;
	int xSpeed = 5;

	@Override
	public void create() {
		shape = new ShapeRenderer();
		balls = new Array<>();
		for (int i=0; i<10; i++){
			balls.add(new Ball(
					MathUtils.random(Gdx.graphics.getWidth()),
					MathUtils.random(Gdx.graphics.getHeight()),
					MathUtils.random(5,100),
					MathUtils.random(3,15),
					MathUtils.random(3,15)
					));
		}
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for (Ball ball : balls){
			ball.draw(shape);
			ball.update();
		}
		shape.end();
	}
}

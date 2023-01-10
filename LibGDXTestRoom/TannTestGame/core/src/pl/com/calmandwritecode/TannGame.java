package pl.com.calmandwritecode;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class TannGame extends ApplicationAdapter {

	ShapeRenderer shape;
	Ball ball;
	Paddle paddle;

	@Override
	public void create() {
		shape = new ShapeRenderer();
		ball = new Ball(
					Gdx.graphics.getWidth()/2,
					Gdx.graphics.getHeight()/2,
					8,
					MathUtils.random(4,6),
					MathUtils.random(4,6)
					);
		paddle = new Paddle(Gdx.graphics.getWidth()/2);
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		ball.draw(shape);
		paddle.draw(shape);
		paddle.checkCollision(ball);
		ball.update();
		paddle.update();
		shape.end();
	}
}

package pl.com.calmandwritecode;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class TannGame extends ApplicationAdapter {

	ShapeRenderer shape;
	Ball ball;
	Paddle paddle;
	Array<Brick> bricks;

	@Override
	public void create() {
		shape = new ShapeRenderer();
		ball = new Ball(
					Gdx.graphics.getWidth()/4,
					Gdx.graphics.getHeight()/4,
					8,
					MathUtils.random(4,6),
					MathUtils.random(4,6)
					);
		paddle = new Paddle(Gdx.graphics.getWidth()/2);
		bricks = new Array<>();
		createBricks();
	}

	private void createBricks() {
		int x = 30;
		int y = Gdx.graphics.getHeight()/2;
		while (y < Gdx.graphics.getHeight()-20){
			while (x < Gdx.graphics.getWidth()-60){
				bricks.add(new Brick(x,y));
				x+=65;
			}
			y+=25;
			x=30;
		}
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		ball.draw(shape);
		paddle.draw(shape);
		for (Iterator<Brick> brickIterator = bricks.iterator(); brickIterator.hasNext();){
			Brick brick = brickIterator.next();
			brick.draw(shape);
			brick.update(ball);
			if (brick.destroyed) brickIterator.remove();
		}
		paddle.checkCollision(ball);
		ball.update();
		paddle.update();
		shape.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		shape.dispose();
	}
}

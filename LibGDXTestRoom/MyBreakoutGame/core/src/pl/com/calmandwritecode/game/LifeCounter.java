package pl.com.calmandwritecode.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import pl.com.calmandwritecode.BreakoutGame;

public class LifeCounter {

    private final Sprite paddleSprite;
    private final BreakoutGame game;

    public LifeCounter(TextureAtlas atlas, BreakoutGame game){
        this.game = game;
        paddleSprite = atlas.createSprite("small_paddle");
    }

    public void draw(SpriteBatch batch){
        batch.draw(paddleSprite, 10, Gdx.graphics.getHeight()-20);
        game.normFont.draw(batch," x "+game.player.getLives(),paddleSprite.getX()+paddleSprite.getWidth()+10,Gdx.graphics.getHeight()-10);
    }

    public void ballOut(){
        game.player.decLife();
    }

    public void extraLife(){
        game.player.extraLife();
    }

    public boolean noMoreLives(){
        return game.player.getLives() < 0;
    }

}

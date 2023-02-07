package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class LifeCounter {

    private Sprite paddleSprite;
    private BitmapFont font;
    private BreakoutGame game;

    public LifeCounter(TextureAtlas atlas, BreakoutGame game){
        this.game = game;
        paddleSprite = atlas.createSprite("small_paddle");
        font = new BitmapFont();
    }

    public void draw(SpriteBatch batch){
        batch.draw(paddleSprite, 10, Gdx.graphics.getHeight()-20);
        font.draw(batch," x "+game.player.getLives(),paddleSprite.getX()+paddleSprite.getWidth()+10,Gdx.graphics.getHeight()-10);
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

    public void dispose(){
        font.dispose();
    }

}

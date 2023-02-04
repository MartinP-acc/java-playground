package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class GridCell extends Rectangle {

    public boolean cursorOver;
    public Sprite sprite;

    public GridCell(float y, float x){
        super(15+50*x,200+30*y,50,30);
    }

    public void draw(ShapeRenderer shapeRenderer, Rectangle cursorRect){
        cursorOver = overlaps(cursorRect);
        if (cursorOver) shapeRenderer.setColor(Color.MAGENTA) ;
        shapeRenderer.rect(x,y,width,height);
        if (cursorOver) shapeRenderer.setColor(Color.WHITE);

    }

    public void drawSprite(SpriteBatch batch){
        if (sprite != null) batch.draw(sprite,x,y);
    }

    public void setTexture(TextureAtlas atlas, ButtonGroup<ImageButton> button) {
        if (cursorOver && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            String spriteName = "";
            switch (button.getCheckedIndex()){
                case 0: spriteName = "brick1"; break;
                case 1: spriteName = "brick2"; break;
                case 2: spriteName = "brick3"; break;
                case 3: spriteName = "brick4"; break;
                case 4: spriteName = "brick5"; break;
                case 5: spriteName = "brick6"; break;
                case 6: spriteName = "brick7"; break;
                case 7: spriteName = "brick8"; break;
                case 8: spriteName = "hard_brick0"; break;
                case 9: spriteName = "wall_brick"; break;
            }
            sprite = atlas.createSprite(spriteName);
        }

        if (cursorOver && Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            sprite = null;
        }
    }
}

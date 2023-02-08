package pl.com.calmandwritecode.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
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
    public String brickName;

    public GridCell(float y, float x){
        super(15+50*x,200+30*y,49,29);
        brickName = " ";
    }

    public void draw(ShapeRenderer shapeRenderer, Rectangle cursorRect){
        cursorOver = overlaps(cursorRect);
        if (cursorOver) shapeRenderer.setColor(Color.YELLOW) ;
        shapeRenderer.rect(x,y,width,height);
        if (cursorOver) shapeRenderer.setColor(Color.DARK_GRAY);

    }

    public void drawSprite(SpriteBatch batch){
        if (sprite != null) batch.draw(sprite,x,y);
    }

    public void setTexture(TextureAtlas atlas, ButtonGroup<ImageButton> button) {
        if (cursorOver && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            String spriteName = button.getButtons().get(button.getCheckedIndex()).getName();
            switch (button.getButtons().get(button.getCheckedIndex()).getName()){
                case "brick1": brickName = "b0"; break;
                case "brick2": brickName = "b1"; break;
                case "brick3": brickName = "b2"; break;
                case "brick4": brickName = "b3"; break;
                case "brick5": brickName = "b4"; break;
                case "brick6": brickName = "b5"; break;
                case "brick7": brickName = "b6"; break;
                case "brick8": brickName = "b7"; break;
                case "hard_brick0": brickName = "hb"; break;
                case "wall_brick": brickName = "wb"; break;
            }
            sprite = atlas.createSprite(spriteName);
        }

        if (cursorOver && Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            sprite = null;
            brickName=" ";
        }
    }
}

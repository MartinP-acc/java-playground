package pl.com.calmandwritecode.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.StringBuilder;

public class Grid {

    private final GridCell[][] cells;
    private final TextureAtlas atlas;
    private final Rectangle cursor;
    private boolean isActive;
    private final ButtonGroup<ImageButton> buttons;

    public Grid(TextureAtlas atlas, ButtonGroup<ImageButton> buttons) {
        this.buttons = buttons;
        this.cursor = new Rectangle(0,0,1,1);
        this.atlas = atlas;
        cells = new GridCell[16][25];
        cleanGrid();
        activate();
    }
    public void cleanGrid() {
        for (int row = 0; row<16; row++){
            for (int col = 0; col<25; col++){
                cells[row][col]=new GridCell(row,col);
            }
        }
    }

    public String saveToString(){
        StringBuilder map = new StringBuilder();
        for (int row = 15; row>=0; row--){
            for (int col = 0; col<25; col++){
                GridCell cell = cells[row][col];
                map.append(cell.brickName);
                if (!cell.brickName.equals(" ")) col++;
            }
            map.append("n");
        }
        return map.toString();
    }

    public void activate(){
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }

    public void updateCursor(){
        cursor.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
    }
    public void render(ShapeRenderer shapeRenderer){
        for (int row = 0; row<16; row++){
            for (int col = 0; col<25; col++){
                GridCell cell = cells[row][col];
                cell.draw(shapeRenderer,cursor);
                if (!isActive) cell.cursorOver = false;
                if (col==0 || (col<24 && cells[row][col-1].sprite==null)) {
                    cell.setTexture(atlas, buttons);
                }else{
                    cell.sprite=null;
                }
            }
        }
    }

    public void drawSprites(SpriteBatch batch){
        for (GridCell[] row : cells){
            for (GridCell cell : row){
                cell.drawSprite(batch);
            }
        }
    }
}
